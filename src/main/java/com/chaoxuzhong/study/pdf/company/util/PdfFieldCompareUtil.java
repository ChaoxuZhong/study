package com.chaoxuzhong.study.pdf.company.util;

import com.chaoxuzhong.study.pdf.company.pojo.FieldTypeEnums;
import com.chaoxuzhong.study.pdf.company.vo.PdfFieldVo;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.fields.PdfField;
import com.spire.pdf.widget.PdfCheckBoxWidgetFieldWidget;
import com.spire.pdf.widget.PdfFormFieldWidgetCollection;
import com.spire.pdf.widget.PdfFormWidget;
import com.spire.pdf.widget.PdfTextBoxFieldWidget;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

@Slf4j
public class PdfFieldCompareUtil {

    public static final String EMPTY = "EMPTY";
    public static final String CHECK_TRUE = "true";
    public static final String CHECK_FALSE = "false";

    public static void comparePdf(String sourcePath, String comparePath) {
        // 待比较的pdf域获取
        PdfDocument soruceDoc = new PdfDocument();
        soruceDoc.loadFromFile(sourcePath);
        PdfFormWidget form = (PdfFormWidget) soruceDoc.getForm();
        PdfFormFieldWidgetCollection formWidgetCollection = form.getFieldsWidget();
        HashMap<String, PdfFieldVo> sourceMap = getVoValueMap(formWidgetCollection);
        soruceDoc.close();

        // 用于比较的pdf的域获取
        PdfDocument forCompareDoc = new PdfDocument();
        forCompareDoc.loadFromFile(comparePath);
        PdfFormWidget formForCompare = (PdfFormWidget) forCompareDoc.getForm();
        PdfFormFieldWidgetCollection forCompareWidget = formForCompare.getFieldsWidget();
        HashMap<String, PdfFieldVo> forCompareMap = getVoValueMap(forCompareWidget);
        forCompareDoc.close();
        log.info("开始进行匹配：sourcePath = {}, comparePath = {}", sourcePath, comparePath);
        doCompareAndLog(sourceMap, forCompareMap);
    }

    private static void doCompareAndLog(HashMap<String, PdfFieldVo> sourceMap, HashMap<String, PdfFieldVo> forCompareMap) {
        ArrayList<LogMsg> nonCheckMatchList = new ArrayList<>();
        ArrayList<LogMsg> matchList = new ArrayList<>();
        ArrayList<LogMsg> notMatchList = new ArrayList<>();
        ArrayList<LogMsg> emptyList = new ArrayList<>();
        sourceMap.forEach((k,v)->{
            PdfFieldVo forCompareVo = forCompareMap.get(k);
            if (forCompareVo == null) {
                notMatchList.add(new LogMsg(k, v.getV(), "ERROR==> 测试文件中找不到对应域:%s"));
            }else if (forCompareVo.getType() != v.getType()) {
                notMatchList.add(new LogMsg(k, v.getV(), "ERROR==> 域类型不匹配::%s"));
            } else if (forCompareVo.getV() == EMPTY && v.getV() == EMPTY) {
                emptyList.add(new LogMsg(k, v.getV(), "ERROR==> 测试对比文件及生成文件都为空::%s"));
            } else if (forCompareVo.getV().equals(v.getV())) {
                if (v.getV() == CHECK_FALSE) {
                    nonCheckMatchList.add(new LogMsg(k, v.getV(), "复选框未勾选，直接MATCH==> key:%s"));
                }else{
                    matchList.add(new LogMsg(k, v.getV(), "SUCCESS MATCH==> key:%s | v:%s"));
                }
            }else{
                notMatchList.add(new LogMsg(k, v.getV(), forCompareVo.getV(), "ERROR==> 对应值不匹配k=%s | sourceV=%s |forCompareV=%s"));
            }
        });
        log.info("—————————————————————————————————匹配的字段数{},具体信息如下———————————————————————————————————", matchList.size());
        showLog(matchList);
        log.info("————————————————————————————————复选框未勾选匹配数{},具体信息如下————————————————————————————————————", nonCheckMatchList.size());
        showLog(nonCheckMatchList);
        log.info("—————————————————————————————————不匹配的字段数{},具体信息如下———————————————————————————————————", notMatchList.size());
        showLog(notMatchList);
        log.info("———————————————————————————————模板和测试比较模板均未填写的字段数{},具体信息如下—————————————————————————————————————", emptyList.size());
        showLog(emptyList);
    }

    private static void showLog(ArrayList<LogMsg> logList) {
        logList = (ArrayList<LogMsg>) logList.stream().sorted().collect(Collectors.toList());
        logList.forEach(logMsg -> {
            log.info(String.format(logMsg.getMsgTemplate(), logMsg.getKey(), logMsg.getValue(), logMsg.getTestValue()));
        });
    }

    private static HashMap<String,PdfFieldVo> getVoValueMap(PdfFormFieldWidgetCollection formWidgetCollection) {
        HashMap<String, PdfFieldVo> result = new HashMap<>();
        for (int i = 0; i < formWidgetCollection.getCount(); i++) {
            PdfField field = formWidgetCollection.get(i);
            String name = field.getName();
            if (field instanceof PdfTextBoxFieldWidget) {
                String v = ((PdfTextBoxFieldWidget) field).getText();
                if (StringUtils.isEmpty(v)) {
                    v = EMPTY;
                }
                result.put(name, new PdfFieldVo(FieldTypeEnums.TEXT, v));
            }
            if (field instanceof PdfCheckBoxWidgetFieldWidget) {
                result.put(name, new PdfFieldVo(FieldTypeEnums.CHECKBOX, ((PdfCheckBoxWidgetFieldWidget) field).getChecked() ? CHECK_TRUE : CHECK_FALSE));
            }
        }
        return result;
    }

    @Data
    public static class LogMsg implements Comparable<LogMsg>{
        private String key;
        private String value;
        private String testValue;
        private String msgTemplate;

        public LogMsg(String key, String value, String msgTemplate) {
            this.key = key;
            this.value = value;
            this.msgTemplate = msgTemplate;
        }

        public LogMsg(String key, String value, String testValue, String msgTemplate) {
            this.key = key;
            this.value = value;
            this.testValue = testValue;
            this.msgTemplate = msgTemplate;
        }

        @Override
        public int compareTo(LogMsg o) {
            return key.compareTo(o.getKey());
        }

    }


}
