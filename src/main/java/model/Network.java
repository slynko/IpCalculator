package model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Data
public class Network {
    private List<Integer> subnetHostsAmount;
    private Ip baseIp;
    private Mask baseMask;
    private List<Subnet> subnets;

    public Network(List<Integer> subnetHostsAmount, String baseIp, int baseMask) {
        subnetHostsAmount.sort(Comparator.reverseOrder());
        this.subnetHostsAmount = subnetHostsAmount;
        this.baseIp = new Ip(baseIp);
        this.baseMask = new Mask(baseMask);
        this.subnets = new ArrayList<>();
    }

    public void printNetwork() {
        System.out.printf("%-5s%-20s%-20s%-20s%-20s%-20s" + System.lineSeparator(),
                "№", "Ip", "Mask", "Broadcast", "First host", "Last host");
        for (Subnet subnet : subnets) {
            System.out.printf("%-5s%-20s%-20s%-20s%-20s%-20s" + System.lineSeparator(),
                    subnets.indexOf(subnet) + 1, subnet.getIp(), subnet.getMask(), subnet.getBroadcast(), subnet.getFirstHostIp(), subnet.getLastHostIp());
        }
    }
}
