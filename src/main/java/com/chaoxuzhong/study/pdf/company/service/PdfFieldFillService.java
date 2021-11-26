package com.chaoxuzhong.study.pdf.company.service;

import cn.hutool.core.util.ObjectUtil;
import com.chaoxuzhong.study.pdf.company.vo.PdfFieldVo;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.fields.PdfField;
import com.spire.pdf.graphics.PdfCjkFontFamily;
import com.spire.pdf.graphics.PdfCjkStandardFont;
import com.spire.pdf.graphics.PdfTextAlignment;
import com.spire.pdf.widget.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * pdf 域填充服务
 */
@Service
@Slf4j
public class PdfFieldFillService {


    // 字体全局共享
    private static final PdfCjkStandardFont PDF_CJK_STANDARD_FONT = new PdfCjkStandardFont(PdfCjkFontFamily.Sino_Type_Song_Light, 9f);

    @Autowired
    private PdfFieldDataService pdfFieldDataService;

    /**
     * 填充pdf 数据来源通过 {@link PdfFieldDataService}
     *
     * @param filePath
     * @param fileName
     * @param memberNo
     * @param isSign
     * @return
     * @throws IOException
     */
    public PdfDocument fillByPdfPath(String filePath, String fileName, String memberNo, Boolean isSign) throws IOException {
        // 根据配置生成所有需要展示的字段值
        HashMap<String, PdfFieldVo> allFieldFromDB = pdfFieldDataService.getPdfFieldVo(memberNo, fileName);
        PdfDocument pdfDocument = fillByPdfPath(filePath, isSign, allFieldFromDB);
        log.info("填充PDF[{}-{}] 单文件填充完成)", memberNo, fileName);
        return pdfDocument;
    }


    /**
     * 填充pdf
     *
     * @param filePath       文件路径
     * @param isSign         是否签名pdf
     * @param allFieldFromDB 所有的填充域的数据
     * @return
     * @throws IOException
     */
    public PdfDocument fillByPdfPath(String filePath, Boolean isSign, HashMap<String, PdfFieldVo> allFieldFromDB) throws IOException {
        //创建PdfDocument对象
        PdfDocument doc = new PdfDocument();

        ClassPathResource resource = new ClassPathResource(filePath);
        InputStream inputStream = resource.getInputStream();
        //加载文档
        doc.loadFromStream(inputStream);

        //获取文档中的域
        PdfFormWidget form = (PdfFormWidget) doc.getForm();

        //获取域控件集合
        PdfFormFieldWidgetCollection formWidgetCollection = form.getFieldsWidget();

        //遍历域控件并填充数据
        for (int i = 0; i < formWidgetCollection.getCount(); i++) {
            PdfField field = formWidgetCollection.get(i);
            String name = field.getName();
            PdfFieldVo pdfFeildValue = allFieldFromDB.get(name);
            if (ObjectUtil.isNull(pdfFeildValue) || ObjectUtil.isEmpty(pdfFeildValue.getV())) {
                field.setReadOnly(true);
                continue;
            }
            // 先解除只读
            field.setReadOnly(false);
            switch (pdfFeildValue.getType()) {
                case TEXT:
                    setText(field, pdfFeildValue);
                    break;
                case CHECKBOX:
                    setCheckBox(field, pdfFeildValue);
                    break;
                case BUTTON:
                    // 配置了需要签名才设置签名
                    if (isSign) {
                        setButton(field, pdfFeildValue);
                    }
                    break;
                default:
                    // 此异常会终止整个填充流程，故只用在测试+调试时，因只有代码写错才会发生此异常
                    throw new IllegalStateException("Unexpected value: " + pdfFeildValue.getType());
            }
            field.setReadOnly(true);
        }

        inputStream.close();
        return doc;
    }

    private void setButton(PdfField field, PdfFieldVo pdfFeildValue) {
        if (field instanceof PdfButtonWidgetFieldWidget) {
            try {
                PdfButtonWidgetFieldWidget buttonWidgetField = (PdfButtonWidgetFieldWidget) field;
                // TODO PDF
//                buttonWidgetField.setButtonImage(PdfImage.fromStream(OSSUtils.getFileStream(pdfFeildValue.getV())));
            } catch (Exception e) {
                log.error("pdf 填充 文件获取异常 ==>", e);
            }
        } else {
            throw new IllegalStateException("pdf field type not macth java config BUTTON");
        }
    }

    private void setCheckBox(PdfField field, PdfFieldVo pdfFeildValue) {
        if (field instanceof PdfCheckBoxWidgetFieldWidget) {
            PdfCheckBoxWidgetFieldWidget checkBoxField = (PdfCheckBoxWidgetFieldWidget) field;
            if (pdfFeildValue.getV().equals("true")) {
                checkBoxField.setChecked(true);
            } else {
                checkBoxField.setChecked(false);
            }
        } else {
            throw new IllegalStateException("pdf field type not macth java config CHECKBOX");
        }
    }

    private void setText(PdfField field, PdfFieldVo pdfFeildValue) {
        try {
            if (field instanceof PdfTextBoxFieldWidget) {
                PdfTextBoxFieldWidget textBoxField = (PdfTextBoxFieldWidget) field;
                textBoxField.setFont(PDF_CJK_STANDARD_FONT);
                textBoxField.setMultiline(true);
                textBoxField.setTextAlignment(PdfTextAlignment.Center);
                textBoxField.setText(pdfFeildValue.getV());
            } else {
                throw new IllegalStateException("pdf field type not macth java config TEXT");
            }
        } catch (Exception e) {
            return;
        }
    }

}
