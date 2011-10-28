package org.sonatype.http.client.detector.properties;

/**
 * Property for OS family string, in "plexus-utils" way (to have a common standard).
 * Holds client's OS family in Plexus Utilities compatible way. See {@link org.sonatype.http.client.detector.internal.PlexusUtilsOs}
 * class for more info. While this library has no dependency declared on Plexus Utils, for reference, here are the Strings returned by
 * this call (copy+pasted from org.codehaus.plexus : plexus-utils : 2.0.5):
 * <p/>
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
 * @author cstamas
 */
public class ClientOsFamily
    extends StringProperty
{

    public ClientOsFamily( final String value )
    {
        super( value );
    }
}
