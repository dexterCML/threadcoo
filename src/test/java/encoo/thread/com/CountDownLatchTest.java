package encoo.thread.com;

import java.util.concurrent.CountDownLatch;

/**
 * Created by IntelliJ IDEA
 * User:Administrator
 * Date:2018/1/7
 * Time:14:16
 */

public class CountDownLatchTest {
    public static void main(String[] args) {
        final CountDownLatch cdl = new CountDownLatch(1);
        final CountDownLatch cdlAnswer = new CountDownLatch(3);
        for(int i=0;i<3;i++) {
            new Thread() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " 正准备接受指令。。。");
                    try {
                        cdl.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " 已接收到响应指令，继续运行。。。");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    cdlAnswer.countDown();
                }
            }.start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行指令准备发出。。。");
        cdl.countDown();
        try {
            cdlAnswer.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程等待子线程执行回应。。。");
        System.out.println("接受到子线程的回应，任务结束");

    }

}
