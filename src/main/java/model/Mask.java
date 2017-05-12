package model;

import static constant.IpConstants.MAX_IP_MASK;

public class Mask extends Ip {
    public Mask(String mask) {
        super(mask);
    }

    public Mask(long mask) {
        if (mask >= 0 && mask <= 32) {
            this.ip = MAX_IP_MASK << mask & MAX_IP_MASK;
        } else {
            throw new IllegalArgumentException(this.getClass().getSimpleName() + " is not correct: " + mask);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Mask(16));
    }
}
