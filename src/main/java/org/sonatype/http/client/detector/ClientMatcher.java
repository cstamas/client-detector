package org.sonatype.http.client.detector;

public interface ClientMatcher
{
    /**
     * Returns Client if it has slightest possibility that UAString fits the client being tested. If 100% no match
     * exists, it returns null.
     * 
     * @param UAString
     * @return Client if there is slightest match, null otherwise.
     */
    Client match( final String UAString );
}
