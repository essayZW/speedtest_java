package cn.imessay.speedtest.service.config;

import cn.imessay.speedtest.config.GlobalConfig;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
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
            if (!Modifier.isStatic(field.getModifiers()) || !Modifier.isPublic(field.getModifiers())) {
                continue;
            }
            configItems.put(field.getName(), field);
        }
    }


    public boolean set(String name, Object value) {
        if (configItems.containsKey(name)) {
            return GlobalConfig.set(configItems.get(name), value);
        }
        return false;
    }

    public Object get(String name) {
        if (configItems.containsKey(name)) {
            try {
                return configItems.get(name).get(null);
            } catch (IllegalAccessException e) {
                return null;
            }
        }
        return null;
    }


}
