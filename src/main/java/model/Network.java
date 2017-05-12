package model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


@Data
public class Network
{
  private List<Integer> subnetHostsAmount;
  private Ip baseIp;
  private Mask baseMask;
  private List<Subnet> subnets;

  public Network(List<Integer> subnetHostsAmount, String baseIp, int baseMask)
  {
    subnetHostsAmount.sort(Comparator.reverseOrder());
    this.subnetHostsAmount = subnetHostsAmount;
    this.baseIp = new Ip(baseIp);
    this.baseMask = new Mask(baseMask);
    this.subnets = new ArrayList<>();
  }
}
