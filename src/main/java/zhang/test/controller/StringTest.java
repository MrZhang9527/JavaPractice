package zhang.test.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import zhang.test.entity.ApiResultVo;

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

    public static final String prefix;

    static {
        prefix = "/app";
    }

    public static void main(String[] args) {
        String text = "{ \"resp_code\": 210,\n" +
                "  " +
                "\"resp_desc\": \"success\",\n" +
                "  \"data\": {\n" +
                "    \"inActuGetNo\": 20000000000,\n" +
                "    \"outActuGetNo\": 20000000000\n" +
                "  }\n" +
                "}";
        JSONObject jsonObject = JSON.parseObject(text);

        // System.out.println(jsonObject.getJSONObject("data").getString("inActuGetNo"));
        // System.out.println(jsonObject.getJSONObject("data").getString("outActuGetNo"));
        // System.out.println(jsonObject.getJSONObject("data").getString("outActuGetNo1"));
        // System.out.println(jsonObject.getJSONObject("data1").getString("outActuGetNo"));


        ApiResultVo resultVo = JSON.toJavaObject(jsonObject, ApiResultVo.class);
        System.out.println(resultVo.getRespCode());
        System.out.println(resultVo.getRespDesc());
        // System.out.println(resultVo.getData()==null);
        // System.out.println(resultVo.getData().getInActuGetNo());
        // System.out.println(resultVo.getData().getOutActuGetNo());
        System.out.println(resultVo.getInActuGetNo());
        System.out.println(resultVo.getOutActuGetNo());
        System.out.println(resultVo.isSuccess());

        System.out.println(StringUtils.substringAfter("/app/cost/test/set",prefix));


        LOGGER.info(String.valueOf(null));

        BigDecimal bigDecimal = BigDecimal.ZERO;
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
