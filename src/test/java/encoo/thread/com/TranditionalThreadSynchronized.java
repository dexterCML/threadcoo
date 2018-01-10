package encoo.thread.com;

import com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory;

/**
 * Created by IntelliJ IDEA
 * User:Administrator
 * Date:2017/12/25
 * Time:21:42
 */

public class TranditionalThreadSynchronized {
    public static void main(String[] args) {
       new TranditionalThreadSynchronized().init();

    }

    void init(){
        final Outputer outputer = new Outputer();
        new Thread(new Runnable() {
            public void run() {
                while(true){
                    outputer.output("HelloWorld");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                while(true){
                    outputer.output2("HelloRedis");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
   static class Outputer{
        void output(String name){
            synchronized (Outputer.class) {
                for (int i = 0; i < name.length(); i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
            }
        }

        static synchronized  void  output2(String name){
            for (int i = 0; i < name.length(); i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println();
        }
    }
}
