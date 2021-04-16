package zhang.test.controller;

/**
 * @Descripthon: 位运算练习
 * @author: MrZhang
 * @date: 2021/4/14 14:24
 */
public class BitOperation {
    public static void main(String[] args) {
        int a = 1;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(-a));
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println(Integer.toBinaryString(2147483647));
        System.out.println(a >>> 2);
        System.out.println(a >> 2);
        System.out.println(a << 2);
    }
}
