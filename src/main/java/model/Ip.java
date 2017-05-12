package model;

import static constant.NetworkConstants.*;
import static validator.NetworkHelper.getIpFrom;

public class Ip
{
  protected long ip;

  public Ip(long ip)
  {
    this.ip = ip;
  }

  public Ip(String ip)
  {
    this.ip = getIpFrom(ip);
  }

  @Override
  public String toString()
  {
    return ((ip & FIRST_OCTET_MASK) >> 24) + "."
        +((ip & SECOND_OCTET_MASK) >> 16) + "."
        + ((ip & THIRD_OCTET_MASK) >> 8) + "."
        + (ip & FOURTH_OCTET_MASK);
  }

  public long getLongValue()
  {
    return ip;
  }
}
