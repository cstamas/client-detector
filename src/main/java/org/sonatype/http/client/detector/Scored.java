package org.sonatype.http.client.detector;

import java.util.Comparator;

public interface Scored
{
    float getScore();

    public static class BestScored
        implements Comparator<Scored>
    {
        public int compare( Scored o1, Scored o2 )
        {
            // "best scored" is actually reverse (descending) order on score
            return Float.compare( o2.getScore(), o1.getScore() );
        }
    }
}
