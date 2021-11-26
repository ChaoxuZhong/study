package com.chaoxuzhong.study.pdf.company.pojo;

/**
 * pdf文件生成，临时文件路径，及文件夹路径
 */

import lombok.Data;

import java.util.List;

@Data
public class TmpPdfPath {
    private String folder;
    private List<String> filePaths;
}
