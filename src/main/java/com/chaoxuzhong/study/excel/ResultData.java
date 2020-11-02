package com.chaoxuzhong.study.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class ResultData {
    @ExcelProperty("系统id")
    private String id;
    @ExcelProperty("number")
    private String number;
    @ExcelProperty("错误描述")
    private String desc;
    @ExcelProperty("language")
    private String language;
    @ExcelProperty("hasAttachment")
    private String hasAttachment; //需要时 yes
    @ExcelProperty("description")
    private String description;
    @ExcelProperty("源stringId")
    private String sourceStringId;
    @ExcelProperty("目标Id")
    private String targetId;
    @ExcelProperty("差异度，越接近1（100%），越相似")
    private String isMatch;

    public String getSourceStringId() {
        return sourceStringId;
    }

    public void setSourceStringId(String sourceStringId) {
        this.sourceStringId = sourceStringId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getIsMatch() {
        return isMatch;
    }

    public void setIsMatch(String isMatch) {
        this.isMatch = isMatch;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getHasAttachment() {
        return hasAttachment;
    }

    public void setHasAttachment(String hasAttachment) {
        this.hasAttachment = hasAttachment;
    }
}
