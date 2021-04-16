package zhang.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Descripthon: 测试基础类
 * @author: MrZhang
 * @date: 2021/1/25 11:33
 */
public class BaseTest {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected Marker marker = MarkerFactory.getMarker(this.getClass().toString());

    protected String getDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    protected String getTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }
}
