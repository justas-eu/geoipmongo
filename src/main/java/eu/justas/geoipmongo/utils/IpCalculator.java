package eu.justas.geoipmongo.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author justas
 */
public class IpCalculator {

    public static Long getLong(String ipAddressString) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getByName(ipAddressString);

        long result = 0;
        for (byte b : inetAddress.getAddress()) {
            result = result << 8 | (b & 0xFF);
        }

        return result;

    }

    public static String longToIp(long longIp) {
        int octet3 = (int) ((longIp >> 24) % 256);
        int octet2 = (int) ((longIp >> 16) % 256);
        int octet1 = (int) ((longIp >> 8) % 256);
        int octet0 = (int) ((longIp) % 256);
        return octet3 + "." + octet2 + "." + octet1 + "." + octet0;
    }
}

