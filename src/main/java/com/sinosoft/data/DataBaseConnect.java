package com.sinosoft.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final String URL = "jdbc:oracle:thin:@//10.136.100.25:1521/tkpsmdev";
    private static final String USERNAME = "fcsm";
    private static final String PASSWORD = "fcsm";

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
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            LOGGER.info(URL);
            String infoString = "user name: " + USERNAME + "\t" + " password:******";
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
