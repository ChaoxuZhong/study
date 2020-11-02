package com.chaoxuzhong.study.local.localDateTime;

import java.util.Set;

/**
 * 本地热点缓存接口
 */
public interface ICache {
    /**
     * 存入缓存，设置超时时间
     * @param key 键
     * @param value 值
     */
    void put(String key, Object value);

    /**
     * 获取缓存
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 获取缓存大小
     * @return
     */
    int size();


    /**
     * 是否存在缓存
     * @param key
     * @return
     */
    boolean isContain(String key);

    /**
     * 获取所有缓存的键
     * @return
     */
    Set<String> getAllKeys();

    /**
     * 删除某个缓存
     * @param key
     */
    void remove(String key);

}
