package org.sonatype.http.client.detector.properties;

/**
 * Property for Java version string, as it would be returned by command: {@code em.getProperty(“java.version”)} call.
 * Some clients does send thins information in their UA.
 * 
 * @author cstamas
 */
public class JavaVersion
    extends StringProperty
{
    public JavaVersion( final String value )
    {
        super( value );
    }
}
