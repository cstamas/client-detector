package org.sonatype.http.client.detector.ua;

import java.util.Collections;
import java.util.List;

import org.sonatype.http.client.detector.ClientMatch;
import org.sonatype.http.client.detector.ClientMatcher;

public abstract class AbstractClientMatcher
    implements ClientMatcher
{

    /**
     * Just a simple "protection" ensuring there is no {@code null returned by implementors}.
     * @param uaString
     * @return
     */
    public final List<ClientMatch> match( final String uaString )
    {
        final List<ClientMatch> matches = getMatches( uaString );

        if ( null == matches )
        {
            return Collections.emptyList();
        }
        else
        {
            return matches;
        }
    }

    protected abstract List<ClientMatch> getMatches( final String uaString );

}
