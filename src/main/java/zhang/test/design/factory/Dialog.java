package zhang.test.design.factory;

/**
 * @Descripthon: 抽象类
 * @author: MrZhang
 * @date: 2021/3/26 11:34
 */
public interface Dialog {
    default void renderWindow(){
        Button button = createButton();
        button.render();
    }

    Button createButton();
}
