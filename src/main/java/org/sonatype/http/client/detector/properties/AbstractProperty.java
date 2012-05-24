package org.sonatype.http.client.detector.properties;

public abstract class AbstractProperty
    implements Property
{
    private final String stringValue;

    public AbstractProperty( final String stringValue )
    {
        this.stringValue = stringValue;
    }

    @Override
    public String stringValue()
    {
        return stringValue;
    }
}
