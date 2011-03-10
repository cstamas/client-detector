package org.sonatype.http.client.detector.ua;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Named;
import javax.inject.Singleton;

import org.sonatype.http.client.detector.AbstractClientMatcher;
import org.sonatype.http.client.detector.ClientMatcher;
import org.sonatype.http.client.detector.PlexusUtilsOs;
import org.sonatype.http.client.detector.SimpleClient;
import org.sonatype.http.client.detector.properties.IsStateless;
import org.sonatype.http.client.detector.properties.IsTool;

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

        final String clientFamily = FAMILY;

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

        return new NexusClient( clientOsFamily, clientFamily, clientMajorVersionString, clientFullVersionString,
            edition, osName, osVersion, osArch, javaVersion, rrsProviderId, rrsProviderVersion, customization );
    }

    public static class NexusClient
        extends SimpleClient
    {
        private final String edition;

        private final String osName;

        private final String osVersion;

        private final String osArch;

        private final String javaVersion;

        private final String rrsProviderId;

        private final String rrsProviderVersion;

        private final String customization;

        public NexusClient( String clientOsFamily, String clientFamily, String clientMajorVersionString,
                            String clientFullVersionString, String edition, String osName, String osVersion,
                            String osArch, String javaVersion, String rrsProviderId, String rrsProviderVersion,
                            String customization )
        {
            super( 1.0f, clientOsFamily, clientFamily, clientMajorVersionString, clientFullVersionString, null );

            this.edition = edition;

            this.osName = osName;

            this.osVersion = osVersion;

            this.osArch = osArch;

            this.javaVersion = javaVersion;

            this.rrsProviderId = rrsProviderId;

            this.rrsProviderVersion = rrsProviderVersion;

            this.customization = customization;

            getProperties().put( IsStateless.class, Boolean.TRUE );
            getProperties().put( IsTool.class, Boolean.TRUE );
        }

        public String getEdition()
        {
            return edition;
        }

        public String getOsName()
        {
            return osName;
        }

        public String getOsVersion()
        {
            return osVersion;
        }

        public String getOsArch()
        {
            return osArch;
        }

        public String getJavaVersion()
        {
            return javaVersion;
        }

        public String getRrsProviderId()
        {
            return rrsProviderId;
        }

        public String getRrsProviderVersion()
        {
            return rrsProviderVersion;
        }

        public String getCustomization()
        {
            return customization;
        }
    }
}
