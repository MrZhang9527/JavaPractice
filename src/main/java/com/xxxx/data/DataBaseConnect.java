package com.xxxx.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zhang.test.controller.ReadConfig;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @Descripthon: 数据库链接工具
 * @author: MrZhang
 * @date: 2021/1/8 9:58
 */
public class DataBaseConnect {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataBaseConnect.class);

    private static String url;
    private static String username ;
    private static String password ;

    static {
        try {
            url = ReadConfig.readProperties("db.url");
            username = ReadConfig.readProperties("db.username");
            password = ReadConfig.readProperties("db.password");
        } catch (IOException e) {
            url = "";
            username = "";
            password = "";
            e.printStackTrace();
        }
    }

    private DataBaseConnect() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 返回一个数据库连接
     * @return 数据库连接
     */
    public static Connection getConnection() throws SQLException {
        // 创建一个数据库连接
        Connection connection = null;
        try
        {
            LOGGER.info("start to try connect database...");
            // 获取连接
            connection = DriverManager.getConnection(url, username, password);
            LOGGER.info(url);
            String infoString = "user name: " + username + "\t" + " password:******";
            LOGGER.info(infoString);
            LOGGER.info("database connect success!");
            return connection;
        } catch (Exception e)
        {
            if( null != connection){
                connection.close();
            }
            e.printStackTrace();
            return null;
        }
    }

}
