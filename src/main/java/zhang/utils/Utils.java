package zhang.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Descripthon: 工具类
 * @author: MrZhang
 * @date: 2021/4/19 15:00
 */
public class Utils {
    public static final Logger logger = LoggerFactory.getLogger(Utils.class);
    private Utils(){throw new IllegalStateException();}
    public static void print(String str){
        logger.info(str);
    }
}
