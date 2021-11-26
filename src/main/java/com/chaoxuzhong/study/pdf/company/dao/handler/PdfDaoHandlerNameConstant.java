package com.chaoxuzhong.study.pdf.company.dao.handler;

import java.util.Arrays;
import java.util.List;

public class PdfDaoHandlerNameConstant {

    public static final String MEMBER_INF0 = "memberInfoDaoHandler";
    public static final String MEMBER_INFO_DETAIL = "memberInfoDetailDaoHandler";
    public static final String JURISDICTION = "jurisdictionDaoHandler";
    public static final String CO_INFO = "coInfoDaoHandler";
    public static final String CO_PERSONAL = "coPersonalDaoHandler";
    public static final String CO_AML = "coAmlDaoHandler";
    public static final String CO_USA = "coUsaDaoHander";
    public static final String CO_IA = "coIaDaoHandler";
    public static final String CO_FILE = "coFileDaoHander";
    public static final String CO_FILE_SIN = "coFileSignDaoHandler";


    public static List<String> getAllName() {
        return Arrays.asList(
                MEMBER_INF0,
                MEMBER_INFO_DETAIL,
                JURISDICTION,
                CO_IA,
                CO_INFO,
                CO_PERSONAL,
                CO_USA,
                CO_FILE,
                CO_FILE_SIN
        );
    }

}
