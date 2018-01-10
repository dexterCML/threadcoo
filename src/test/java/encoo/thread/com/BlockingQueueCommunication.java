package encoo.thread.com;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by IntelliJ IDEA
 * User:Administrator
 * Date:2018/1/7
 * Time:20:18
 */

public class BlockingQueueCommunication {
    static BlockingQueue mainQueue = new ArrayBlockingQueue(1);
    static BlockingQueue subQueue = new ArrayBlockingQueue(1);
    public static void main(String[] args) {
        try {
            mainQueue.put(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final Process p = new Process();
        new Thread(new Runnable() {
            public void run() {
                for(int i=0;i<100;i++) {
                    try {
                        subQueue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    p.subProcess(i);
                    try {
                        mainQueue.put(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        for(int i=0;i<100;i++){
            try {
                mainQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.mainProcess(i);
            try {
                subQueue.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    static class Process{
        void mainProcess(int loop){
            for(int i=0;i<10;i++){
                System.out.println("main thread each times: "+i+" loop for: "+loop);
            }
        }

        void subProcess(int loop){
            for(int i=0;i<10;i++){
                System.out.println("sub thread each times: "+i+" loop for: "+loop);
            }
        }
    }
}


