package org.sonatype.http.client.detector.properties;

public abstract class StringProperty
    extends AbstractProperty
{
    public StringProperty( final String value )
    {
        super( value );
    }
    
    public String value()
    {
        return stringValue();
    }
}
