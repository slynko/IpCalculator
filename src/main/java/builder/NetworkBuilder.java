package builder;

import model.Network;

public abstract class NetworkBuilder {
    protected Network network;

    public abstract void calculateIpsAndMasks();

    public abstract void calculateBroadcasts();

    public abstract void calculateFirstAndLastHost();

    public NetworkBuilder(Network network) {
        this.network = network;
    }

    public Network getNetwork() {
        return network;
    }
}
