package org.sonatype.http.client.detector;

import java.util.Map;

import org.sonatype.http.client.detector.properties.BooleanProperty;
import org.sonatype.http.client.detector.properties.Property;
import org.sonatype.http.client.detector.properties.StringProperty;

/**
 * The abstraction of the "client information", it's "capabilities", and anything else that is extracted from it's User
 * Agent string. Many clients sends different information, some of them only "family" name, while other reveal the OS
 * subtleties too, they run on.
 * 
 * @author cstamas
 */
public interface Client
{
    /**
     * Returns the "common name" of client, like "firefox", "wget", or "apache-maven".
     * 
     * @return "common name" if matched, or {@code null} if unknown.
     */
    String getClientFamily();

    /**
     * Returns the "major version" of client, like in case of Apache-Maven "2" or "3", or in case of Google Chrome
     * browser "8", "9" or "10".
     * 
     * @return the "major version" of the client, or {@code null} if unknown.
     */
    String getClientMajorVersionString();

    /**
     * Returns the full version string of the UA, like "3.0.2" for Apache-Maven 3.0.2 or "10.0.648.127" for Chrome
     * 10.0.648.127, etc.
     * 
     * @return the full version string of the client, or {@code null} if unknown.
     */
    String getClientFullVersionString();

    /**
     * A method for getting all properties that are defined for client. The returned map is an unmodifiable copy.
     * 
     * @return the map of all properties.
     */
    Map<Class<? extends Property>, Property> getProperties();

    /**
     * A method for getting some property of the client.
     * 
     * @param clazz of the property
     * @return the property asked for or {@code null} if not present.
     */
    <P extends Property> P getProperty( Class<P> clazz );

    /**
     * A shortcut method for asking about presence of some property of the client.
     * 
     * @param clazz of the property
     * @return true only and only if the property is present.
     */
    <P extends Property> boolean has( Class<P> clazz );

    /**
     * A shortcut method for asking about presence and value of some string property of the client.
     * 
     * @param clazz of the property
     * @return true only and only if the string property is present and has {@code expected} value (hence, will be true
     *         if property exists, and it's string value equals to {@code expected} parameter).
     */
    <P extends StringProperty> boolean is( Class<P> clazz, String expected );

    /**
     * A shortcut method for asking about presence and value of some boolean property of the client.
     * 
     * @param clazz of the property
     * @return true only and only if the boolean property is present and has {@code expected} value (hence, will be true
     *         if property exists, and it's boolean value is same as {@code expected}).
     */
    <P extends BooleanProperty> boolean is( Class<P> clazz, boolean expected );
}
