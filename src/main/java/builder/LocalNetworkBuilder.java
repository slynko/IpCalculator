package builder;

import model.Ip;
import model.Mask;
import model.Network;
import model.Subnet;

import java.util.List;

import static constant.NetworkConstants.MAX_IP_MASK;
import static validator.NetworkHelper.getMaskFrom;
import static validator.NetworkHelper.getNumericMaskFrom;

public class LocalNetworkBuilder extends NetworkBuilder
{
  public LocalNetworkBuilder(Network network)
  {
    super(network);
  }

  @Override
  public void calculateIpsAndMasks()
  {
    List<Subnet> subnets = network.getSubnets();
    for (int hostsAmount : network.getSubnetHostsAmount())
    {
      Subnet newSubnet = new Subnet();
      if (subnets.size() == 0)
      {
        newSubnet.setIp(network.getBaseIp());
      }
      else
      {
        long prevSubnetIp = subnets.get(subnets.size() - 1).getIp().getLongValue();
        Mask prevSubnetMask = subnets.get(subnets.size() - 1).getMask();

        long newSubnetIp =
            ((prevSubnetIp >> getNumericMaskFrom(prevSubnetMask.getLongValue())) + 1) << getNumericMaskFrom(
                prevSubnetMask.getLongValue());

        newSubnet.setIp(new Ip(newSubnetIp));
      }
      newSubnet.setMask(getMaskFrom(hostsAmount));
      subnets.add(newSubnet);
    }
  }

  @Override
  public void calculateBroadcasts()
  {
    for (Subnet subnet : network.getSubnets())
    {
      subnet.setBroadcast(new Ip((subnet.getMask().getLongValue() ^ MAX_IP_MASK) | subnet.getIp().getLongValue()));
    }
  }

  @Override
  public void calculateFirstAndLastHost()
  {
    for (Subnet subnet : network.getSubnets())
    {
      subnet.setFirstHostIp(new Ip(subnet.getIp().getLongValue() + 1));
      subnet.setLastHostIp(new Ip(subnet.getBroadcast().getLongValue() - 1));
    }
  }
}
