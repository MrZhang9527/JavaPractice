package zhang.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Descripthon: 多线程练习
 * @author: MrZhang
 * @date: 2020/12/31 9:12
 */
public class ThreadTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(ThreadTest.class);

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Runnable target = new MyRunnable();
        Thread newThread = new Thread(target);
        int len = 100;
        myThread.start();
        newThread.start();
        for (int i = 0; i < len; i++) {
            LOGGER.info("主线程数据：{}", i);
        }
    }
}

/**
 * 编写一个类，直接继承java.lang.Thread，重写run方法。
 */
class MyThread extends Thread{
    @Override
    public void run() {
        int len = 100;
        for (int i = 0; i < len; i++) {
            ThreadTest.LOGGER.warn("分支线程数据：{}", i);
        }
        super.run();
    }
}

/**
 * 编写一个类，实现java.lang.Runnable接口，实现run方法。
 */
class MyRunnable implements Runnable{

    @Override
    public void run() {
        int len = 100;
        for (int i = 0; i < len; i++) {
            ThreadTest.LOGGER.debug("分支线程数据：{}", i);
        }
    }
}