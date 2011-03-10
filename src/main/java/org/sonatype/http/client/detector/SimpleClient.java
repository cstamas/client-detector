package org.sonatype.http.client.detector;

import java.util.HashMap;
import java.util.Map;

public class SimpleClient
    implements Client
{
    private final float score;

    private final String clientOsFamily;

    private final String clientFamily;

    private final String clientMajorVersionString;

    private final String clientFullVersionString;

    private final Map<Class<?>, Boolean> properties;

    public SimpleClient( final float score, final String clientOsFamily, final String clientFamily,
                         final String clientMajorVersionString, final String clientFullVersionString,
                         final Map<Class<?>, Boolean> properties )
    {
        this.score = score;
        this.clientOsFamily = clientOsFamily;
        this.clientFamily = clientFamily;
        this.clientMajorVersionString = clientMajorVersionString;
        this.clientFullVersionString = clientFullVersionString;
        this.properties = new HashMap<Class<?>, Boolean>();

        if ( properties != null )
        {
            this.properties.putAll( properties );
        }
    }

    public float getScore()
    {
        return score;
    }

    public String getClientOsFamily()
    {
        return clientOsFamily;
    }

    public String getClientFamily()
    {
        return clientFamily;
    }

    public String getClientMajorVersionString()
    {
        return clientMajorVersionString;
    }

    public String getClientFullVersionString()
    {
        return clientFullVersionString;
    }

    public Boolean hasProperty( Class<?> clazz )
    {
        return getProperties().get( clazz );
    }

    protected Map<Class<?>, Boolean> getProperties()
    {
        return properties;
    }
}
