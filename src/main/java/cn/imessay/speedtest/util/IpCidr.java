package cn.imessay.speedtest.util;

/**
 * 负责CIDR信息解析的工具类
 */
public class IpCidr {

    public String prefix;
    public int prefixLength;
    private Type type;
    private IpRange range;

    public static enum Type {
        IPv4, IPv6;
    }

    public static class IpRange {
        public String start;
        public String end;
    }
}
