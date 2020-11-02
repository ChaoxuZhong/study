package com.chaoxuzhong.study.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.StringUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 生成结果
 */
public class GenerateResult {

    private Logger logger = LoggerFactory.getLogger(GenerateResult.class);

    List<ResultData> list = new ArrayList<ResultData>();

    public static void main(String[] args) {
        GenerateResult generateResult = new GenerateResult();
        generateResult.matchRule();

    }

    /**
     * 匹配规则
     */
    public void matchRule() {
        String sourceFilePath = "/Users/chaoxu/Documents/Octane_backlog items_selected_2020_10_1___10_15_36.xlsx";
        String targetFilePath = "/Users/chaoxu/Documents/targetData.xlsx";
        SourceDateReader sourceDateReader = new SourceDateReader();
        TargetDataReader targetDataReader = new TargetDataReader();
        sourceDateReader.simpleRead(sourceFilePath);
        targetDataReader.simpleRead(targetFilePath);
        List<SourceData> sourceDataList = CacheList.getSourceList();
        List<TargetData> targetDataList = CacheList.getTargetList();
        AtomicInteger i = new AtomicInteger();
        sourceDataList.stream().forEach(s -> {
            logger.info("第{}条记录正在执行", i.getAndIncrement());
            if (StringUtils.isEmpty(s.getNumber())) {
                logger.info("sourceDate number 为空");
                String desc = "number不存在；";
                ResultData data = new ResultData();
                data.setId(s.getId());
                data.setId(s.getNumber());
                data.setLanguage(s.getLanguage());
                data.setHasAttachment(s.getHasAttachments());
                data.setDescription(s.getDescription());
                data.setDesc(desc);
                list.add(data);
                return;
            }
            AtomicInteger j = new AtomicInteger();
            AtomicBoolean matchFlag = new AtomicBoolean(false);
            AtomicReference<TargetData> targetData = new AtomicReference<>(new TargetData());
            String desc = new String();
            targetDataList.stream().forEach(t -> {
//                logger.info("内层 第{}条记录正在执行", j.getAndIncrement());
                if (s.getNumber().equals(t.getNumber())) {
                    targetData.set(t);
                    matchFlag.set(true);
                }
            });
            ResultData data = new ResultData();
            if (matchFlag.get()) {
                desc = desc + genDescription(s, targetData.get());
                if (StringUtils.isEmpty(targetData.get().getId())) {
                    desc = desc + "target Id为空，无法匹配；";
                }else{
                    data.setIsMatch(""+IsMatch.Levenshtein(s.getStringId().replace("\"",""), targetData.get().getId()));
                }
            } else {
                desc = desc + "无可以匹配的number；";
            }
            data.setId(s.getId());
            data.setNumber(s.getNumber());
            data.setLanguage(s.getLanguage());
            data.setHasAttachment(s.getHasAttachments());
            data.setDescription(s.getDescription());
            data.setDesc(desc);
            data.setSourceStringId(s.getStringId());
            data.setTargetId(targetData.get().getId());
            list.add(data);
//            try {
//                logger.info("");
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//                logger.error("sleep Error = {}", e);
//            }
        });

        simpleWrite();
    }

    public void simpleWrite() {
        // 写法2
        String fileName = "/Users/chaoxu/Documents/result.xlsx";
        ;
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(fileName, ResultData.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            excelWriter.write(list, writeSheet);
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }
    }


    private String genDescription(SourceData sourceData, TargetData targetData) {
        String desc = new String();
        if (StringUtils.isEmpty(sourceData.getHasAttachments())) {
            desc = desc + "hasAttachment 不等于yes";
        }
        boolean descriptionFlag = false;
        if (StringUtils.isEmpty(containsNum(sourceData.getDescription()))) {
            desc = desc + "description未填写；";
            descriptionFlag = true;
        }
        if ("One place".equals(sourceData.getChangeVolumn())) {
            if (descriptionFlag) {

            } else {
                if (containsNum(sourceData.getDescription())) {
                    desc = desc + "One place情况下：description中含有数字，请检查是否有多条";
                }
                if (containFind(sourceData.getDescription())) {
                    // 注意查看是否有found find
                    desc = desc + "One place情况下：description中含有found find bundle bind attach html png，请检查是否有未找到的情况";
                }
            }
        }
        if ("Multiple places".equals(sourceData.getChangeVolumn())) {
            if (descriptionFlag) {

            } else {

            }
        }
        return desc;
    }

    private boolean containFind(String string) {
        if (string.contains("found")) {
            return true;
        }
        if (string.contains("find")) {
            return true;
        }
        if (string.contains("bundle")) {
            return true;
        }
        if (string.contains("bind")) {
            return true;
        }
        if (string.contains("html")) {
            return true;
        }
        if (string.contains("attach")) {
            return true;
        }
        if (string.contains("png")) {
            return true;
        }
        return false;
    }

    private boolean containsNum(String string) {
        if (string.contains("0")) {
            return true;
        }
        if (string.contains("1")) {
            return true;
        }
        if (string.contains("2")) {
            return true;
        }
        if (string.contains("3")) {
            return true;
        }
        if (string.contains("4")) {
            return true;
        }
        if (string.contains("5")) {
            return true;
        }
        if (string.contains("6")) {
            return true;
        }
        if (string.contains("7")) {
            return true;
        }
        if (string.contains("8")) {
            return true;
        }
        if (string.contains("9")) {
            return true;
        }
        return false;
    }
}
