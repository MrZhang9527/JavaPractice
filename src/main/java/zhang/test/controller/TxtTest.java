package zhang.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * @Descripthon: 文本文件处理
 * @author: MrZhang
 * @date: 2021/1/19 16:12
 */
public class TxtTest {
    public static final Logger LOGGER = LoggerFactory.getLogger(TxtTest.class);

    public static final String ONE = "prompt Importing table";
    public static final String TWO = "set feedback off";
    public static final String THREE = "set define off";
    public static final String FOUR = "prompt Done.";


    public static List<String> readFile(String filePath){
        StringBuilder str = new StringBuilder();
        // 绝对路径或相对路径都可以，写入文件时演示相对路径,读取以上路径的input.txt文件
        // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw
        // 不关闭文件会导致资源的泄露，读写文件都同理
        // Java7的try-with-resources可以优雅关闭文件，异常时自动关闭文件；详细解读https://stackoverflow.com/a/12665271
        try (FileReader reader = new FileReader(filePath);
             // 建立一个对象，它把文件内容转成计算机能读懂的语言
             BufferedReader br = new BufferedReader(reader)
        ) {
            String line;
            //网友推荐更加简洁的写法
            while ((line = br.readLine()) != null) {
                // 一次读入一行数据
                // 无效数据 核心 空 分隔符
                if (line.contains(ONE)||line.contains(TWO)||line.contains(THREE)||line.contains(FOUR)||"".equals(line)){
                    LOGGER.info(line);
                    continue;
                }
                str.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Arrays.asList(str.toString().split(";"));
    }

    public static void writeFile(String filePath, String context){
        try {
            // 相对路径，如果没有则要建立一个新的文件
            File writeName = new File(filePath);
            // 创建新文件,有同名的文件的话直接覆盖
            if (!writeName.createNewFile()) {
                throw new IOException("创建文件失败");
            }
            // FileWriter writer = new FileWriter(writeName);
            // BufferedWriter out = new BufferedWriter(writer);
            try (
                 Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(writeName), "ANSI"))
            ) {
                out.write(context);
                out.flush(); // 把缓存区内容压入文件
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String string = "测试中文乱码";
        String endPath = "C:\\Users\\MrZhang\\Desktop\\deal\\end.txt";
        writeFile(endPath, string);
        // List<String> strings= readFile(filePath);
        // for (String str: strings) {
        //     LOGGER.warn(str);
        // }
        // User user = new User();

    }
}
