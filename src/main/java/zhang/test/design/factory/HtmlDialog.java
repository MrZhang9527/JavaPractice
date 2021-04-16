package zhang.test.design.factory;

/**
 * @Descripthon: 具体创建者
 * @author: MrZhang
 * @date: 2021/3/26 11:36
 */
public class HtmlDialog implements Dialog {
    @Override
    public Button createButton() {
        return new HtmlButton();
    }
}
