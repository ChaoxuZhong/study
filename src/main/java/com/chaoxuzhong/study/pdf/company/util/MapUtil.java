package com.chaoxuzhong.study.pdf.company.util;

import cn.hutool.core.util.ReflectUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

/**
 * 对象转map
 */
@Slf4j
public class MapUtil {

    /**
     * 此工具类始终不转换成map的字段
     */
    public static final List<String> ALWAYS_NOT_TO_MAP_FIELDS = Arrays.asList("id");
    public static final List<String> EMPTY_LIST = new ArrayList<>();
    public static final String COLON = ":";

    public static HashMap<String, Object> objToMap(Object object) {
        return objToMap(object, EMPTY_LIST);
    }

    /**
     * 将object 转换成map
     * @param object 待转换对象
     * @param notToMapFields 对象中不转换的字段
     * @return
     */
    public static HashMap<String, Object> objToMap(Object object, List<String> notToMapFields) {
        Field[] fields = ReflectUtil.getFields(object.getClass());
        HashMap<String, Object> result = new HashMap<>();
        Arrays.stream(fields).forEach(field -> {
            if (notToMapField(field.getName(), notToMapFields)) {
                return;
            }
            field.setAccessible(true);
            try {
                result.put(field.getName(), field.get(object));
            } catch (IllegalAccessException e) {
                log.info("field {} getObject Error ==> {}", field.getName(), e.getMessage());
            }
        });
        return result;

    }

    /**
     * 不转换成map的域名
     * @param fieldName
     * @param notToMapFields
     * @return
     */
    private static boolean notToMapField(String fieldName, List<String> notToMapFields) {
        // 数据库主键不转换
        return notToMapFields.contains(fieldName) || ALWAYS_NOT_TO_MAP_FIELDS.contains(fieldName);
    }

    /**
     * map key 后缀添加
     * @param map
     * @param suffix
     * @return
     */
    public static HashMap<String, Object> appendKeysSuffix(HashMap<String, Object> map, String suffix) {
        return repalceKeys(map, (k) -> k.concat(COLON).concat(suffix));
    }

    /**
     * map key 添加前缀
     * @param map
     * @param prefix
     * @return
     */
    public static HashMap<String, Object> appendKeysPrefix(HashMap<String,Object> map, String prefix) {
        return repalceKeys(map, (k) -> prefix.concat(COLON).concat(k));
    }

    public static HashMap<String, Object> repalceKeys(HashMap<String,Object> map, Function<String,String> keyModifyfunction) {
        HashMap<String, Object> result = new HashMap<>();
        map.forEach((k, v)->{
            String newK = keyModifyfunction.apply(k);
            result.put(newK, v);
        });
        return result;
    }
}
