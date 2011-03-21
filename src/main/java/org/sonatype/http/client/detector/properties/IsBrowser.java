package org.sonatype.http.client.detector.properties;

/**
 * Marker for "full blown" HTTP User Agents, like browser are, and unlike libraries are.
 * 
 * @author cstamas
 */
public class IsBrowser
    extends BooleanProperty
{
    public IsBrowser( boolean value )
    {
        super( value );
    }
}
