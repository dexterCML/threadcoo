package encoo.thread.com;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by IntelliJ IDEA
 * User:Administrator
 * Date:2017/12/24
 * Time:12:42
 */

public class TraditionalTimerTest {
    public static void main(String[] args) {
        System.out.println("2秒后出发定时器。。。");
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("我是定时器触发后的结果。");
            }
        }, 2000,1000);
    }
}
