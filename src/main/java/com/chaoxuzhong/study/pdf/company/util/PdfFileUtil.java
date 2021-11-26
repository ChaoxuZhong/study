package com.chaoxuzhong.study.pdf.company.util;

import com.chaoxuzhong.study.pdf.company.pojo.PdfTemplate;
import com.chaoxuzhong.study.pdf.company.pojo.TmpPdfPath;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@Slf4j
public class PdfFileUtil {

    public static void deleteTmpFile(TmpPdfPath tmpPdfPath) throws IOException {
        for (String path : tmpPdfPath.getFilePaths()) {
            // 删除临时文件
            if (path.contains(PdfTemplate.FILE_TEMP_DIR)) {
                deleteFile(path);
            }
        }
        // 文件夹同时删除
        deleteFile(tmpPdfPath.getFolder());
    }

    public static void deleteFile(String path) throws IOException {
        File file = new File(path);
        boolean flag = file.delete();
        log.info("删除文件{}：{}", flag ? "success" : "fail", path);
    }

    /**
     * 创建临时路径
     *
     * @param custNoTmpDirPrefix
     * @return
     */
    public static Boolean createTemDir(String custNoTmpDirPrefix, boolean created) {
        if (created) {
            return created;
        }
        File file = new File(custNoTmpDirPrefix);
        if (file.exists()) {
            return true;
        }
        return file.mkdirs();
    }


    /**
     * 是否勾选复选框
     * 规则：key中包含枚举值即勾选
     * 比如数据库值为 01,02,03 ，key 为primaryEduLevel03 ，则该复选框应该勾选
     *
     * @param key
     * @param fieldValue
     * @return
     */
    public static String isBoxCheck(String key, String fieldValue) {
        String[] checkBoxs = fieldValue.split(",");
        for (String checkBoxValueDB : checkBoxs) {
            if (key.contains(checkBoxValueDB)) {
                return "true";
            }
        }
        return "false";
    }
}

