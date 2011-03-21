package org.sonatype.http.client.detector.properties;

public class AbstractProperty
    implements Property
{
    private final String stringValue;

    public AbstractProperty( final String stringValue )
    {
        this.stringValue = stringValue;
    }

    public String stringValue()
    {
        return stringValue;
    }
}
