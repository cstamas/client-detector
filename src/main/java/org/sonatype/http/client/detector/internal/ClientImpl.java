package org.sonatype.http.client.detector.internal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sonatype.http.client.detector.Client;
import org.sonatype.http.client.detector.properties.BooleanProperty;
import org.sonatype.http.client.detector.properties.ClientFullVersion;
import org.sonatype.http.client.detector.properties.ClientMajorVersion;
import org.sonatype.http.client.detector.properties.ClientOsFamily;
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

        this.properties = new HashMap<Class<? extends Property>, Property>();
        if ( properties != null )
        {
            for ( Property property : properties )
            {
                addProperty( property );
            }
        }
    }

    @Override
    public String getClientFamily()
    {
        return clientFamily;
    }

    @Override
    public String getClientMajorVersionString()
    {
        return getStringPropertyValue( ClientMajorVersion.class );
    }

    @Override
    public String getClientFullVersionString()
    {
        return getStringPropertyValue( ClientFullVersion.class );
    }

    @Override
    public <P extends Property> P getProperty( Class<P> clazz )
    {
        P prop = (P) getProperties().get( clazz );

        if ( prop != null )
        {
            return prop;
        }
        else
        {
            return null;
        }
    }

    @Override
    public <P extends Property> boolean hasProperty( Class<P> clazz )
    {
        P property = getProperty( clazz );

        if ( property != null )
        {
            if ( property instanceof BooleanProperty )
            {
                return ( (BooleanProperty) property ).value();
            }
            else
            {
                return property.stringValue() != null;
            }
        }

        return false;
    }

    // ==

    protected Map<Class<? extends Property>, Property> getProperties()
    {
        return properties;
    }

    protected void addProperty( Property p )
    {
        properties.put( p.getClass(), p );
    }

    protected <P extends StringProperty> String getStringPropertyValue( Class<P> clazz )
    {
        StringProperty sp = getProperty( clazz );

        if ( sp != null )
        {
            return sp.value();
        }

        return null;
    }
}
