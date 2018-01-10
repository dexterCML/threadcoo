package encoo.thread.com;

import java.util.Random;

/**
 * Created by IntelliJ IDEA
 * User:Administrator
 * Date:2017/12/27
 * Time:21:30
 */

public class ThreadLocalTest {
    private static int  data = 0;
    private static ThreadLocal<Integer> threadlocal = new ThreadLocal<Integer>();
    public static void main(String[] args) {
        for(int i=0;i<2;i++){
            new Thread(new Runnable() {
                public void run() {
                    int data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName()+" has put value: "+data);
                    /* threadlocal.set(data);
                    new Aprocess().get();
                    new Bprocess().get();*/
                   Persion.getThreadInstance().setName("caomalai"+data);
                   Persion.getThreadInstance().setAge(data);
                   Persion.getThreadInstance().setSex("boy"+data);
                   new Aprocess().get();
                   new Bprocess().get();
                }
            }).start();
        }
    }
    static class Aprocess{
        void get(){
            Persion.getThreadInstance().toPrint();
            //System.out.println("A get data from "+Thread.currentThread().getName()+" value:"+threadlocal.get());
        }
    }

    static class Bprocess{
       void get(){
           Persion.getThreadInstance().toPrint();
           //System.out.println("B get data from "+Thread.currentThread().getName()+" value:"+threadlocal.get());
       }
    }
}

class Persion{
    private String name;
    private int age;
    private String sex;

    private static Persion persion = null;

    private static ThreadLocal<Persion> threadLocal = new ThreadLocal<Persion>();
    public Persion(){}
    public static Persion getThreadInstance(){
        if(threadLocal.get()==null){
            threadLocal.set(new Persion());
        }
        return threadLocal.get();
    }

    public void toPrint(){
        System.out.println("线程："+Thread.currentThread().getName()+", get Persion info: name "+name+", age "
                +age+", sex "+sex);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

