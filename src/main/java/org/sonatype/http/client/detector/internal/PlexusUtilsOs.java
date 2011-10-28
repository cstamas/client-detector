package org.sonatype.http.client.detector.internal;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

/**
 * Some methods providing compatibility for plexus-utils without being dependent on it.
 * This code is copy+pasted from (org.codehaus.plexus : plexus-utils : 2.0.5) to not depent on PU, but still,
 * provide "loosely coupled" common compatibility wrt "OS families".
 * 
 * @author cstamas
 */
public class PlexusUtilsOs
{
    // define the families for easier reference
    public static final String FAMILY_DOS = "dos";

    public static final String FAMILY_MAC = "mac";

    public static final String FAMILY_NETWARE = "netware";

    public static final String FAMILY_OS2 = "os/2";

    public static final String FAMILY_TANDEM = "tandem";

    public static final String FAMILY_UNIX = "unix";

    public static final String FAMILY_WINDOWS = "windows";

    public static final String FAMILY_WIN9X = "win9x";

    public static final String FAMILY_ZOS = "z/os";

    public static final String FAMILY_OS400 = "os/400";

    public static final String FAMILY_OPENVMS = "openvms";

    // store the valid families
    private static final Set<String> validFamilies = setValidFamilies();

    /**
     * Initializes the set of valid families.
     */
    private static Set<String> setValidFamilies()
    {
        Set<String> valid = new HashSet<String>();
        valid.add( FAMILY_DOS );
        valid.add( FAMILY_MAC );
        valid.add( FAMILY_NETWARE );
        valid.add( FAMILY_OS2 );
        valid.add( FAMILY_TANDEM );
        valid.add( FAMILY_UNIX );
        valid.add( FAMILY_WINDOWS );
        valid.add( FAMILY_WIN9X );
        valid.add( FAMILY_ZOS );
        valid.add( FAMILY_OS400 );
        valid.add( FAMILY_OPENVMS );

        return valid;
    }

    private static String getOsFamily( final String osName )
    {
        // in case the order of static initialization is
        // wrong, get the list
        // safely.
        Set<String> families = null;
        if ( !validFamilies.isEmpty() )
        {
            families = validFamilies;
        }
        else
        {
            families = setValidFamilies();
        }
        Iterator<String> iter = families.iterator();
        while ( iter.hasNext() )
        {
            String fam = iter.next();
            if ( isFamily( fam, osName ) )
            {
                return fam;
            }
        }
        return null;
    }

    public static boolean isFamily( final String family, final String osName )
    {
        boolean retValue = false;

        if ( family != null )
        {
            boolean isFamily = true;

            if ( family != null )
            {
                if ( family.equalsIgnoreCase( FAMILY_WINDOWS ) )
                {
                    isFamily = osName.indexOf( FAMILY_WINDOWS ) > -1;
                }
                else if ( family.equalsIgnoreCase( FAMILY_OS2 ) )
                {
                    isFamily = osName.indexOf( FAMILY_OS2 ) > -1;
                }
                else if ( family.equalsIgnoreCase( FAMILY_NETWARE ) )
                {
                    isFamily = osName.indexOf( FAMILY_NETWARE ) > -1;
                }
                else if ( family.equalsIgnoreCase( FAMILY_DOS ) )
                {
                    isFamily = osName.equals( ";" ) && !isFamily( FAMILY_NETWARE, osName );
                }
                else if ( family.equalsIgnoreCase( FAMILY_MAC ) )
                {
                    isFamily = osName.indexOf( FAMILY_MAC ) > -1;
                }
                else if ( family.equalsIgnoreCase( FAMILY_TANDEM ) )
                {
                    isFamily = osName.indexOf( "nonstop_kernel" ) > -1;
                }
                else if ( family.equalsIgnoreCase( FAMILY_UNIX ) )
                {
                    // Original, but we have no access to Path Separator
                    // isFamily =
                    // PATH_SEP.equals( ":" ) && !isFamily( FAMILY_OPENVMS, osName )
                    // && ( !isFamily( FAMILY_MAC, osName ) || osName.endsWith( "x" ) );
                    isFamily =
                        !isFamily( FAMILY_OPENVMS, osName )
                            && ( !isFamily( FAMILY_MAC, osName ) || osName.endsWith( "x" ) );
                }
                else if ( family.equalsIgnoreCase( FAMILY_WIN9X ) )
                {
                    isFamily =
                        isFamily( FAMILY_WINDOWS, osName )
                            && ( osName.indexOf( "95" ) >= 0 || osName.indexOf( "98" ) >= 0
                                || osName.indexOf( "me" ) >= 0 || osName.indexOf( "ce" ) >= 0 );
                }
                else if ( family.equalsIgnoreCase( FAMILY_ZOS ) )
                {
                    isFamily = osName.indexOf( FAMILY_ZOS ) > -1 || osName.indexOf( "os/390" ) > -1;
                }
                else if ( family.equalsIgnoreCase( FAMILY_OS400 ) )
                {
                    isFamily = osName.indexOf( FAMILY_OS400 ) > -1;
                }
                else if ( family.equalsIgnoreCase( FAMILY_OPENVMS ) )
                {
                    isFamily = osName.indexOf( FAMILY_OPENVMS ) > -1;
                }
                else
                {
                    isFamily = osName.indexOf( family.toLowerCase( Locale.US ) ) > -1;
                }
            }

            retValue = isFamily;
        }
        return retValue;
    }

    /**
     * *Some UA string contains complete values of {@code System.getProperty( "os.name" )}, and this method "normalizes"
     * that value is same set of values as used in Plexus Utilities, to allow at least coarse platform matching.
     * 
     * @param osName
     * @return
     */
    public static String getPUOsFamilyFromJavaOsNameSystemProperties( final String osName )
    {
        return getOsFamily( osName.toLowerCase( Locale.US ) );
    }

}
