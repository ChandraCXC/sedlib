package cfa.vo.sedlib;

import java.util.Comparator;

/**
 * <p>Compare class for Ranges. This compare class is intended for 
 *    comparing intervals based off of primarily their  mins.
 *    This allows tools to organize the interval based on its min values.
 *
 *
 */
public class RangeMinComparator implements Comparator<RangeParam>
{
    public int compare(RangeParam r1, RangeParam r2)
    {
        Double r1_min = r1.getMin ();
        Double r2_min = r2.getMin ();
        Double r1_max = r1.getMax ();
        Double r2_max = r2.getMax ();

        if ((r1.getMin () == r2.getMin ()) && (r1.getMax () == r2.getMax ()))
            return 0;

        if (!r1.isSetMin ())
            r1_min = r2_min;
        else if (!r2.isSetMin ())
            r2_min = r1_min;

        if (!r1.isSetMax ())
            r1_max = r2_max;
        else if (!r2.isSetMax ())
            r2_max = r1_max;


        if (r1_min < r2_min)
            return -1;
        else if (r1_min > r2_min)
            return 1;
        else
        {
            if (r1_max < r2_max)
                return -1;
            else
                return 1;
        }

    }

    public boolean equals(Object obj)
    {
        return super.equals (obj);
    }
}

