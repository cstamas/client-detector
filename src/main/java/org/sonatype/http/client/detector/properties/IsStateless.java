package org.sonatype.http.client.detector.properties;

/**
 * Marker for clients <em>known</em> to be stateless, like Apache Maven is.
 * 
 * @author cstamas
 */
public class IsStateless
    extends BooleanProperty
{
    public IsStateless( boolean value )
    {
        super( value );
    }
}
