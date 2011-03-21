package org.sonatype.http.client.detector.properties;

/**
 * Marker for clients known to be stateful (like full blown browsers are).
 * 
 * @author cstamas
 */
public class IsStateful
    extends BooleanProperty
{
    public IsStateful( boolean value )
    {
        super( value );
    }
}
