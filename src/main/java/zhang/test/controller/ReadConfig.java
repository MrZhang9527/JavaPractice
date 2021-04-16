package zhang.test.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 * @Descripthon: 读取配置文件
 * @author: MrZhang
 * @date: 2021/3/15 10:40
 */
public class ReadConfig {

    private static final String CONFIG_NAME = "config.properties";

    /**
     * 读取配置文件
     * @param key key值
     * @return 属性值，没有返回null值，读取的文件为：config.properties
     * @throws IOException 读取配置文件IO异常
     */
    public static String readProperties(String key) throws IOException {
        Properties properties = new Properties();
        properties.load(ReadConfig
                .class
                .getClassLoader()
                .getResourceAsStream(CONFIG_NAME));
        return properties.getProperty(key);
    }

    /**
     * 读取配置文件
     * @param key key值
     * @param filePath 文件路径
     * @return 读取指定文件的属性，没有文件会报错，没有的key值返回null值
     * @throws IOException 读取配置文件IO异常
     */
    public static String readProperties(String key, String filePath) throws IOException {
        Properties properties = new Properties();
        properties.load(ReadConfig
                .class
                .getClassLoader()
                .getResourceAsStream(filePath));
        return properties.getProperty(key);
    }

    public static boolean writeProperties(String key, String value) throws IOException {
        boolean result = true;
        Properties properties = new Properties();
        properties.setProperty(key,value);
        try {
            try(OutputStream outputStream=new FileOutputStream("ReadConfig.URL_.getPath()")){
                properties.store(outputStream,"update " + key);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(readProperties("app.root"));
        // boolean b = writeProperties("test", "test123");
        // System.out.println(b);
        // System.out.println(readProperties("test"));
        // System.out.println(readProperties("app.root"));
    }
}
