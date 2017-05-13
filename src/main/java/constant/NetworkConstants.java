package constant;

public abstract class NetworkConstants {
    public static final String IP_REGEXP = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";

    public static final short MAX_OCTET_VALUE = 255;
    public static final short MIN_OCTET_VALUE = 0;

    public static final byte MAX_MASK_VALUE = 32;
    public static final byte MIN_MASK_VALUE = 0;

    public static final long FIRST_OCTET_MASK = 0b00000000_00000000_00000000_00000000_11111111_00000000_00000000_00000000L;
    public static final long SECOND_OCTET_MASK = 0b00000000_00000000_00000000_00000000_00000000_11111111_00000000_00000000L;
    public static final long THIRD_OCTET_MASK = 0b00000000_00000000_00000000_00000000_00000000_00000000_11111111_00000000L;
    public static final long FOURTH_OCTET_MASK = 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_11111111L;

    public static final long MAX_IP_MASK = 0b00000000_00000000_00000000_00000000_11111111_11111111_11111111_11111111L;
}
