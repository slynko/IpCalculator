
import builder.LocalNetworkBuilder;
import builder.NetworkDirector;
import model.Network;

import java.util.Arrays;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        String baseIp = "10.0.0.0";
        int baseMask = 16;
        List<Integer> subnetHostsAmount = Arrays.asList(
                125, 125, 125, 125, 125, 125
                , 50, 50, 50, 50, 50, 50
                , 30, 30, 30, 30, 30
                , 15, 15, 15
                , 7, 7
        );

        Network network = new Network(subnetHostsAmount, baseIp, baseMask);
        NetworkDirector networkDirector = new NetworkDirector();
        networkDirector.buildNetwork(new LocalNetworkBuilder(network));
        network.printNetwork();
    }
}
