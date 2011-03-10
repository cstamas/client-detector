package org.sonatype.http.client.detector;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Named( "default" )
@Singleton
public class Detector
{
    private final List<ClientMatcher> matchers;

    public @Inject
    Detector( List<ClientMatcher> matchers )
    {
        this.matchers = matchers;
    }

    /**
     * Returns the "best match" of matched clients, or null if no "best match" exists. Example usage:
     * 
     * <pre>
     * HttpServletRequest request = ...
     * 
     * Client matchedClient = matchBest( request.getHeader( "User-Agent" ) );
     * 
     * if (matchedClient != null) {
     *   ...
     * }
     * </pre>
     * 
     * @param uaString
     * @return
     */
    public Client matchBest( final String uaString )
    {
        final List<Client> clientMatches = match( uaString );

        if ( !clientMatches.isEmpty() )
        {
            return clientMatches.get( 0 );
        }
        else
        {
            return null;
        }
    }

    /**
     * Returns the ordered list ("best match" first, then descending) of matched clients, or empty list if no match was
     * possible. Never returns null. Example usage:
     * 
     * <pre>
     * HttpServletRequest request = ...
     * 
     * List&lt;Client&gt; matchedClients = match( request.getHeader( "User-Agent" ) );
     * 
     * if (!matchedClients.isEmpty()) {
     *   ...
     * }
     * </pre>
     * 
     * @param uaString
     * @return
     */
    public List<Client> match( final String uaString )
    {
        TreeSet<Client> result = new TreeSet<Client>( new Scored.BestScored() );

        for ( ClientMatcher matcher : matchers )
        {
            Client client = matcher.match( uaString );

            if ( client != null )
            {
                result.add( client );
            }
        }

        return new ArrayList<Client>( result );
    }
}
