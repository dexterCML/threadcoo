package encoo.thread.com;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by IntelliJ IDEA
 * User:Administrator
 * Date:2018/1/6
 * Time:16:30
 */

public class ReadWriteLockTest {
    public static void main(String[] args) {
        final Queues data = new Queues();
        ExecutorService es = Executors.newCachedThreadPool();
        for(int i=0;i<3;i++ ){
            es.execute(new Runnable() {
                public void run() {
                    data.read();
                }
            });
            es.execute(new Runnable() {
                public void run() {
                    Integer num = new Random().nextInt(1000);
                    data.write(num);
                }
            });
        }
    }


}

class Queues{
    private Object data=null;
    ReadWriteLock rwLock = new ReentrantReadWriteLock();
    void read(){
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+" has ready to read data ...");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName()+" had read data: "+data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            rwLock.readLock().unlock();
        }
    }

    void write(Object data){
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+" has ready to write data ...");
            Thread.sleep(2000);
            this.data = data;
            System.out.println(Thread.currentThread().getName()+" had write data: "+data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            rwLock.writeLock().unlock();
        }
    }
}
