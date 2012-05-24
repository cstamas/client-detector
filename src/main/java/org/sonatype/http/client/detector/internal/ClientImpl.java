package org.sonatype.http.client.detector.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sonatype.http.client.detector.Client;
import org.sonatype.http.client.detector.properties.BooleanProperty;
import org.sonatype.http.client.detector.properties.ClientFullVersion;
import org.sonatype.http.client.detector.properties.ClientMajorVersion;
import org.sonatype.http.client.detector.properties.Property;
import org.sonatype.http.client.detector.properties.StringProperty;

/**
 * Default implementation of Client interface.
 * 
 * @author cstamas
 */
public class ClientImpl
    implements Client
{
    private final String clientFamily;

    private final Map<Class<? extends Property>, Property> properties;

    public ClientImpl( final String clientFamily, final List<Property> properties )
    {
        this.clientFamily = clientFamily;
        final Map<Class<? extends Property>, Property> props = new HashMap<Class<? extends Property>, Property>();
        if ( properties != null )
        {
            for ( Property property : properties )
            {
                props.put( property.getClass(), property );
            }
        }
        this.properties = Collections.unmodifiableMap( props );
    }

    @Override
    public String getClientFamily()
    {
        return clientFamily;
    }

    @Override
    public String getClientMajorVersionString()
    {
        return getPropertyStringValueOrNull( ClientMajorVersion.class );
    }

    @Override
    public String getClientFullVersionString()
    {
        return getPropertyStringValueOrNull( ClientFullVersion.class );
    }

    @Override
    public Map<Class<? extends Property>, Property> getProperties()
    {
        return properties;
    }

    @Override
    public <P extends Property> P getProperty( final Class<P> clazz )
    {
        return clazz.cast( properties.get( clazz ) );
    }

    @Override
    public <P extends Property> boolean has( final Class<P> clazz )
    {
        return getProperty( clazz ) != null;
    }

    @Override
    public <P extends StringProperty> boolean is( final Class<P> clazz, final String expected )
    {
        final P property = getProperty( clazz );
        if ( property == null )
        {
            return false;
        }

        return ( expected == null ? property.stringValue() == null : expected.equals( property.stringValue() ) );
    }

    @Override
    public <P extends BooleanProperty> boolean is( Class<P> clazz, boolean expected )
    {
        final P property = getProperty( clazz );
        if ( property == null )
        {
            return false;
        }

        return expected == property.booleanValue();
    }

    // ==

    protected <P extends Property> String getPropertyStringValueOrNull( final Class<P> clazz )
    {
        return getPropertyStringValueOrNull( getProperty( clazz ) );
    }

    protected String getPropertyStringValueOrNull( final Property prop )
    {
        if ( prop == null )
        {
            return null;
        }

        return prop.stringValue();
    }

}
