package zhang.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * @Descripthon: 字符串测试
 * @author: MrZhang
 * @date: 2021/3/8 14:07
 */
public class StringTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(StringTest.class);
    private static final Marker MARKER = MarkerFactory.getMarker(StringTest.class.toString());
    public static void main(String[] args) {
        BigDecimal bigDecimal = BigDecimal.ONE;
        System.out.println(bigDecimal.toString());
        System.out.println(String.valueOf(bigDecimal));
        String strA = " abcd ", strB = null;
        print(strA);
        print("");
        print(strB);
        // getLength(strA);
        // getLength("");
        // getLength(strB);
        String key = "ORA-03150";
        String content = "-3150,ORA-03150:";
        LOGGER.info(String.valueOf(content.contains(key)));
    }
    public static void print(String text) {
        // Java 8
        Optional.ofNullable(text).ifPresent(System.out::println);
        // Pre-Java 8
        if (text != null) {
            System.out.println(text);
        }
    }

    private static void change(Object object){
        System.out.println(object);
        object = "ss";
        System.out.println(object);
    }
}
