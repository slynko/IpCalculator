package model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static constant.NetworkConstants.MAX_IP_MASK;

@Data
public class Network
{
  private List<Integer> subnetHostsAmount;
  private Ip baseIp;
  private Mask baseMask;
  private List<Subnet> subnetworks;

  public Network(List<Integer> subnetHostsAmount, Ip baseIp, Mask baseMask)
  {
    subnetHostsAmount.sort(Comparator.reverseOrder());
    this.subnetHostsAmount = subnetHostsAmount;
    this.baseIp = baseIp;
    this.baseMask = baseMask;
    this.subnetworks = new ArrayList<>();
  }

  public Network(List<Integer> subnetHostsAmount, String baseIp, int baseMask)
  {
    subnetHostsAmount.sort(Comparator.reverseOrder());
    this.subnetHostsAmount = subnetHostsAmount;
    this.baseIp = new Ip(baseIp);
    this.baseMask = new Mask(baseMask);
    this.subnetworks = new ArrayList<>();
  }

  public Network(List<Integer> subnetHostsAmount, String baseIp, String baseMask)
  {
    subnetHostsAmount.sort(Comparator.reverseOrder());
    this.subnetHostsAmount = subnetHostsAmount;
    this.baseIp = new Ip(baseIp);
    this.baseMask = new Mask(baseMask);
    this.subnetworks = new ArrayList<>();
  }

  public class NetworkCalculator
  {

    private Network network;

    public NetworkCalculator(Network network)
    {
      this.network = network;
    }

    public Mask calculateMask(int hostsAmount)
    {
      int mask = 0;
      for (int i = 1; i <= hostsAmount; i <<= 1)
      {
        mask++;
      }
      return new Mask(mask);
    }

    public void calculateSubnetsIps()
    {
      for (int hostsAmount : subnetHostsAmount)
      {
        Subnet newSubnet;
        if (subnetworks.size() == 0)
        {
          newSubnet = new Subnet(network.getBaseIp(), calculateMask(hostsAmount));
        }
        else
        {
          long prevSubnetIp = subnetworks.get(subnetworks.size() - 1).getIp().getLongValue();
          Mask prevSubnetMask = subnetworks.get(subnetworks.size() - 1).getMask();

          Mask newSubnetMask = calculateMask(hostsAmount);
          long newSubnetIp = ((prevSubnetIp >> prevSubnetMask.getIntMask()) + 1) << prevSubnetMask.getIntMask();

          newSubnet = new Subnet(new Ip(newSubnetIp), newSubnetMask);
        }
        subnetworks.add(newSubnet);
      }
    }

    public void calculateSubnetBroadcasts()
    {
      for (Subnet subnet: subnetworks) {
        subnet.setBroadcast(new Ip((subnet.getMask().getLongValue() ^ MAX_IP_MASK) | subnet.getIp().getLongValue()));
      }
    }

    public void calculateFirstAndLastHost()
    {
      for (Subnet subnet: subnetworks) {
        subnet.setFirstHostIp(new Ip(subnet.getIp().getLongValue() + 1));
        subnet.setLastHostIp(new Ip(subnet.getBroadcast().getLongValue() - 1));
      }
    }
  }


  public static void main(String[] args)
  {
    List<Integer> subnetHostsAmount = Arrays.asList(
        125, 125, 125, 125, 125, 125
        , 50, 50, 50, 50, 50, 50
        , 30, 30, 30, 30, 30
        , 15, 15, 15
        , 7, 7
    );

    System.out.println(subnetHostsAmount);
    Network network = new Network(subnetHostsAmount, "10.0.0.0", 16);
    NetworkCalculator networkCalculator = network.new NetworkCalculator(network);
    networkCalculator.calculateSubnetsIps();
    networkCalculator.calculateSubnetBroadcasts();
    networkCalculator.calculateFirstAndLastHost();
    System.out.println(network.getSubnetworks());
  }
}
