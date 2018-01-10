package encoo.thread.com;

import org.junit.Test;

/**
 * Created by IntelliJ IDEA
 * User:Administrator
 * Date:2017/12/20
 * Time:22:10
 */

public class ThreadTest {
    //@Test
    public static void main(String[] args) {
        Thread td = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while(true){
                    System.out.println("当前线程为："+Thread.currentThread().getName());
                }
            }
        };
        td.start();
    }
}
