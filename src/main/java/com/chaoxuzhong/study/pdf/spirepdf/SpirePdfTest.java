//package com.chaoxuzhong.study.pdf.spirepdf;
//
//import com.spire.pdf.FileFormat;
//import com.spire.pdf.PdfDocument;
//import com.spire.pdf.fields.PdfField;
//import com.spire.pdf.graphics.PdfCjkFontFamily;
//import com.spire.pdf.graphics.PdfCjkStandardFont;
//import com.spire.pdf.graphics.PdfImage;
//import com.spire.pdf.widget.*;
//
//import java.awt.*;
//
//public class SpirePdfTest {
//    public static void main(String[] args) {
//        //创建PdfDocument对象
//        PdfDocument doc = new PdfDocument();
//
//        //加载一个测试文档
//        doc.loadFromFile("/Users/chaoxu/Documents/word-adenfin/trs/标记过-type 9 ind ac open 23092021/A45260787 v5.0 Adenfin_IMA Template.pdf");
//
//        //获取文档中的域
//        PdfFormWidget form = (PdfFormWidget) doc.getForm();
//
//        //获取域控件集合
//        PdfFormFieldWidgetCollection formWidgetCollection = form.getFieldsWidget();
//
//
//        //遍历域控件并填充数据
//        for (int i = 0; i < formWidgetCollection.getCount(); i++) {
//
//            PdfField field = formWidgetCollection.get(i);
//            String name = field.getName();
//            if (field instanceof PdfTextBoxFieldWidget) {
//                PdfTextBoxFieldWidget textBoxField = (PdfTextBoxFieldWidget) field;
//
//                Font font = new Font("宋体", Font.PLAIN, 10);
////                PdfTrueTypeFont pdfTrueTypeFont = new PdfTrueTypeFont(font, true);
//                PdfCjkStandardFont cjkFont = new PdfCjkStandardFont(PdfCjkFontFamily.Sino_Type_Song_Light, 9f);
//
//                textBoxField.setFont(cjkFont);
//                textBoxField.setText("吴敏");
//            }
//            if (field instanceof PdfRadioButtonListFieldWidget) {
//                PdfRadioButtonListFieldWidget radioButtonListField = (PdfRadioButtonListFieldWidget) field;
//                radioButtonListField.setSelectedIndex(1);
//            }
//            if (field instanceof PdfListBoxWidgetFieldWidget) {
//                PdfListBoxWidgetFieldWidget listBox = (PdfListBoxWidgetFieldWidget) field;
//                listBox.setSelectedIndex(1);
//            }
//            if (field instanceof PdfCheckBoxWidgetFieldWidget) {
//                PdfCheckBoxWidgetFieldWidget checkBoxField = (PdfCheckBoxWidgetFieldWidget) field;
//                checkBoxField.setChecked(true);
//
//            }
//            if (field instanceof PdfComboBoxWidgetFieldWidget) {
//                PdfComboBoxWidgetFieldWidget comboBoxField = (PdfComboBoxWidgetFieldWidget) field;
//                comboBoxField.setSelectedIndex(1);
//            }
//            if (field instanceof PdfButtonWidgetFieldWidget) {
//                PdfButtonWidgetFieldWidget buttonWidgetField = (PdfButtonWidgetFieldWidget) field;
//                buttonWidgetField.setButtonImage(PdfImage.fromFile("/Users/chaoxu/Documents/word-adenfin/trs/标记过-type 9 ind ac open 23092021/images/1+2+3 xml4_img_8.jpg"));
//            }
//            field.setReadOnly(true);
//        }
//
//        //保存文档
//        doc.saveToFile("FillFormFields.pdf", FileFormat.PDF);
//    }
//}
