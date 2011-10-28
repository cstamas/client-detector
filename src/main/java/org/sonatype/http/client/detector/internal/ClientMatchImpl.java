package org.sonatype.http.client.detector.internal;

import org.sonatype.http.client.detector.Client;
import org.sonatype.http.client.detector.ClientMatch;

/**
 * Default implementation of ClientMatch.
 *
 * @author: cstamas
 */
public class ClientMatchImpl
    implements ClientMatch
{

    private final int score;

    private final Client client;

    public ClientMatchImpl( final int score, final Client client )
    {
        this.score = score;
        this.client = client;
    }

    @Override
    public int getScore()
    {
        return score;
    }

    @Override
    public Client getClient()
    {
        return client;
    }

    @Override
    public int compareTo( final ClientMatch o )
    {
        // the "best order" is actually a reverse natural order by score (biggest 1st)
        return getScore() - o.getScore();
    }
}
