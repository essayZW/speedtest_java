package cn.imessay.speedtest.service.config;

import cn.imessay.speedtest.config.GlobalConfig;
import cn.imessay.speedtest.exception.InvalidConfigNameException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

@Service
public class GlobalConfigService {

    private Map<String, Field> configItems = new HashMap<>();


    @PostConstruct
    private void init() {
        Field[] fields = GlobalConfig.class.getFields();
        for (Field field : fields) {
            if (!Modifier.isStatic(field.getModifiers()) || !Modifier.isPublic(field.getModifiers()) ||
                Modifier.isFinal(field.getModifiers())) {
                continue;
            }
            configItems.put(field.getName(), field);
        }
    }


    public boolean set(String name, Object value) {
        name = name.toUpperCase();
        if (configItems.containsKey(name)) {
            Field field = configItems.get(name);
            Class<?> fieldClass = field.getType();
            if (fieldClass == String.class) {
                return GlobalConfig.set(field, value);
            }
            // 将string类型通过反射转化为对应字段的类型
            Method method = null;
            try {
                method = fieldClass.getMethod("valueOf", String.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                return false;
            }
            Object newValue;
            try {
                newValue = method.invoke(null, value);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                return false;
            }
            return GlobalConfig.set(field, newValue);
        }
        return false;
    }

    public Object get(String name) throws InvalidConfigNameException {
        name = name.toUpperCase();
        if (configItems.containsKey(name)) {
            try {
                return configItems.get(name).get(null);
            } catch (IllegalAccessException e) {
                return null;
            }
        }
        throw new InvalidConfigNameException(name + " not exists");
    }


}
