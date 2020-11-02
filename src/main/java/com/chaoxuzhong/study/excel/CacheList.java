package com.chaoxuzhong.study.excel;

import java.util.ArrayList;
import java.util.List;

public class CacheList {
    private static final List<SourceData> sourceList = new ArrayList<>();
    private static final List<TargetData> targetList = new ArrayList<>();

    public static List<SourceData> getSourceList() {
        return sourceList;
    }

    public static void addSourceList(SourceData data) {
        sourceList.add(data);
    }

    public static List<TargetData> getTargetList() {
        return targetList;
    }
    public static void addTargetList(TargetData target) {
        targetList.add(target);
    }


}
