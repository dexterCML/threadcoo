package encoo.thread.com;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by IntelliJ IDEA
 * User:Administrator
 * Date:2018/1/7
 * Time:12:59
 */

public class CyclicBarrierTest {
    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        final CyclicBarrier cb = new CyclicBarrier(3);
        for(int i=0;i<3;i++){
            es.execute(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep((long)Math.random()*10000);

                        System.out.println("线程"+ Thread.currentThread().getName()+
                            "已到达集合点，当前已有"+String.valueOf(cb.getNumberWaiting()+1)+"到达，正在等待。。。");
                        cb.await();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
