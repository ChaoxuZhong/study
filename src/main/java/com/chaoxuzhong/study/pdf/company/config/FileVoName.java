package com.chaoxuzhong.study.pdf.company.config;

import com.chaoxuzhong.study.pdf.company.pojo.PdfFileNameEnums;
import com.chaoxuzhong.study.pdf.company.pojo.PdfTemplate;
import com.chaoxuzhong.study.pdf.company.service.PdfTemplateNameConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public class FileVoName {
    public static final FileVoName NONSIGN_ADEN_CUST_INFO = new FileVoName(
            PdfFileNameEnums.ADEN_ACOPENING_FORM_CORPORATE,
            false,
            Arrays.asList(
                    PdfTemplate.init(PdfTemplateNameConstant.ADEN_AC_OPENING_FORM_CORPORATE_01),
                    PdfTemplate.init(PdfTemplateNameConstant.ADEN_AC_OPENING_FORM_CORPORATE_02)
            ));


    /**
     * 文件名称
     */
    private PdfFileNameEnums fileNameEnums;
    /**
     * 文件是否签名  true，签名，false，不签名
     */
    private Boolean isSign;

    private List<PdfTemplate> pdfTemplates;

    public static List<FileVoName> allFileVoNames = Arrays.asList(NONSIGN_ADEN_CUST_INFO);

    public static FileVoName valueOf(String fileEnName) {
        for (FileVoName fileVoName : allFileVoNames) {
            if (fileVoName.getFileNameEnums().getEnVoName().equals(fileEnName)) {
                return fileVoName;
            }
        }
        throw new IllegalArgumentException("fileEnName is not exist:" + fileEnName);
    }

}
