package com.chaoxuzhong.study.local.localDateTime;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CatchWorkingDayUtil implements ICache {

    // 本类实例对象
    private static volatile CatchWorkingDayUtil instance;

    // 容器初始化大小  初始化为30，每天清理的情况下等价于一个月的缓存时间
    private static final int CATCH_INITIAL_SIZE = 30;

    // 缓存容器定义
    private static ConcurrentHashMap<String, Long> cacheMap;

    private CatchWorkingDayUtil() {
        cacheMap = new ConcurrentHashMap<>(CATCH_INITIAL_SIZE);
    }

    /**
     * 双重检校锁获取实例对象
     * @return
     */
    public static CatchWorkingDayUtil getInstance() {
        if (instance == null) {
            synchronized (CatchWorkingDayUtil.class) {
                if (instance == null) {
                    instance = new CatchWorkingDayUtil();
                }
                return instance;
            }
        }
        return instance;
    }

    /**
     * 清除缓存
     */
    public void clearCache(){
        cacheMap.clear();
    }

//    public void putAll(String key, List<Integer> values) {
//        if (isContain(key)) {
//            return;
//        }else{
//            cacheMap.putAll(key, (Integer) value);
//        }
//    }

    @Override
    public void put(String key, Object value) {
        if (isContain(key)) {
            return;
        }else{
            cacheMap.put(key, (Long) value);
        }
    }

    @Override
    public Object get(String key) {
        if (isContain(key)) {
            return cacheMap.get(key);
        }
        return null;
    }

    @Override
    public int size() {
        return cacheMap.size();
    }

    @Override
    public boolean isContain(String key) {
        return cacheMap.containsKey(key);
    }

    @Override
    public Set<String> getAllKeys() {
        return cacheMap.keySet();
    }

    @Override
    public void remove(String key) {
        cacheMap.remove(key);
    }
}
