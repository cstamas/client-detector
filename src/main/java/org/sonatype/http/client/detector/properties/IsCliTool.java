package org.sonatype.http.client.detector.properties;

/**
 * Marker for "tools", usually CLI tools like WGET or CURL is.
 * 
 * @author cstamas
 */
public class IsCliTool
    extends BooleanProperty
{
    public IsCliTool( boolean value )
    {
        super( value );
    }
}
