package ch.supsi.myproj.utils;

import java.io.IOException;
import java.util.Properties;

public class AppInfoLoader {

    public static Properties getAppInfo(){
        final Properties properties = new Properties();
        try {
            properties.load(AppInfoLoader.class.getClassLoader().getResourceAsStream("pom.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return properties;
    }


}
