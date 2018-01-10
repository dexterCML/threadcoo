package encoo.thread.com;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by IntelliJ IDEA
 * User:Administrator
 * Date:2018/1/6
 * Time:17:39
 */

public class CacheDemo {
    public static void main(String[] args) {
        CacheMap map = new CacheMap();
        String name = map.getData("name").toString();
        System.out.println(name);
    }

    Map<String,Object> cache = new HashMap<String,Object>();
    ReadWriteLock rwl = new ReentrantReadWriteLock();

    Object getData(String key){
        rwl.readLock().lock();
        Object value = null;
        try {
            value = cache.get(key);
            if (value == null) {
                rwl.readLock().unlock();
                rwl.writeLock().lock();
                if (value == null) {
                    value = "";
                    cache.put(key, value);
                }
                rwl.writeLock().unlock();
            }
            rwl.readLock();
        }finally{
            rwl.readLock().unlock();
        }
        return value;
    }
}

class CacheMap<String,Object> extends HashMap<String,Object>{


    Map<String,Object> cache = new HashMap<String, Object>();
    ReadWriteLock rwl = new ReentrantReadWriteLock();

    public Object getData(String key){
        rwl.readLock().lock();
        Object value = null;
        try {
            value = cache.get(key);
            if (value == null) {
                rwl.readLock().unlock();
                rwl.writeLock().lock();
                if (value == null) {
                    value = (Object)"caomalai";
                    cache.put(key, value);
                }
                rwl.writeLock().unlock();
            }
            rwl.readLock().lock();
        }finally{
            rwl.readLock().unlock();
        }
        return value;
    }
}
