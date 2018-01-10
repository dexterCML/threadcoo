package encoo.thread.com;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by IntelliJ IDEA
 * User:Administrator
 * Date:2018/1/7
 * Time:15:59
 */

public class MyQueue {
    private Object[] bufferQueue;
    private Lock  lock = new ReentrantLock();
    private Condition cdput = lock.newCondition();
    private Condition cdtake = lock.newCondition();
    int putptr, takeptr, count;

    MyQueue(int size){
        bufferQueue = new Object[size];
    }

    void put(Object obj){
        lock.lock();
        while(count==bufferQueue.length){
            try {
                System.out.println("队列已满，等待线程取走数据。。。");
                cdput.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        bufferQueue[putptr] = obj;
        if(++putptr==bufferQueue.length)putptr=0;
        ++count;
        System.out.println("插入数据："+obj+" 队列["+bufferQueue.length+"]中还有"+count+"条数据");
        cdtake.signal();
        lock.unlock();
    }

    Object take(){
        lock.lock();
        while(count==0){
            try {
                System.out.println("队列已空，等待线程放入数据。。。");
                cdtake.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Object backvalue = bufferQueue[takeptr];
        if(++takeptr==bufferQueue.length)takeptr=0;
        --count;
        System.out.println("取出数据："+backvalue+" 队列["+bufferQueue.length+"]中还有"+count+"条数据");
        cdput.signal();
        lock.unlock();
        return backvalue;
    }

    public static void main(String[] args) {
        final MyQueue queue  = new MyQueue(10);
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i=0;i<100;i++){
            service.execute(new Runnable() {
                public void run() {
                    queue.put(new Random().nextInt(1000));
                }
            });
        }
        for(int i=0;i<100;i++){
            service.execute(new Runnable() {
                public void run() {
                    queue.take();
                }
            });
        }
    }
}
