package org.sonatype.http.client.detector;

import java.util.List;

/**
 * Client detector to "detect" clients based on their User Agent string. You usually get the UA string from HTTP request
 * "User-Agent" header (if in web environment), but you can use any alternative means to do so. Example usage:
 * <p/>
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
 */
public interface ClientDetector
{
    /**
     * Returns the "best match" of matched clients, or {@code null} if no "best match" exists. This is actually a
     * shortcut for {@link #match(String)} that will return 1st element of the list.
     * 
     * @param uaString
     * @return the best matched Client or {@code null} if none exists.
     */
    Client matchBest( final String uaString );

    /**
     * Returns the ordered list (with "best match" first, then descending) of matched clients, or empty list if no match
     * was possible. Never returns {@code null}.
     * 
     * @param uaString
     * @return ordered list in descending order by "match", never {@code null}.
     */
    List<Client> match( final String uaString );
}
