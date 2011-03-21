package org.sonatype.http.client.detector.properties;

/**
 * Marker for client running on handheld, smartphone or tablet.
 * 
 * @author cstamas
 */
public class IsHandheld
    extends BooleanProperty
{
    public IsHandheld( boolean value )
    {
        super( value );
    }
}
