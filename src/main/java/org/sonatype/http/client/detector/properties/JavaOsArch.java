package org.sonatype.http.client.detector.properties;

/**
 * Property for Java OS arch string, as it would be returned by command: {@code em.getProperty(“os.arch”)} call.
 * Some clients does send thins information in their UA.
 * 
 * @author cstamas
 */
public class JavaOsArch
    extends StringProperty
{
    public JavaOsArch( final String value )
    {
        super( value );
    }
}
