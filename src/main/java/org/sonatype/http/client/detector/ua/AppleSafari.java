package org.sonatype.http.client.detector.ua;

import javax.inject.Named;
import javax.inject.Singleton;

import org.sonatype.http.client.detector.AbstractClientMatcher;
import org.sonatype.http.client.detector.Client;

@Named
@Singleton
public class AppleSafari
    extends AbstractClientMatcher
{
    public Client match( final String UAString )
    {
        // TODO Auto-generated method stub
        return null;
    }
}