package cn.imessay.speedtest.mybatis.type;

public enum AddressType {
    IPv4("IPv4"), IPv6("IPv6");

    private final String value;
    AddressType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
