package zhang.test.design.factory;

/**
 * @Descripthon: 产品 1
 * @author: MrZhang
 * @date: 2021/3/26 11:25
 */
public class HtmlButton implements Button{
    @Override
    public void render() {
        System.out.println("<button>this is a html button</button>");
        onClick();
    }

    @Override
    public void onClick() {
        System.out.println("this html button on click");
    }
}
