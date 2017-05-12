package validator;

import static constant.NetworkConstants.IP_REGEXP;
import static constant.NetworkConstants.MAX_MASK_VALUE;
import static constant.NetworkConstants.MAX_OCTET_VALUE;
import static constant.NetworkConstants.MIN_MASK_VALUE;
import static constant.NetworkConstants.MIN_OCTET_VALUE;

public abstract class NetworkHelper
{
  public static long getIpFrom(String ipStr)
  {
    long ip = 0;
    if (ipStr.matches(IP_REGEXP))
    {
      String[] ipOctets = ipStr.split("\\.");

      long firstOctet = Integer.parseInt(ipOctets[0]);
      long secondOctet = Integer.parseInt(ipOctets[1]);
      long thirdOctet = Integer.parseInt(ipOctets[2]);
      long fourthOctet = Integer.parseInt(ipOctets[3]);

      if (isOctetValid(firstOctet) && isOctetValid(secondOctet)
          && isOctetValid(thirdOctet) && isOctetValid(fourthOctet))
      {
        ip |= (firstOctet << 24);
        ip |= (secondOctet << 16);
        ip |= (thirdOctet << 8);
        ip |= fourthOctet;
      }
      else
      {
        throw new IllegalArgumentException("Argument is not correct, one of octets is more than " + MAX_OCTET_VALUE
            + ", or less than " + MIN_OCTET_VALUE);
      }
    }
    else
    {
      throw new IllegalArgumentException("Argument is not correct: " + ip);
    }

    return ip;
  }

  public static boolean isOctetValid(long ip)
  {
    return ip <= MAX_OCTET_VALUE && ip >= MIN_OCTET_VALUE;
  }

  public static boolean isMaskValid(long mask)
  {
    return mask >= MIN_MASK_VALUE && mask <= MAX_MASK_VALUE;
  }
}
