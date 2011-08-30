#!/bin/ksh

# remove date specific information from the fits file
sedCmd="s/Java FITS: [A-Za-z][A-Za-z][A-Za-z] [A-Za-z][A-Za-z][A-Za-z] [0-9][0-9] [0-9][0-9]:[0-9][0-9]:[0-9][0-9] [-A-Z0-9a-z]\{3,4\} [0-9][0-9][0-9][0-9]/Java FITS: XXX XX XX:XX:XX XXXX XXXX/"
diff <(cat $1 | \sed "$sedCmd" | sort) <(cat $2 | \sed "$sedCmd" | sort)

