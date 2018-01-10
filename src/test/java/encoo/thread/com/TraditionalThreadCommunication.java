package encoo.thread.com;

/**
 * Created by IntelliJ IDEA
 * User:Administrator
 * Date:2017/12/26
 * Time:21:09
 */

public class TraditionalThreadCommunication {
    public static void main(String[] args) {
        final Business business = new Business();
        new Thread(new Runnable() {
            public void run() {
                for (int i=0;i<10;i++) {
                business.sub();
                }
            }
        }).start();
        for(int i=0;i<10;i++){
            business.main();
        }
    }
}

class Business{
    boolean isMainTurn = true;
    synchronized void sub(){
        try {
            while(isMainTurn)this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 50; i++) {
            System.out.println("sub thread:" + i);
        }
        isMainTurn = true;
        this.notify();
    }
    synchronized void main(){
        try {
            while(!isMainTurn)this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 50; i++) {
            System.out.println("main thread:" + i);
        }
        isMainTurn = false;
        this.notify();
    }

}

