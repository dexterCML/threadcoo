package encoo.thread.com;


import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA
 * User:Administrator
 * Date:2018/1/4
 * Time:21:04
 */

public class CallableAndFuture {
    public static void main(String[] args) {
        callbackAndFuture();
    }

    public void ThreadPoolTest(){
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for(int i=0;i<10;i++) {
            final int num = i;
            executorService.execute(new Runnable() {
                public void run() {

                    System.out.println("thread :"+Thread.currentThread().getName()+" have executed task:"+num);

                }
            });
        }
    }


    public static void callbackAndFuture(){
        ExecutorService es = Executors.newFixedThreadPool(3);
        Future<String> future = es.submit(new Callable<String>() {
            public String call() throws Exception {
                Thread.sleep(1000);
                return "caomalai";
            }
        });
        System.out.println("waiting result...");
        try {
            System.out.println("get callback:"+future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
