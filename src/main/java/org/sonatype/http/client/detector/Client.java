package org.sonatype.http.client.detector;

public interface Client
    extends Scored
{
    /**
     * Returns client's OS family in Plexus Utilities compatible way. See {@link PlexusUtilsOs} class for more info.
     * While this library has no dependency declared on Plexus Utils, for reference, here are the Strings returned by
     * this call (copied from org.codehaus.plexus : plexus-utils : 2.0.5):
     * 
     * <pre>
     * public static final String FAMILY_DOS = &quot;dos&quot;;
     * 
     * public static final String FAMILY_MAC = &quot;mac&quot;;
     * 
     * public static final String FAMILY_NETWARE = &quot;netware&quot;;
     * 
     * public static final String FAMILY_OS2 = &quot;os/2&quot;;
     * 
     * public static final String FAMILY_TANDEM = &quot;tandem&quot;;
     * 
     * public static final String FAMILY_UNIX = &quot;unix&quot;;
     * 
     * public static final String FAMILY_WINDOWS = &quot;windows&quot;;
     * 
     * public static final String FAMILY_WIN9X = &quot;win9x&quot;;
     * 
     * public static final String FAMILY_ZOS = &quot;z/os&quot;;
     * 
     * public static final String FAMILY_OS400 = &quot;os/400&quot;;
     * 
     * public static final String FAMILY_OPENVMS = &quot;openvms&quot;;
     * </pre>
     * 
     * @return
     */
    String getClientOsFamily();

    /**
     * Returns the "common name" of client, like "firefox", "wget", or "apache-maven".
     * 
     * @return
     */
    String getClientFamily();

    /**
     * Returns the "major version" of client, like in case of Apache-Maven "2" or "3", or in case of Google Chrome
     * browser "8", "9" or "10".
     * 
     * @return
     */
    String getClientMajorVersionString();

    /**
     * Returns the full version string of the UA, like "3.0.2" for Apache-Maven 3.0.2 or "10.0.648.127" for Chrome
     * 10.0.648.127, etc.
     * 
     * @return
     */
    String getClientFullVersionString();

    /**
     * A method for asking about some properties of the client. It is intentionally "tri state", returning Boolean.TRUE
     * or Boolean.FALSE when we are positive about some property, or null if we cannot decide.
     * 
     * @param clazz
     * @return
     */
    Boolean hasProperty( Class<?> clazz );
}