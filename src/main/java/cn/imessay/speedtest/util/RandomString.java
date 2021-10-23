package cn.imessay.speedtest.util;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class RandomString {
    private final static String dict;
    static {
        dict = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    }
    public static String generate(int byteSize) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        while ((byteSize --) != 0) {
            builder.append(dict.charAt(random.nextInt() & 63));
        }
        return builder.toString();
    }


    public static byte[] generateByte(int byteSize) {
        return  generate(byteSize).getBytes(StandardCharsets.UTF_8);
    }
}
