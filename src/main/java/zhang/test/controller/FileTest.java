package zhang.test.controller;


import java.io.*;
import java.nio.file.Files;

/**
 * @Descripthon: 文件操作
 * @author: MrZhang
 * @date: 2021/1/25 11:32
 */
public class FileTest extends BaseTest{
    private static final String FILE_CREATE_FAIL = "Failed to create file";

    private void dealDirectory(String directoryPath) throws IOException {
        File file = new File(directoryPath);
        if (file.exists() && file.isDirectory()){
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.isFile()){
                        logger.info(marker, "Start working on files: {}", f.getName());
                        String log = "test log22222";
                        logger.info(marker, "{}, End of file processing", f.getName());
                        logger.info(marker, "Start moving files to store log files");
                        move(f, log);
                        logger.info(marker, "File movement completed");
                    }
                }
            }else {
                logger.info("No files in directory");
            }

        }else {
            throw new FileNotFoundException("Directory does not exists");
        }
    }

    /**
     * 迁移文件
     * @param f 文件
     * @param log  日志内容
     * @throws IOException IO异常
     */
    private void move(File f, String log) throws IOException {
        if (f.exists() && f.isFile()){
            String parentPath = f.getParent();
            String filePath = parentPath +"\\"+ getDate();
            File toFile = new File(filePath, f.getName());

            if (toFile.exists()) {
                logger.info("文件已存在");
                Files.delete(toFile.toPath());
                logger.info("删除已存在文件");
            }
            if (null != toFile.getParentFile() && !toFile.getParentFile().isDirectory()){
                toFile.getParentFile().mkdirs();
            }
            boolean to = f.renameTo(toFile);
            if (to) {
                logger.info("文件迁移成功");
                creatLogFile(filePath, f.getName(), log);
            }else {
                logger.error("文件迁移失败");
            }
        }
    }

    /**
     * 创建日志文件
     * @param filePath 文件路径
     * @param name  文件名称
     * @param log   日志内容
     * @throws IOException IO异常
     */
    private void creatLogFile(String filePath, String name, String log) throws IOException {
        File logFile = new File(filePath, name+".log");
        if (!logFile.exists()) {
            logger.info("日志文件不存在");
            if(null != logFile.getParentFile()){
                if(logFile.createNewFile()){
                    logger.info("创建日志文件成功");
                }else {
                    logger.info("创建日志文件失败");
                    return;
                }
            }
        }
        try (FileWriter writer = new FileWriter(logFile);
             BufferedWriter out = new BufferedWriter(writer)) {
            logger.info("开始写入日志");
            out.write(log);
            out.flush(); // 把缓存区内容压入文件
            logger.info("写入日志完成");
        }
    }

    public File creatFile(String filePath, String fileName) throws IOException {
        boolean creatDir = true;
        boolean creatFile;
        File file = new File(filePath, fileName);
        if(file.exists() && file.isFile()){
            // 路径存在，且为一个文件
            logger.info("File exists");
            return file;
        }else if(file.exists() && file.isDirectory()){
            // 路径存在，但是一个目录
            throw new FileNotFoundException("The path is a directory");
        }else if(!(file.exists() && file.isFile())){
            // 判断文件是否存在
            logger.info("File does not exist");
            // 判断父目录是否存在
            if(!file.getParentFile().exists()){
                logger.info("Create the file parent directory");
                // 不存在则创建
                creatDir = file.getParentFile().mkdirs();
            }
            if (creatDir) {
                // 创建父目录成功
                logger.info("Create the file");
                creatFile = file.createNewFile();
            }else {
                // 创建父目录失败，抛出异常
                logger.error("Directory creation failed");
                throw new FileNotFoundException("Directory creation failed");
            }
            if (creatFile){
                // 创建文件成功
                logger.info("Create file successfully");
                return file;
            }else {
                // 创建文件失败，抛出异常
                throw new NullPointerException(FILE_CREATE_FAIL);
            }
        }else {
            throw new NullPointerException(FILE_CREATE_FAIL);
        }
    }

    public static FileTest creat(){
        return  new FileTest();
    }

    @org.jetbrains.annotations.NotNull
    public static String readFile(String filePath){
        StringBuilder result = new StringBuilder();
        try(FileReader reader = new FileReader(filePath);
            BufferedReader br = new BufferedReader(reader)) {
            String line;
            // 按行读取数据
            while ((line = br.readLine()) != null){
                result.append(line);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return result.toString().replace("prompt Importing table lcgrpcont...set feedback offset define off", "").replace("prompt Done.", "");
    }

    public static void main(String[] args) {
        String data = readFile("lcgrpcont.txt");
        String[] strings = data.split(";");
        String sql = strings[0];
        String sqlTemplate = sqlTemplate(sql);
        System.out.println(sqlTemplate);
        for (String string : strings) {
            System.out.println(string);
        }
        System.out.println(getValue(sql));
    }

    public static String sqlTemplate(String sql){
        String regex = ",";
        StringBuilder result = new StringBuilder();
        int start = sql.indexOf("(");
        int end = sql.indexOf(")");
        String substring = sql.substring(start+1, end);
        result.append(sql, 0, end + 1);
        result.append(" values (");
        String[] split = substring.split(regex);
        System.out.println(split.length);
        for (int i = 0; i < split.length; i++) {
            if (i==split.length - 1){
                result.append("?");
            }else {
                result.append("?, ");
            }
        }
        result.append(")");
        return result.toString();
    }

    public static String getValue(String sql){
        String[] strings = sql.split("values");
        // String[] result = [];
        String string = strings[strings.length - 1];
        int start = string.indexOf("(");
        String substring = string.substring(start+1, string.length()-1);
        String[] res = substring.split(",");
        for (String s : res) {
            if (s.contains("to_")){

            }
        }
        return substring;

    }
}
