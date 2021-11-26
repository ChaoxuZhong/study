package com.chaoxuzhong.study.pdf.company.pojo;


import lombok.Getter;


/**
 * pdf 模板类，此类包含模板加载路径，生成临时文件的路径和模板文件名称
 *
 */
@Getter
public class PdfTemplate {

    // 模板文件在系统中路径  本地
//    public static final String TEMPLATE_FILE_PATH_ROOT = "src/main/resources/pdf/template/";
//    // 临时文件路径 本地
//    public static final String FILE_TEMP_DIR = "src/main/resources/pdf/tmp/";

    // 模板文件在系统中路径  linux 打包后jar中的路径()
    public static final String TEMPLATE_FILE_PATH_ROOT = "pdf/template/";
    // 临时文件路径 linux服务器
    public static final String FILE_TEMP_DIR = "tmp/pdf/";
    /**
     * 模板路径
     */
    private String templatePath;
    /**
     * pdf 模板名称
     */
    private String templateName;

    /**
     * 填充方式
     */
    private TheWayToFillEnum theWayToFillEnum;



    private PdfTemplate(String templateName, TheWayToFillEnum theWayToFillEnum) {
        this.templatePath = TEMPLATE_FILE_PATH_ROOT + templateName;
        this.templateName = templateName;
        this.theWayToFillEnum = theWayToFillEnum;
    }

    public static PdfTemplate init(String templateName) {
        return new PdfTemplate(templateName, TheWayToFillEnum.FILL_BY_DAO);
    }

    public static PdfTemplate init(String templateName,TheWayToFillEnum theWayToFillEnum) {
        return new PdfTemplate(templateName, theWayToFillEnum);
    }

    /**
     * 填充方式
     */
    public enum TheWayToFillEnum{
        // 不填充
        NON_FILL,
        // 根据dao层填充
        FILL_BY_DAO,
        // 自有数据填充
        FILL_BY_PREPARED_DATA
    }

}

