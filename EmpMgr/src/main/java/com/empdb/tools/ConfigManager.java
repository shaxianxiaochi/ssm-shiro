package com.empdb.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static ConfigManager configManager;
    private static Properties properties;

    private static class ConfigManagerHelper{
        private static final ConfigManager CONFIG_MANAGER = new ConfigManager();
    }

    private ConfigManager(){
        properties = new Properties();
        String configFile = "database.properties";
        InputStream inputStream = ConfigManager.class.getClassLoader().getResourceAsStream(configFile);
        try {
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取ConfigManager的实例
     * @return
     */
    public static ConfigManager getInstance(){
        configManager = ConfigManagerHelper.CONFIG_MANAGER;
        return configManager;
    }

    /**
     * 根据key获取值
     * @param key
     * @return
     */
    public static String getValue(String key){
        return properties.getProperty(key);
    }
}
