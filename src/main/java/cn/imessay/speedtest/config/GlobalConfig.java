package cn.imessay.speedtest.config;

public class GlobalConfig {

    /**
     * ip地址查询API最长的等待时间
     */
    public static Integer MAX_IP_API_AWAIT_TIME = 3;

    /**
     * IP 地址信息查询API地址
     */
    public static String IP_API_ADDRESS = "http://ip-api.com/json/";

    /**
     * AOP切面中放入model中的用户信息
     */
    public static String MODEL_USER_KEY = "CURRENT_USER";

    /**
     * SESSION 在cookie中的键值
     */
    public static String SESSION_ID_NAME = "SESSION_ID";

    /**
     * 未登录后的重定向地址
     */
    public static String NOT_LOGIN_REDIRECT_URL = "/login";


    /**
     * 存储在redis中的sessionid的前缀
     */
    public static String USER_SESSION_KEY_PREFIX = "APP_USER_SESSION_";
}
