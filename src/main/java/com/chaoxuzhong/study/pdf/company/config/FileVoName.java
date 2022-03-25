package com.chaoxuzhong.study.pdf.company.config;

import com.chaoxuzhong.study.pdf.company.pojo.PdfFileNameEnums;
import com.chaoxuzhong.study.pdf.company.pojo.PdfTemplate;
import com.chaoxuzhong.study.pdf.company.service.PdfTemplateNameConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
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

    public static final FileVoName NONSING_FW8BEN = new FileVoName(
            PdfFileNameEnums.FW8BENE,
            false,
            Collections.singletonList(
                    PdfTemplate.init(PdfTemplateNameConstant.FW8BEN)
            )
    );

    public static final FileVoName NONSIGN_AML_QUESTIONNAIRE = new FileVoName(
            PdfFileNameEnums.AML_QUESTIONNAIRE,
            false,
            Collections.singletonList((
                    PdfTemplate.init(PdfTemplateNameConstant.AML_QUESTIONNAIRE)
            ))
    );

    public static final FileVoName NONSIGN_SELF_CERT_ENTITY = new FileVoName(
            PdfFileNameEnums.SELF_CERTIFICATION_ENTITY_FORM,
            false,
            Collections.singletonList((
                    PdfTemplate.init(PdfTemplateNameConstant.SELF_SERT_ENTITY)
            ))
    );







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
