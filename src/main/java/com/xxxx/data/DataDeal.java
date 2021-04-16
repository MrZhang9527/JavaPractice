package com.xxxx.data;

import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.LoggerFactory;
import org.slf4j.MarkerFactory;

import java.io.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Descripthon: 处理insert数据
 * @author: MrZhang
 * @date: 2021/1/8 9:58
 */
public class DataDeal {
    private static final SimpleDateFormat SDF =   new SimpleDateFormat( "yyyy/MM/dd");
    private static final String VALUES = " values ";
    private static final Logger LOGGER = LoggerFactory.getLogger(DataDeal.class);
    private static final Marker MARKER = MarkerFactory.getMarker(DataDeal.class.toString());
    /**
     * 读取文件
     * @param file 文件
     * @return 文件列表
     */
    public static List<String> readTxt(File file) throws IOException {
        List<String> list = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
            String lineTxt;
            while ((lineTxt = br.readLine()) != null) {
                if (!"".equals(lineTxt)) {
                    list.add(lineTxt);
                }
            }
            br.close();
        } catch (Exception e) {
            if (null != br){
                br.close();
            }
            LOGGER.error(MARKER, "read errors :{}", e.toString());
        }

        return list;
    }

    /**
     * 处理txt脚本文件夹
     * @param folderPath 文件夹路径
     */
    public static void dealTxtScriptFolder(String folderPath) {
        File foldr = new File(folderPath);
        if (foldr.exists() && foldr.isDirectory()) {
            File[] file = foldr.listFiles();
            assert file != null;
            for (File f : file) {
                if (f.exists() && f.isFile()) {
                    LOGGER.info(MARKER, "start deal: {}", f.getAbsolutePath());
                    // dealTxtScriptPro(f);
                    LOGGER.info(MARKER, "end deal: {}", f.getAbsolutePath());
                }
            }
        }
    }

    /**
     * 处理文件脚本
     * @param file 文件
     */
    public static void dealTxtScript(File file) {
        long startTime = System.currentTimeMillis();
        int batchSize = 0;
        int count = 1;
        Statement stat = null;
        Connection conn = null;
        int dealCount = 10;
        try {
            // 获取游标
            conn = DataBaseConnect.getConnection();
            assert conn != null;
            conn.setAutoCommit(false);
            stat = conn.createStatement();

            // 读取文件
            List<String> list = readTxt(file);
            for (String sql : list) {
                stat.addBatch(sql);
                batchSize ++;
                if (batchSize == dealCount) {
                    long startExe = System.currentTimeMillis();
                    try {
                        stat.executeBatch();
                        conn.commit();
                        long endExe = System.currentTimeMillis();
                        LOGGER.info(MARKER, "第 {} 次，处理 {} 条sql语句提交所耗时间：{}", count, dealCount, consumingTime(startExe, endExe));
                    } catch (Exception e) {
                        conn.rollback();
                        LOGGER.info(MARKER, "第 {} 次，处理 {} 条sql语句时出现错误：{}", count, dealCount, e.getMessage());
                        LOGGER.info(MARKER, "出错的sql语句范围：{}行 - {}行", count * dealCount - dealCount, count * dealCount);
                    }
                    // 重置
                    batchSize = 0;
                    stat.clearBatch();
                    count ++;
                }

            }

            stat.executeBatch();
            conn.commit();

        } catch (SQLException | IOException e) {
            try {
                assert conn != null;
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            LOGGER.error("程序异常终止");
            e.printStackTrace();
        } finally {

            long endTime = System.currentTimeMillis();
            LOGGER.info(MARKER, "该文件处理所耗时间：{}", consumingTime(startTime, endTime));
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 处理文件脚本
     * @param list 文件
     */
    public static void dealTxtScriptPro(List<String> list) {
        long startTime = System.currentTimeMillis();
        int batchSize = 0;
        int count = 1;
        String[] values;
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        int dealCount = 10;
        try {
            // 读取文件
            // List<String> list = readTxt(file);
            String sqlText = getSqlTemplate(list.get(0));
            List<Integer> nums = getDateIndex(list.get(0));
            // 获取游标
            conn = DataBaseConnect.getConnection();
            assert conn != null;
            preparedStatement = conn.prepareStatement(sqlText);
            conn.setAutoCommit(false);
            for (String sql : list) {
                values = getValues(sql);
                for (int i = 0; i < values.length; i++) {
                    if(nums.contains(i)){
                        if("".equals(values[i])){
                            preparedStatement.setDate(i+1, null);
                        }else {
                            preparedStatement.setDate(i+1, new java.sql.Date(SDF.parse(values[i]).getTime()));
                        }

                    }else {
                        preparedStatement.setObject(i+1, values[i]);
                    }
                }
                preparedStatement.addBatch();
                batchSize ++;
                if (batchSize == dealCount) {
                    long startExe = System.currentTimeMillis();
                    try {
                        preparedStatement.executeBatch();
                        conn.commit();
                        long endExe = System.currentTimeMillis();
                        LOGGER.info(MARKER, "第 {} 次，处理 {} 条sql语句提交所耗时间：{}", count, dealCount, consumingTime(startExe, endExe));
                    } catch (Exception e) {
                        conn.rollback();
                        LOGGER.info(MARKER, "第 {} 次，处理 {} 条sql语句时出现错误：{}", count, dealCount, e.getMessage());
                        LOGGER.info(MARKER, "出错的sql语句范围：{}行 - {}行", count * dealCount - dealCount, count * dealCount);
                    }
                    // 重置
                    batchSize = 0;
                    preparedStatement.clearBatch();
                    count ++;
                }
            }
            preparedStatement.executeBatch();
            conn.commit();
        } catch (Exception e) {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            LOGGER.error("程序异常终止");
            e.printStackTrace();
        } finally {
            long endTime = System.currentTimeMillis();
            LOGGER.info(MARKER, "该文件处理所耗时间：{}", consumingTime(startTime, endTime));
            closeResources(conn, preparedStatement);
        }
    }

    /**
     * 关闭资源
     * @param conn 连接
     * @param preparedStatement st
     */
    public static void closeResources(Connection conn, PreparedStatement preparedStatement){
        try{
            if (preparedStatement != null&& !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 获取 insert into t() values ()
     * @param sql SQL语句
     * @return 插入模板
     */
    public static String getSqlTemplate(String sql){
        String[] temp = sql.split(VALUES);
        StringBuilder stringBuilder = new StringBuilder(temp[0]);
        stringBuilder.append(" values (");
        String values = temp[1].replace("to_date(", "").replace("'yyyy/mm/dd'),", "").replace("(", "").replace(")", "");
        LOGGER.info(values);
        String[] temp2 = values.split(",");
        for (int i = 0; i < temp2.length; i++) {
            if(i==temp2.length-1){
                stringBuilder.append("?)");
            }else {
                stringBuilder.append("?,");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 获取sql语句中值带有日期的索引
     * @param sql 语句
     * @return list index
     */
    public static List<Integer> getDateIndex(String sql){
        int counter = 0;
        List<Integer>  result = new ArrayList<>();
        String[] temp = sql.split(VALUES);
        String initValues = temp[1];
        String[] temp3 = initValues.split(",");
        for (int i = 0; i < temp3.length; i++) {
            if("'yyyy/mm/dd')".equals(temp3[i])){
                counter++;
                result.add(i-counter);
            }
        }
        return result;
    }

    /**
     * 获取 sql中的值
     * @param sql 语句
     * @return 值数组
     */
    public static String[] getValues(String sql){
        String[] temp = sql.split(VALUES);
        String values = temp[1].replace("to_date(", "").replace("'yyyy/mm/dd'),", "").replace("(", "").replace(")", "");
        String[] temp2 = values.split(",");
        String[] results = new String[temp2.length];
        for (int i = 0; i < temp2.length; i++) {
            results[i] = temp2[i].replace("'", "");
        }
        return results;
    }

    /**
     * 转换两个时间戳的差值
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 转换后的字符串
     */
    public static String consumingTime(Long startTime, Long endTime){
        DecimalFormat df = new DecimalFormat("#.00");
        int units = 60;
        String s;
        String m;
        String h;
        String smh;
        long result = (endTime - startTime);
        double seconds = result / 1000D;
        s = seconds + " 秒 ";
        smh = s;
        if (seconds >= units){
            s = df.format(seconds % units) + " 秒 ";
            long minutes = ((long)seconds) / units;
            m = minutes + " 分 ";
            smh = m + s;
            if (minutes >= units){
                m = minutes % units + " 分 ";
                long hours = minutes / units;
                h = hours + " 小时 ";
                smh = h + m + s;
            }
        }
        return smh;
    }

    public static void main(String[] args){

    }
}
