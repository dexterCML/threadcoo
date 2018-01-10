package encoo.thread.com;

/**
 * Created by IntelliJ IDEA
 * User:Administrator
 * Date:2017/12/30
 * Time:12:55
 */

public class MultiThreadShareData {
    public static void main(String[] args) {
        for(int i=0;i<50;i++){
            new Thread().start();

        }
    }
}
