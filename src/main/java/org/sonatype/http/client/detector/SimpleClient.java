package org.sonatype.http.client.detector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sonatype.http.client.detector.properties.BooleanProperty;
import org.sonatype.http.client.detector.properties.ClientFullVersion;
import org.sonatype.http.client.detector.properties.ClientMajorVersion;
import org.sonatype.http.client.detector.properties.ClientOsFamily;
import org.sonatype.http.client.detector.properties.Property;
import org.sonatype.http.client.detector.properties.StringProperty;

public class SimpleClient
    implements Client
{
    private final float score;

    private final String clientFamily;

    private final Map<Class<? extends Property>, Property> properties;

    public SimpleClient( final float score, final String clientFamily, final List<Property> properties )
    {
        this.score = score;
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

    public float getScore()
    {
        return score;
    }

    public String getClientFamily()
    {
        return clientFamily;
    }

    public String getClientOsFamily()
    {
        return getStringPropertyValue( ClientOsFamily.class );
    }

    public String getClientMajorVersionString()
    {
        return getStringPropertyValue( ClientMajorVersion.class );
    }

    public String getClientFullVersionString()
    {
        return getStringPropertyValue( ClientFullVersion.class );
    }

    @SuppressWarnings( "unchecked" )
    public <P extends Property> P getProperty( Class<P> clazz )
    {
        Property prop = getProperties().get( clazz );

        if ( prop != null )
        {
            return (P) prop;
        }
        else
        {
            return null;
        }
    }

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
