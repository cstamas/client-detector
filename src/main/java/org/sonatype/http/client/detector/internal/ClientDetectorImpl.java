package org.sonatype.http.client.detector.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.sonatype.http.client.detector.Client;
import org.sonatype.http.client.detector.ClientDetector;
import org.sonatype.http.client.detector.ClientMatch;
import org.sonatype.http.client.detector.ClientMatcher;

@Named
@Singleton
public class ClientDetectorImpl
    implements ClientDetector
{

    private final List<ClientMatcher> matchers;

    @Inject
    public ClientDetectorImpl( final List<ClientMatcher> matchers )
    {
        this.matchers = matchers;
    }

    @Override
    public Client matchBest( final String uaString )
    {
        final List<ClientMatch> clientMatches = gatherMatches( uaString );

        if ( !clientMatches.isEmpty() )
        {
            return clientMatches.get( 0 ).getClient();
        }
        else
        {
            return null;
        }
    }

    @Override
    public List<Client> match( final String uaString )
    {
        final ArrayList<Client> result = new ArrayList<Client>();

        final List<ClientMatch> clientMatches = gatherMatches( uaString );

        for ( ClientMatch match : clientMatches )
        {
            if ( match.getScore() > 0 )
            {
                result.add( match.getClient() );
            }
        }

        return result;
    }

    // ==

    protected List<ClientMatch> gatherMatches( final String uaString )
    {
        final TreeSet<ClientMatch> result = new TreeSet<ClientMatch>();

        for ( ClientMatcher matcher : matchers )
        {
            final List<ClientMatch> clientMatches = matcher.match( uaString );

            if ( clientMatches != null )
            {
                for ( ClientMatch match : clientMatches )
                {
                    if ( match != null )
                    {
                        result.add( match );
                    }
                }
            }
        }

        return new ArrayList<ClientMatch>( result );
    }
}
