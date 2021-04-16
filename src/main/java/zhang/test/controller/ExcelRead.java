package zhang.test.controller;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Descripthon: excel文件读取
 * @author: MrZhang
 * @date: 2021/1/22 11:10
 */
public class ExcelRead {
    public static final Logger LOGGER = LoggerFactory.getLogger(ExcelRead.class);
    public static final Marker MARKER = MarkerFactory.getMarker(ExcelRead.class.getName());

    static void readXlsx(String path) {
        try{
            // 读取的时候可以使用流，也可以直接使用文件名
            FileInputStream is = new FileInputStream(path);
            Workbook xwb = new XSSFWorkbook(is);
            Sheet sheet = xwb.getSheetAt(0);
            if(sheet == null) {
                return;
            }
            for(int numRow = 0; numRow <= sheet.getLastRowNum(); numRow++) {
                Row row = sheet.getRow(numRow);
                if(row == null) {
                    continue;
                }
                // 循环cell
                for(int numCell = 0; numCell < row.getLastCellNum(); numCell++) {
                    Cell cell = row.getCell(numCell);
                    if(cell == null) {
                        continue;
                    }
                    // 打印数据
                    LOGGER.info(MARKER, "xlsx表格中读取的数据:{}", getValue(cell));
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static String getValue(Cell cell) {
        String val = null;
        switch(cell.getCellType()) {
            // 公式类型
            case Cell.CELL_TYPE_FORMULA:
                val = String.valueOf(cell.getStringCellValue());
                break;
            // 布尔类型
            case Cell.CELL_TYPE_BOOLEAN:
                val = String.valueOf(cell.getBooleanCellValue());
                break;
            // 字符串类型
            case Cell.CELL_TYPE_STRING:
                val = cell.getStringCellValue().trim();
                break;
            // 数值类型
            case Cell.CELL_TYPE_NUMERIC:
                // 日期格式
                if(DateUtil.isCellDateFormatted(cell)) {
                    if (cell.getNumericCellValue() > 1){
                        val =   dateToString(cell.getDateCellValue(), "yyyy-MM-dd");
                    }else{
                        val =   dateToString(cell.getDateCellValue(), "HH:mm:ss");
                    }

                }else {
                    // 四舍五入
                    val = new DecimalFormat("#.####").format(cell.getNumericCellValue());
                }
                break;
            default: //其它类型
                break;
        }
        return val;
    }
    public static String dateToString(Date date, String format){
        // Date -> LocalDateTime -> String
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(),zone);
        return df.format(localDateTime);
    }

    public static void main(String[] args) {
        String filePath = "C:\\Users\\MrZhang\\Desktop\\test.xlsx";
        readXlsx(filePath);
    }
}
