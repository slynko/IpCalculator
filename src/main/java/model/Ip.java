package model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import static constant.IpConstants.*;

public class Ip {
    protected long ip;

    protected Ip() {
        // do nothing
    }

    public Ip(String ip) {
        if (ip.matches(IP_PATTERN)) {
            initializeOctets(ip);
        } else {
            throw new IllegalArgumentException(this.getClass().getSimpleName() + " is not correct: " + ip);
        }
    }

    protected void initializeOctets(String ip) {
        String[] ipOctets = ip.split("\\.");

        long firstOctet = Integer.parseInt(ipOctets[0]);
        long secondOctet = Integer.parseInt(ipOctets[1]);
        long thirdOctet = Integer.parseInt(ipOctets[2]);
        long fourthOctet = Integer.parseInt(ipOctets[3]);

        if (firstOctet > MAX_OCTET_VALUE || secondOctet > MAX_OCTET_VALUE
                || thirdOctet > MAX_OCTET_VALUE || fourthOctet > MAX_OCTET_VALUE) {
            throw new IllegalArgumentException(this.getClass().getSimpleName() +
                    " is not correct, one of octets is more than " + MAX_OCTET_VALUE);
        }

        this.ip |= (firstOctet << 24);
        this.ip |= (secondOctet << 16);
        this.ip |= (thirdOctet << 8);
        this.ip |= fourthOctet;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append((ip & FIRST_OCTET_MASK) >> 24).append(".")
                .append((ip & SECOND_OCTET_MASK) >> 16).append(".")
                .append((ip & THIRD_OCTET_MASK) >> 8).append(".")
                .append(ip & FOURTH_OCTET_MASK)
                .toString();
    }

    public static void main(String[] args) {
        System.out.println(new Ip("192.168.0.1").toString());
    }
}
