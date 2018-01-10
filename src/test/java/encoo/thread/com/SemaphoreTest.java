package encoo.thread.com;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by IntelliJ IDEA
 * User:Administrator
 * Date:2018/1/7
 * Time:12:09
 */

public class SemaphoreTest {
    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        final SemaphoreInfo si = new SemaphoreInfo();
        final Semaphore sp = new Semaphore(3);
        for(int i=0;i<10;i++){
            es.execute(new Runnable() {
                public void run() {
                    si.info(sp);
                }
            });
        }
    }

}

class SemaphoreInfo{
    public void info (Semaphore sph){
        try {
            sph.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int runnum = 3-sph.availablePermits();
        System.out.println("线程" + Thread.currentThread().getName() + "进入，当前有"
                 +runnum
                 +"个线程正在运行");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程"+Thread.currentThread().getName()+"即将离开");
        sph.release();

    }
}


