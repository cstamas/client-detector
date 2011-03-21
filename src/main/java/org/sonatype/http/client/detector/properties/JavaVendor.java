package org.sonatype.http.client.detector.properties;

/**
 * Property for Java vendor string, as it would be returned by command: {@code em.getProperty(“java.vendor”)} call.
 * Some clients does send thins information in their UA.
 * 
 * @author cstamas
 */
public class JavaVendor
    extends StringProperty
{
    public JavaVendor( final String value )
    {
        super( value );
    }
}
