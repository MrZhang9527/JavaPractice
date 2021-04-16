package zhang.test.design.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * 设计模式 - 工厂模式
 * 1. 定义 产品 的通用接口 - Button
 * 2. 定义多种具体产品实现 Button 接口 - HtmlButton(产品1),WindowsButton(产品2)
 * 3. 定义 工厂 的通用接口 - Dialog
 * 4. 定义具体产品工厂实现 Dialog 接口 - HtmlDialog(生产产品1),WindowsDialog(生产产品2)
 * @Descripthon: 客户端代码
 * @author: MrZhang
 * @date: 2021/3/26 11:39
 */
public class Demo {
    static final Logger logger = LoggerFactory.getLogger(Demo.class.toString());
    static final Marker MARKER = MarkerFactory.getMarker(Demo.class.toString());
    public static final String WINDOWS_8_1 = "Windows 8.1";
    public static final String OS_NAME = "os.name";
    private static Dialog dialog;

    public static void main(String[] args) {
        configure();
        runBusinessLogic();
        logger.info(logger.getName());
    }

    static void configure(){
        if (WINDOWS_8_1.equals(System.getProperty(OS_NAME))) {
            dialog = new WindowsDialog();
        }else {
            dialog = new HtmlDialog();
        }
    }

    static void runBusinessLogic(){
        dialog.renderWindow();
    }
}
