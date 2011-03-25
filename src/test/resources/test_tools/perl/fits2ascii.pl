#!/usr/bin/env perl
########################################################################
#
# File: fits2ascii.pl
#
# Author:  jcant		Created: Sat Feb 28 15:11:01 2009
#
# National Virtual Observatory; contributed by Center for Astrophysics
#
########################################################################


	my $usage = "
    Usage: perl fits2ascii.pl [ -h ][-o <outfile> ]
        Converts a FITS file to a wholly ascii representation.  Header
        records are written one per line; trailing white space is
        removed. Header records following 'END' records are deleted
        (to the end of the
        Data is converted to ascii and written one 'column' or field
        per line.  Each line contains all the data points for that
        column.

        Only one row, corresponding to one segment, is supported.

        options:
            -D do _not_ list data
            -o send output to <outfile>
            -h show this help

";

use strict;
use warnings;
use Getopt::Std;

	
	#
	# Process command line options
	#
	my $optionList = "Dho:";
	my $opt_h = undef;
	our $opt_o = undef;
	our $opt_D = undef;
	my $outfile = undef;

	&getopts( $optionList ) || die $usage;

	die $usage if ( $opt_h );

	$outfile = $opt_o;
	my $bNoListData = $opt_D;

	my $recCount = 0;
	my $input = undef;
	my $bytesRead = 0;

	sub eatRestOfBlock();
	sub listData( $ );
	sub unpack2( $ );
	sub unpack4( $ );
	sub unpack8( $ );

	# These are the fits datatype keys
	# L  1  Logical
	# X  ?  Bit
	# B  1  unsigned byte
	# I  2  16 bit integer
	# J  4  32 bit interger
	# K  8  64 bitn integer
	# A  1  character
	# E  4  single prec float
	# D  8  double prec float
	# C  8  single prec complex
	# M 16  double prec complex
	# P  8  Array Descriptor (32 bit)
	# Q 16  Array Descriptor (64-bit)

{ #main block

	open( IN, $ARGV[ 0 ] )or die "Can't open input: $ARGV[ 0 ]\n" ;

	if ( $outfile )
	{
		open( OUTFILE, ">$outfile" ) or die "Can't open $outfile for output";
		select( OUTFILE );
	}

	# read block 1, the mandatory image block
	while ( 1 )
	{
		my $rc = 0;
		$rc = read ( IN, $input, 80 );
		$bytesRead += $rc;

		last if $input =~ m/^END/;
		last if ( !$rc );
	}

	eatRestOfBlock();

	# read block 2
	my %dataArrangement = ();
	while (1)
	{
		my $rc = 0;
		$rc = read ( IN, $input,80 );
		$bytesRead += $rc;

		print "$input\n";

		last if $input =~ m/^END/;
		last if ( !$rc );

		# look for TFORM and get data size
		#  *= \"\[0-9]+\)[A-Z]/ )
		if ( $input =~ m/TFORM([0-9]+) *= '([0-9]+)([A-Z])/  )
		{
			$dataArrangement{ $1 } = [ $2, $3 ];
			#print "TFOMR!! $bytesRead   1:$1,    2:$2,   3:$3  "
			#.length(keys(%dataArrangement )) . " \n";
		}
	}
	eatRestOfBlock();

	listData( \%dataArrangement ) if ( !$bNoListData );

} #main block

sub listData( $ )
{
	my ( $dataSetRef ) = @_;

	my %dataSet = %$dataSetRef;

my @kys = keys( %dataSet );
#foreach my $k ( keys( %dataSet ) ){print "$#kys: \t$k \t" .  $dataSet{$k}. "\n";}
	my $variableCount = $#kys+1;
	for ( my $key = 1; $key < $variableCount +1; $key += 1 )
	{
		my $dataCount = $dataSet{ $key }[ 0 ];
		my $dataType  = $dataSet{ $key }[ 1 ];
		if ( $dataType eq "E" )
		{
			for ( my $i = 0; $i < $dataCount; $i++ )
			{
				my $nBytesRead = read ( IN, $input, 4 );
				die "Failed to read 4 bytes\n" if ( $nBytesRead != 4 );

				my $value = unpack4( $input );
				print "$value  ";
				my @chr = split( //, $input );
			}
			print "\n";
		
		}
		if ( $dataType eq "D" )
		{
			for ( my $i = 0; $i < $dataCount; $i++ )
			{
				my $nBytesRead = read ( IN, $input, 8 );
				die "Failed to read 8 bytes\n" if ( $nBytesRead != 8 );

				my $value = unpack8( $input );
				print "$value  ";
				my @chr = split( //, $input );
			}
			print "\n";
		
		}
		elsif ( $dataType eq "I" )
		{
			for ( my $i = 0; $i < $dataCount; $i++ )
			{
				my $nBytesRead = read ( IN, $input, 2 );
				die "Failed to read 2 bytes\n" if ( $nBytesRead != 2 );

				my $value = unpack2(  $input );
				print "$value  ";
			}
			print "\n";
		}
		elsif ( $dataType eq "J" )
		{
			for ( my $i = 0; $i < $dataCount; $i++ )
			{
				my $nBytesRead = read ( IN, $input, 4 );
				die "Failed to read 4 bytes\n" if ( $nBytesRead != 4 );

				my $value = unpack4(  $input );
				print "$value  ";
			}
			print "\n";
		}
		else
		{
			print "UNSUPPORTED DATA TYPE: $dataType\n";
		}
	}
}


sub eatRestOfBlock()
{
	# are we, by chance, at the end of a block?
	#
	return if ( $bytesRead % 2880 == 0 );
	my $blockend =  2880 * (int( $bytesRead/2880  )) + 2880 ;

	my $dontcare = undef;
	$bytesRead += read( IN, $dontcare, ( $blockend - $bytesRead ) );
}


sub unpack2( $ )
{
	my ( $in ) = @_;

	# split, reverse, join back together to get 'other-endian'
	# ordering of the bytes.
	my @x = split( //, $in );
	@x = reverse( @x );
	$in = join( "", @x );

	return unpack( "i", $in);
}

sub unpack4( $ )
{
	my ( $in ) = @_;

	# split, reverse, join back together to get 'other-endian'
	# ordering of the bytes.
	my @x = split( //, $in );
	@x = reverse( @x );
	$in = join( "", @x );

	return unpack( "i", $in);
}


sub unpack8( $ )
{
	my ( $in ) = @_;

	# split, reverse, join back together to get 'other-endian'
	# ordering of the bytes.
	my @x = split( //, $in );
	@x = reverse( @x );
	$in = join( "", @x );

	# 'd' ==> double precision float
	return unpack( "d", $in );
}
