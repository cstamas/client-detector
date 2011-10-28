package org.sonatype.http.client.detector;

import org.sonatype.http.client.detector.properties.Property;

/**
 * The abstraction of the "client information", it's "capabilities", and anything else that is extractable from it's
 * User Agent string. Many clients sends different information, some of them only "family" name, while other reveal
 * the OS subtleties too, they run on.
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
     * A method for getting some property of the client.
     *
     * @param clazz of the property
     * @return the property asked for or null if not present.
     */
    <P extends Property> P getProperty( Class<P> clazz );

    /**
     * A shortcut method for asking about some properties of the client.
     *
     * @param clazz of the property
     * @return true only and only if the property is present and has non-null stringValue() value. If P is subclass of
     *         BooleanProperty, the value() result will be returned (hence, will be true if property exists, and it's
     *         boolean value is true).
     */
    <P extends Property> boolean hasProperty( Class<P> clazz );
}
