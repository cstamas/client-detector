package org.sonatype.http.client.detector;

import org.sonatype.guice.bean.containers.InjectedTestCase;
import org.sonatype.http.client.detector.properties.IsStateless;
import org.sonatype.http.client.detector.properties.IsCliTool;
import org.sonatype.http.client.detector.ua.SonatypeNexus;
import org.sonatype.http.client.detector.ua.SonatypeNexus.NexusClient;

public class SimpleDetectorTest
    extends InjectedTestCase
{
    public void testNexus()
    {
        // when nexus proxy repo reaches out (RRS presence shows that)
        final String nexusUaStr = "Nexus/1.9.0 (OSS; Mac OS X; 10.6.6; x86_64; 1.6.22_22) httpClient/1.9.0 foo";

        Detector detector = lookup( Detector.class );

        Client client = detector.matchBest( nexusUaStr );

        assertNotNull( client );

        assertEquals( "mac", client.getClientOsFamily() );
        assertEquals( SonatypeNexus.FAMILY, client.getClientFamily() );
        assertEquals( "1", client.getClientMajorVersionString() );
        assertEquals( "1.9.0", client.getClientFullVersionString() );
        assertEquals( 1.0f, client.getScore() );

        assertEquals( true, client.hasProperty( IsStateless.class ) );
        assertEquals( true, client.hasProperty( IsCliTool.class ) );

        assertTrue( client instanceof NexusClient );

        NexusClient nexusClient = (NexusClient) client;

        assertEquals( "OSS", nexusClient.getEdition() );
        assertEquals( "Mac OS X", nexusClient.getOsName() );
        assertEquals( "10.6.6", nexusClient.getOsVersion() );
        assertEquals( "x86_64", nexusClient.getOsArch() );
        assertEquals( "1.6.22_22", nexusClient.getJavaVersion() );

        assertEquals( "httpClient", nexusClient.getRrsProviderId() );
        assertEquals( "1.9.0", nexusClient.getRrsProviderVersion() );

        assertEquals( "foo", nexusClient.getCustomization() );
    }

    public void testNexus1()
    {
        // when nexus "reaches" to something else than proxy repo, like LVO
        final String nexusUaStr = "Nexus/1.9.0 (OSS; Mac OS X; 10.6.6; x86_64; 1.6.22_22)";

        Detector detector = lookup( Detector.class );

        Client client = detector.matchBest( nexusUaStr );

        assertNotNull( client );

        assertEquals( "mac", client.getClientOsFamily() );
        assertEquals( SonatypeNexus.FAMILY, client.getClientFamily() );
        assertEquals( "1", client.getClientMajorVersionString() );
        assertEquals( "1.9.0", client.getClientFullVersionString() );
        assertEquals( 1.0f, client.getScore() );

        assertEquals( true, client.hasProperty( IsStateless.class ) );
        assertEquals( true, client.hasProperty( IsCliTool.class ) );

        assertTrue( client instanceof NexusClient );

        NexusClient nexusClient = (NexusClient) client;

        assertEquals( "OSS", nexusClient.getEdition() );
        assertEquals( "Mac OS X", nexusClient.getOsName() );
        assertEquals( "10.6.6", nexusClient.getOsVersion() );
        assertEquals( "x86_64", nexusClient.getOsArch() );
        assertEquals( "1.6.22_22", nexusClient.getJavaVersion() );

        assertNull( nexusClient.getRrsProviderId() );
        assertNull( nexusClient.getRrsProviderVersion() );

        assertNull( nexusClient.getCustomization() );
    }
}
