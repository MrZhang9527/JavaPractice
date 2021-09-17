package zhang.test.controller;

import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.Logger;
import org.slf4j.MarkerFactory;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Descripthon: Log4j测试
 * @author: MrZhang
 * @date: 2020/12/30 11:27
 */
public class Log4jTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(Log4jTest.class);
    private static final Marker MARKER = MarkerFactory.getMarker(Log4jTest.class.toString());

    public static void main(String[] args) {
        logTests("1");
    }

    public static void logTests(String msg){
        LOGGER.trace(MARKER, "{} {}", msg, "11");
        LOGGER.debug(msg);
        LOGGER.info(msg);
        LOGGER.info(MARKER, "{} {}", msg, "11");
        LOGGER.warn(msg);
        LOGGER.error(msg);
        LOGGER.info(null);
    }

    public static void addSelf(){
        int a=0;
        int b=0;
        int c=0;
        int d=0;

        a++;
        b = b++;
        c = ++c;
        ++d;
        LOGGER.info("{}", a);
        LOGGER.info("{}", b);
        LOGGER.info("{}", c);
        LOGGER.info("{}", d);
        LOGGER.info("测试");
    }

    public static void mapTest(){
        Map<String,Object> codeMap = new HashMap<>(1);
        codeMap.put("1", 11);
        codeMap.put("2", 12);
        codeMap.put("3", 13);
        codeMap.put("4", 14);
        codeMap.forEach((k, v)->LOGGER.info(String.valueOf(v)));

        List<String> codeList = new ArrayList<>();
        codeList.add("1");
        codeList.add("2");
        codeList.add("3");
        codeList.add("4");
        codeList.add("5");
        codeList.forEach(LOGGER::error);

        codeList.stream().filter(v -> v.contains("2")).forEach(LOGGER::warn);
    }


}
