package model;

import lombok.Data;

@Data
public class Subnet
{
  private Ip ip;
  private Mask mask;
  private Ip broadcast;
  private Ip firstHostIp;
  private Ip lastHostIp;

  public Subnet(Ip ip, Mask mask)
  {
    this.ip = ip;
    this.mask = mask;
  }

  public Subnet(String ip, String mask)
  {
    this.ip = new Ip(ip);
    this.mask = new Mask(mask);
  }

  public Subnet(String ip, long mask)
  {
    this.ip = new Ip(ip);
    this.mask = new Mask(mask);
  }

  @Override
  public String toString()
  {
    return new StringBuilder()
        .append("ip:").append(ip).append(" ")
        .append("mask:").append(mask).append(" ")
        .append("broadcast:").append(broadcast).append(" ")
        .append("hosts:").append(firstHostIp).append("-").append(lastHostIp)
        .toString();
  }
}
