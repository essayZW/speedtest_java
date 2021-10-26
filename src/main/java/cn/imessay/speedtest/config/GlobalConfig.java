package cn.imessay.speedtest.config;

import cn.imessay.speedtest.service.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

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
    public final static String MODEL_USER_KEY = "CURRENT_USER";

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


    /**
     * 用户session的过期时间
     */
    public static Long USER_SESSION_EXPIRE_SECONDS = 86400L;


    /**
     * 该应用在redis中存储的全局配置项的前缀
     */
    public final static String GLOBAL_CONFIG_PREFIX = "SPEEDTEST_GLOBAL_CONFIG_";


    /**
     * 管理员密码
     */
    public static String ADMIN_PASSWORD = "password";

    /**
     * 管理员session在redis中的key
     */
    public final static String ADMIN_SESSION_KEY = "ADMIN_SESSION";

    /**
     * 管理员SESSION存储在cookie中的key值
     */
    public static String ADMIN_SESSION_NAME = "ASESSION_ID";

    /**
     * 是否允许用户自己注册账户
     */
    public static Boolean ALLOW_USER_REGISTER = true;

    /**
     * 该web应用的名称
     */
    public static String WEBAPP_NAME = "Speedtest";


    /**
     * CAS验证方面
     */

    /**
     * CAS验证中心地址
     */
    public static String CAS_CENTER_ADDRESS = "";

    /**
     * 是否启用CAS验证
     */
    public static Boolean ENABLE_CAS_LOGIN = false;

    /**
     * 用户username在cas验证返回的xml中的节点名称
     */
    public static String USERNAME_ELEMENT_NAME = "";

    /**
     * CAS登录接口地址
     */
    public static String CAS_LOGIN_PATH = "";
    /**
     * CAS登出接口地址
     */
    public static String CAS_LOGOUT_PATH = "";
    /**
     * CAS验证接口地址
     */
    public static String CAS_TICKET_VALIDATE_PATH = "";

    private static RedisService redisService;

    public static void setRedisService(RedisService redisService) {
        GlobalConfig.redisService = redisService;
    }

    private static final Logger logger = LoggerFactory.getLogger(GlobalConfig.class);

    /**
     * 从redis中初始化配置
     */
    public static void initConfig() {
        Class<GlobalConfig> configClass = GlobalConfig.class;
        Field[] fields = configClass.getFields();
        int allCount = 0;
        int successCount = 0;
        int skipCount = 0;
        for (Field field : fields) {
            // 因为只有静态公共字段是配置项，所以简单过滤一下
            if (!Modifier.isStatic(field.getModifiers())
                    ||!Modifier.isPublic(field.getModifiers())
                    || Modifier.isFinal(field.getModifiers())) {
                continue;
            }
            allCount ++;
            Object value = redisService.get(GlobalConfig.GLOBAL_CONFIG_PREFIX + field.getName());
            if (value != null) {
                try {
                    field.set(null, value);
                    successCount ++;
                } catch (IllegalAccessException | IllegalArgumentException e) {
                    logger.warn("GlobalConfig Init Error||{}||{}||{}", field.getName(), value.getClass().getName(), value.toString());
                }
            }
            else {
                skipCount ++;
            }
        }
        logger.info("GlobalConfig Status: success: {}, all: {}, skip: {}", successCount, allCount, skipCount);
    }

    /**
     * 设置配置的值，并同步到redis中
     * @param field 字段值
     * @param value 新的值
     */
    public static boolean set(Field field, Object value) {
         // 将缓存设置和字段设置合并为一个原子操作
         // 避免缓存中的值和当前内存中的值不一致
        synchronized (field.getName().intern()) {
            boolean status = redisService.set(GLOBAL_CONFIG_PREFIX + field.getName(), value);
            if (status) {
                try {
                    field.set(null, value);
                }
                catch (IllegalAccessException e) {
                    status = false;
                }
            }
            return status;
        }
    }
}
