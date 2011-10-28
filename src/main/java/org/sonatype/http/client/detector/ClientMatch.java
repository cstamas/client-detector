package org.sonatype.http.client.detector;

/**
 * A comparable client match holds the score of the match and the client instance.
 *
 * @author: cstamas
 */
public interface ClientMatch
    extends Comparable<ClientMatch>
{

    /**
     * Returns the score of the match. The biggest the better the "score" is. The score alone has no "absolute" meaning, it's
     * used only to relativize the relation between multiple matches.
     *
     * @return
     */
    int getScore();

    /**
     * The Client that is matched.
     *
     * @return the Client, never {@code null}
     */
    Client getClient();

}
