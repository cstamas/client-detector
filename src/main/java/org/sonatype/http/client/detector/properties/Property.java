package org.sonatype.http.client.detector.properties;

public interface Property<T>
{
    String stringValue();

    T value();
}
