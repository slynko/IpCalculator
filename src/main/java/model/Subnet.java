package model;

import lombok.Data;

@Data
public class Subnet {
    private Ip ip;
    private Mask mask;
    private Ip broadcast;
    private Ip firstHostIp;
    private Ip lastHostIp;

    @Override
    public String toString() {
        return "ip:" + ip + " "
                + "mask:" + mask + " "
                + "broadcast:" + broadcast + " "
                + "hosts:" + firstHostIp + "-" + lastHostIp;
    }
}
