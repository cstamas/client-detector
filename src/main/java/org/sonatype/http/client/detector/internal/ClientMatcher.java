package org.sonatype.http.client.detector.internal;

import java.util.List;


/**
 * Matcher for one single client (the granularity depends on the "resolution" you want to achieve). It knows all about
 * possible UA Strings for that given client. Typically, you have a ClientMatcher ratio to "supported clients" close
 * to one-to-one, but there are some clients (see {@link org.sonatype.http.client.detector.ua.SonatypeNexus and
 * {@link org.sonatype.http.client.detector.ua.SonatypeNexusRRS} as example) that within same application provide different
 * "subsystems" and hence, different User Agent strings. Again, it's up to developer how "coarse" or "fine grained"
 * want to be with ClientMatcher implementations.
 *
 * @author: cstamas
 */
public interface ClientMatcher
{

    /**
     * Returns a list of {@link ClientMatch} if there is even smallest possibility that UA string matches the given client(s).
     * Returns empty list if it is completely positive that the UA string does not match the Client(s) supported by this instance.
     *
     *
     * @param uaString
     * @return
     */
    List<ClientMatch> match( final String uaString );

}
