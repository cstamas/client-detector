package org.sonatype.http.client.detector.properties;

public class AbstractProperty<T>
    implements Property<T>
{

    private final T value;

    public AbstractProperty( final T value )
    {
        this.value = value;
    }

    @Override
    public String stringValue()
    {
        return String.valueOf( value() );
    }

    @Override
    public T value()
    {
        return value;
    }
}
