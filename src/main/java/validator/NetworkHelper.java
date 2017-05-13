package validator;

import model.Mask;

import static constant.NetworkConstants.IP_REGEXP;
import static constant.NetworkConstants.MAX_IP_MASK;
import static constant.NetworkConstants.MAX_MASK_VALUE;
import static constant.NetworkConstants.MAX_OCTET_VALUE;
import static constant.NetworkConstants.MIN_MASK_VALUE;
import static constant.NetworkConstants.MIN_OCTET_VALUE;

public abstract class NetworkHelper {
    public static long getIpFrom(String ipStr) {
        long ip = 0;
        if (ipStr.matches(IP_REGEXP)) {
            String[] ipOctets = ipStr.split("\\.");

            long firstOctet = Integer.parseInt(ipOctets[0]);
            long secondOctet = Integer.parseInt(ipOctets[1]);
            long thirdOctet = Integer.parseInt(ipOctets[2]);
            long fourthOctet = Integer.parseInt(ipOctets[3]);

            if (isOctetValid(firstOctet) && isOctetValid(secondOctet)
                    && isOctetValid(thirdOctet) && isOctetValid(fourthOctet)) {
                ip |= (firstOctet << 24);
                ip |= (secondOctet << 16);
                ip |= (thirdOctet << 8);
                ip |= fourthOctet;
            } else {
                throw new IllegalArgumentException("Argument is not correct, one of octets is more than " + MAX_OCTET_VALUE
                        + ", or less than " + MIN_OCTET_VALUE);
            }
        } else {
            throw new IllegalArgumentException("Argument is not correct: " + ip);
        }

        return ip;
    }

    public static long getMaskFrom(long numericMask) {
        if (isMaskValid(numericMask)) {
            return MAX_IP_MASK << numericMask & MAX_IP_MASK;
        } else {
            throw new IllegalArgumentException("Argument is not correct: " + numericMask);
        }
    }

    public static long getNumericMaskFrom(long mask) {
        int intMask = 1;
        while (((mask <<= 1) & MAX_IP_MASK) > 0) {
            intMask++;
        }
        return 32 - intMask;
    }

    public static Mask getMaskFrom(int hostsAmount) {
        int mask = 0;
        for (int i = 1; i <= hostsAmount; i <<= 1) {
            mask++;
        }
        return new Mask(mask);
    }

    public static boolean isOctetValid(long ip) {
        return ip <= MAX_OCTET_VALUE && ip >= MIN_OCTET_VALUE;
    }

    public static boolean isMaskValid(long mask) {
        return mask >= MIN_MASK_VALUE && mask <= MAX_MASK_VALUE;
    }
}
