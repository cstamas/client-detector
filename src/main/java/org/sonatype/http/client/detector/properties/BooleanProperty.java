package org.sonatype.http.client.detector.properties;

public abstract class BooleanProperty
    extends AbstractProperty
{
    private final boolean value;

    public BooleanProperty( final boolean value )
    {
        super( Boolean.toString( value ) );

        this.value = value;
    }

    public boolean value()
    {
        return value;
    }
}
