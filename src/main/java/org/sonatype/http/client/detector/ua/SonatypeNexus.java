package org.sonatype.http.client.detector.ua;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Named;
import javax.inject.Singleton;

import org.sonatype.http.client.detector.AbstractClientMatcher;
import org.sonatype.http.client.detector.ClientMatcher;
import org.sonatype.http.client.detector.PlexusUtilsOs;
import org.sonatype.http.client.detector.SimpleClient;
import org.sonatype.http.client.detector.properties.ClientFullVersion;
import org.sonatype.http.client.detector.properties.ClientMajorVersion;
import org.sonatype.http.client.detector.properties.ClientOsFamily;
import org.sonatype.http.client.detector.properties.IsCliTool;
import org.sonatype.http.client.detector.properties.IsStateless;
import org.sonatype.http.client.detector.properties.JavaOsArch;
import org.sonatype.http.client.detector.properties.JavaOsName;
import org.sonatype.http.client.detector.properties.JavaOsVersion;
import org.sonatype.http.client.detector.properties.JavaVersion;
import org.sonatype.http.client.detector.properties.Property;
import org.sonatype.http.client.detector.properties.StringProperty;

@Named
@Singleton
public class SonatypeNexus
    extends AbstractClientMatcher
    implements ClientMatcher
{
    public static final String FAMILY = "nexus";

    // "Nexus/1.9.0 (OSS; Mac OS X; 10.6.6; x86_64; 1.6.22_22) httpClient/1.9.0 foo"
    private static final Pattern nexusUAPattern =
        Pattern.compile( "^Nexus/([0-9\\.]+) \\(([a-zA-Z0-9\\-_\\. ]+); ([a-zA-Z0-9\\-_\\. ]+); ([a-zA-Z0-9\\-_\\. ]+); ([a-zA-Z0-9\\-_\\. ]+); ([a-zA-Z0-9\\-_\\. ]+)\\)( ([a-zA-Z0-9\\-_\\. ]+)/([0-9\\.]+)( (.*))?)?$" );

    public NexusClient match( final String UAString )
    {
        if ( !UAString.startsWith( "Nexus/" ) )
        {
            return null;
        }

        Matcher m = nexusUAPattern.matcher( UAString );

        if ( !m.matches() )
        {
            return null;
        }

        final String clientOsFamily = PlexusUtilsOs.getPUOsFamilyFromJavaOsNameSystemProperties( m.group( 3 ) );

        final String clientFullVersionString = m.group( 1 );

        final String clientMajorVersionString =
            clientFullVersionString.substring( 0, clientFullVersionString.indexOf( '.' ) );

        final String edition = m.group( 2 );

        final String osName = m.group( 3 );

        final String osVersion = m.group( 4 );

        final String osArch = m.group( 5 );

        final String javaVersion = m.group( 6 );

        String rrsProviderId = null;
        String rrsProviderVersion = null;
        if ( m.groupCount() > 7 )
        {
            rrsProviderId = m.group( 8 );

            rrsProviderVersion = m.group( 9 );
        }

        String customization = null;
        if ( m.groupCount() > 9 && m.group( 10 ) != null && m.group( 10 ).trim().length() > 0 )
        {
            customization = m.group( 10 ).substring( 1 );
        }

        ArrayList<Property> properties = new ArrayList<Property>();

        // add known properties
        properties.add( new IsStateless( true ) );
        properties.add( new IsCliTool( true ) );

        properties.add( new ClientOsFamily( clientOsFamily ) );
        properties.add( new ClientMajorVersion( clientMajorVersionString ) );
        properties.add( new ClientFullVersion( clientFullVersionString ) );

        properties.add( new JavaOsName( osName ) );
        properties.add( new JavaOsVersion( osVersion ) );
        properties.add( new JavaOsArch( osArch ) );
        properties.add( new JavaVersion( javaVersion ) );

        // Nexus specific properties
        // edition
        properties.add( new NexusEdition( edition ) );

        if ( rrsProviderId != null )
        {
            // rrsProviderId
            properties.add( new NexusRemoteRepositoryStorageProviderId( rrsProviderId ) );
            // rrsProviderVersion
            properties.add( new NexusRemoteRepositoryStorageProviderVersion( rrsProviderVersion ) );
        }

        // customization
        if ( customization != null )
        {
            properties.add( new NexusRemoteRepositoryStorageCustomization( customization ) );
        }

        return new NexusClient( FAMILY, properties );
    }

    public static class NexusEdition
        extends StringProperty
    {
        public NexusEdition( final String value )
        {
            super( value );
        }
    }

    public static class NexusRemoteRepositoryStorageProviderId
        extends StringProperty
    {
        public NexusRemoteRepositoryStorageProviderId( final String value )
        {
            super( value );
        }
    }

    public static class NexusRemoteRepositoryStorageProviderVersion
        extends StringProperty
    {
        public NexusRemoteRepositoryStorageProviderVersion( final String value )
        {
            super( value );
        }
    }

    public static class NexusRemoteRepositoryStorageCustomization
        extends StringProperty
    {
        public NexusRemoteRepositoryStorageCustomization( final String value )
        {
            super( value );
        }
    }

    public static class NexusClient
        extends SimpleClient
    {
        public NexusClient( final String clientFamily, final List<Property> properties )
        {
            super( 1.0f, clientFamily, properties );
        }

        public String getEdition()
        {
            return getStringPropertyValue( NexusEdition.class );
        }

        public String getOsName()
        {
            return getStringPropertyValue( JavaOsName.class );
        }

        public String getOsVersion()
        {
            return getStringPropertyValue( JavaOsVersion.class );
        }

        public String getOsArch()
        {
            return getStringPropertyValue( JavaOsArch.class );
        }

        public String getJavaVersion()
        {
            return getStringPropertyValue( JavaVersion.class );
        }

        public String getRrsProviderId()
        {
            return getStringPropertyValue( NexusRemoteRepositoryStorageProviderId.class );
        }

        public String getRrsProviderVersion()
        {
            return getStringPropertyValue( NexusRemoteRepositoryStorageProviderVersion.class );
        }

        public String getCustomization()
        {
            return getStringPropertyValue( NexusRemoteRepositoryStorageCustomization.class );
        }
    }
}
