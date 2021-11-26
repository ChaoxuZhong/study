package com.chaoxuzhong.study.pdf.company.util;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class MapUtilTest {


    @Test
    public void objectToMapReturnAllFieldMapTest() {
        // GIVEN
        ObjectToMapTestDto objectToMapTestDto = new ObjectToMapTestDto();
        Integer integer = Integer.valueOf("123");
        objectToMapTestDto.setInteger(integer);
        BigDecimal one = BigDecimal.ONE;
        objectToMapTestDto.setBigDecimal(one);
        String string = "String";
        objectToMapTestDto.setString(string);
        Long id = 10L;
        objectToMapTestDto.setId(id);
        String notToMap = "notToMap";
        objectToMapTestDto.setNotToMap(notToMap);
        List<String> notToMaps = Arrays.asList(notToMap);

        // WHEN
        HashMap<String, Object> map = MapUtil.objToMap(objectToMapTestDto, notToMaps);

        // THEN
        Assert.assertTrue(integer == map.get("integer"));
        Assert.assertTrue(one == map.get("bigDecimal"));
        Assert.assertTrue(string == map.get("string"));
        Assert.assertNull(map.get("id"));
        Assert.assertNull(map.get("notToMap"));
    }

    @Test
    public void appendKeysSuffixTest(){
        // GIVEN
        HashMap<String, Object> testMap = new HashMap<>();
        Object objectOne = new Object();
        Object objectTwo = new Object();
        Object objectThree = new Object();
        testMap.put("key1", objectOne);
        testMap.put("key2", objectTwo);
        testMap.put("key3", objectThree);
        // WHEN
        testMap = MapUtil.appendKeysSuffix(testMap, "01");
        // THEN
        Assert.assertTrue(testMap.get("key1:01") == objectOne);
        Assert.assertTrue(testMap.get("key2:01") == objectTwo);
        Assert.assertTrue(testMap.get("key3:01") == objectThree);
    }

    @Test
    public void appendKeysprefixTest(){
        // GIVEN
        HashMap<String, Object> testMap = new HashMap<>();
        Object objectOne = new Object();
        Object objectTwo = new Object();
        Object objectThree = new Object();
        testMap.put("key1", objectOne);
        testMap.put("key2", objectTwo);
        testMap.put("key3", objectThree);
        // WHEN
        testMap = MapUtil.appendKeysPrefix(testMap, "prefix");
        // THEN
        Assert.assertTrue(testMap.get("prefix:key1") == objectOne);
        Assert.assertTrue(testMap.get("prefix:key2") == objectTwo);
        Assert.assertTrue(testMap.get("prefix:key3") == objectThree);
    }
}