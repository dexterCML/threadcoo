package encoo.thread.com;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by IntelliJ IDEA
 * User:Administrator
 * Date:2018/1/7
 * Time:19:55
 */

public class BlockingQueueTest {
    public static void main(String[] args) {
        final BlockingQueue queue = new ArrayBlockingQueue(3);
        new Thread(new Runnable() {
            public void run () {
                int i=0;
                while(true) {
                    System.out.println("队列[3]中元素个数为：" + queue.size() + "，准备向队列中放入数据。。。");
                    try {
                        queue.put(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("数据已经插入，队列大小为："+queue.size());
                    if(i++>100)break;
                }
            }
        }).start();
        int i=0;
        while(true){
            System.out.println("队列[3]中元素个数为：" + queue.size() + "，准备向队列中取出数据。。。");
            try {
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("数据已经取出，队列大小为："+queue.size());
            if(i++>100)break;
        }
    }

}
