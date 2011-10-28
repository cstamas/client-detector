package org.sonatype.http.client.detector.properties;

/**
 * Property for Java OS version string, as it would be returned by command: {@code em.getProperty("os.version")} call.
 * Some clients does send thins information in their UA.
 * 
 * @author cstamas
 */
public class JavaOsVersion
    extends StringProperty
{
    public JavaOsVersion( final String value )
    {
        super( value );
    }
}
