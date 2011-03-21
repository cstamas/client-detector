package org.sonatype.http.client.detector.properties;

/**
 * Marker for library User Agents, like Apache HttpClient is, etc.
 * 
 * @author cstamas
 */
public class IsLibrary
    extends BooleanProperty
{
    public IsLibrary( boolean value )
    {
        super( value );
    }
}
