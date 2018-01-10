package encoo.thread.com;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by IntelliJ IDEA
 * User:Administrator
 * Date:2018/1/6
 * Time:22:20
 */

public class ConditionCommunication {
    public static void main(String[] args) {
    final Process process = new Process();
        new Thread(new Runnable() {
            public void run() {
                for(int i=0;i<50;i++){
                    process.sub(i);
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                for(int i=0;i<50;i++){
                    process.subson(i);
                }
            }
        }).start();

        for(int i=0;i<50;i++) {
            process.main(i);
        }
    }
    static class Process {
        private Lock lock = new ReentrantLock();
        private Condition condition1 = lock.newCondition();
        private Condition condition2 = lock.newCondition();
        private Condition condition3 = lock.newCondition();
        int runNum = 1;
        public void sub(int loop){
            lock.lock();
            while(runNum!=2){
                try {
                    condition2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for(int i=0;i<10;i++){
                System.out.println("sub thread each times: "+i+" loop for: "+loop);
            }
            runNum=3;
            condition3.signal();
            lock.unlock();
        }

        public void subson(int loop){
            lock.lock();
            while(runNum!=3){
                try {
                    condition3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for(int i=0;i<10;i++){
                System.out.println("subson thread each times: "+i+" loop for: "+loop);
            }
            runNum=1;
            condition1.signal();
            lock.unlock();
        }

        public void main(int loop){
            lock.lock();
            while(runNum!=1){
                try {
                    condition1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for(int i=0;i<10;i++){
                System.out.println("main thread each times: "+i+" loop for: "+loop);
            }
            runNum=2;
            condition2.signal();
            lock.unlock();
        }
    }
}



