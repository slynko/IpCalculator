package builder;

import model.Network;

public class NetworkDirector {
    public Network buildNetwork(NetworkBuilder builder) {
        builder.calculateIpsAndMasks();
        builder.calculateBroadcasts();
        builder.calculateFirstAndLastHost();
        return builder.getNetwork();
    }
}
