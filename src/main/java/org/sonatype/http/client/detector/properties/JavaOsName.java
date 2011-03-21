package org.sonatype.http.client.detector.properties;

/**
 * Property for Java OS name string, as it would be returned by command: {@code em.getProperty(“os.name”)} call.
 * Some clients does send thins information in their UA.
 * 
 * @author cstamas
 */
public class JavaOsName
    extends StringProperty
{
    public JavaOsName( final String value )
    {
        super( value );
    }
}
