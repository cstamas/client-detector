package org.sonatype.http.client.detector;

import org.sonatype.guice.bean.containers.InjectedTestCase;
import org.sonatype.http.client.detector.internal.ClientDetectorImpl;
import org.sonatype.http.client.detector.properties.ClientEdition;
import org.sonatype.http.client.detector.properties.ClientOsFamily;
import org.sonatype.http.client.detector.properties.ClientSubsystem;
import org.sonatype.http.client.detector.properties.IsCliTool;
import org.sonatype.http.client.detector.properties.IsStateless;
import org.sonatype.http.client.detector.properties.JavaOsArch;
import org.sonatype.http.client.detector.properties.JavaOsName;
import org.sonatype.http.client.detector.properties.JavaOsVersion;
import org.sonatype.http.client.detector.properties.JavaVendor;
import org.sonatype.http.client.detector.properties.JavaVersion;
import org.sonatype.http.client.detector.ua.SonatypeNexus;
import org.sonatype.http.client.detector.ua.SonatypeNexus.NexusClient;

public class SimpleDetectorTest
    extends InjectedTestCase
{

    public void testNexus()
    {
        // when nexus proxy repo reaches out (RRS presence shows that)
        final String nexusUaStr = "Nexus/1.9.0 (OSS; Mac OS X; 10.6.6; x86_64; 1.6.22_22) httpClient/1.9.0 foo";

        ClientDetector detector = lookup( ClientDetector.class );

        Client client = detector.matchBest( nexusUaStr );

        assertNotNull( client );

        assertEquals( "nexus", client.getClientFamily() );
        assertEquals( "rrs", client.getProperty( ClientSubsystem.class ).stringValue() );
        assertEquals( "mac", client.getProperty( ClientOsFamily.class ).stringValue() );
        assertEquals( SonatypeNexus.FAMILY, client.getClientFamily() );
        assertEquals( "1", client.getClientMajorVersionString() );
        assertEquals( "1.9.0", client.getClientFullVersionString() );

        assertEquals( true, client.is( IsStateless.class, true ) );
        assertEquals( true, client.is( IsCliTool.class, true ) );

        assertTrue( client instanceof NexusClient );

        NexusClient nexusClient = (NexusClient) client;

        assertEquals( "OSS", nexusClient.getProperty( ClientEdition.class ).stringValue() );
        assertEquals( "Mac OS X", nexusClient.getProperty( JavaOsName.class ).stringValue() );
        assertEquals( "10.6.6", nexusClient.getProperty( JavaOsVersion.class ).stringValue() );
        assertEquals( "x86_64", nexusClient.getProperty( JavaOsArch.class ).stringValue() );
        assertEquals( "1.6.22_22", nexusClient.getProperty( JavaVersion.class ).stringValue() );
        assertEquals( false, nexusClient.has( JavaVendor.class ) );

        assertEquals( "httpClient", nexusClient.getRrsProviderId() );
        assertEquals( "1.9.0", nexusClient.getRrsProviderVersion() );

        assertEquals( "foo", nexusClient.getUserUACustomization() );
    }

    public void testNexusPlatformUA()
    {
        // when nexus "reaches" to something else than proxy repo, like LVO
        final String nexusUaStr = "Nexus/1.9.0 (Pro; Mac OS X; 10.6.6; x86_64; 1.6.22_22)";

        ClientDetectorImpl detector = lookup( ClientDetectorImpl.class );

        Client client = detector.matchBest( nexusUaStr );

        assertNotNull( client );

        assertEquals( "nexus", client.getClientFamily() );
        assertFalse( client.has( ClientSubsystem.class ) );
        assertEquals( true, client.is( ClientOsFamily.class, "mac" ) );
        assertEquals( SonatypeNexus.FAMILY, client.getClientFamily() );
        assertEquals( "1", client.getClientMajorVersionString() );
        assertEquals( "1.9.0", client.getClientFullVersionString() );

        assertEquals( true, client.is( IsStateless.class, true ) );
        assertEquals( true, client.is( IsCliTool.class, true ) );

        assertTrue( client instanceof NexusClient );

        NexusClient nexusClient = (NexusClient) client;

        assertEquals( "Pro", nexusClient.getProperty( ClientEdition.class ).stringValue() );
        assertEquals( "Mac OS X", nexusClient.getProperty( JavaOsName.class ).stringValue() );
        assertEquals( "10.6.6", nexusClient.getProperty( JavaOsVersion.class ).stringValue() );
        assertEquals( "x86_64", nexusClient.getProperty( JavaOsArch.class ).stringValue() );
        assertEquals( "1.6.22_22", nexusClient.getProperty( JavaVersion.class ).stringValue() );
        assertEquals( false, nexusClient.has( JavaVendor.class ) );

        assertNull( nexusClient.getRrsProviderId() );
        assertNull( nexusClient.getRrsProviderVersion() );

        assertNull( nexusClient.getUserUACustomization() );
    }
}
