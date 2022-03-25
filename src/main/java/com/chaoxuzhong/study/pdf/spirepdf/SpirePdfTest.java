package com.chaoxuzhong.study.pdf.spirepdf;

import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.fields.PdfField;
import com.spire.pdf.graphics.PdfCjkFontFamily;
import com.spire.pdf.graphics.PdfCjkStandardFont;
import com.spire.pdf.graphics.PdfImage;
import com.spire.pdf.graphics.PdfRGBColor;
import com.spire.pdf.widget.*;
import org.apache.poi.xdgf.geom.Dimension2dDouble;

import java.awt.*;
import java.awt.geom.Point2D;

public class SpirePdfTest {
    public static void main(String[] args) {
        //创建PdfDocument对象
        PdfDocument doc = new PdfDocument();

        //加载一个测试文档
        doc.loadFromFile("/Users/chaoxu/Documents/work-adenfin/trs/九号牌个人开户/V3NinePersonal04NewAddress/PERSONAL_CUST_INFORMATION_CHECK_TURE.pdf");

        //获取文档中的域
        PdfFormWidget form = (PdfFormWidget) doc.getForm();

        //获取域控件集合
        PdfFormFieldWidgetCollection formWidgetCollection = form.getFieldsWidget();

//        testAllKindOfPdfFeild(formWidgetCollection);

//        checkBoxSizeChange(formWidgetCollection);

        checkBoxNotCheck(formWidgetCollection);

        //保存文档
        doc.saveToFile("/Users/chaoxu/Documents/work-adenfin/trs/九号牌个人开户/V3NinePersonal04NewAddress/PERSONAL_CUST_INFORMATION.pdf", FileFormat.PDF);
    }

    private static void checkBoxNotCheck(PdfFormFieldWidgetCollection formWidgetCollection) {
        for (int i = 0; i < formWidgetCollection.getCount(); i++) {
            PdfField field = formWidgetCollection.get(i);
            field.setReadOnly(false);
            if (field instanceof PdfCheckBoxWidgetFieldWidget) {
                PdfCheckBoxWidgetFieldWidget checkBoxField = (PdfCheckBoxWidgetFieldWidget) field;
                checkBoxField.setChecked(false);
            }
            field.setReadOnly(true);
        }
    }

    /**
     * 调整PDF复选框的大小
     *
     * @param formWidgetCollection
     */
    private static void checkBoxSizeChange(PdfFormFieldWidgetCollection formWidgetCollection) {
        PdfRGBColor backColorTransparent;
        PdfRGBColor borderColorTransparent = null;

        // 记录已知的透明色
        for (int i = 0; i < formWidgetCollection.getCount(); i++) {
            PdfField field = formWidgetCollection.get(i);
            if ("primaryEduLevel03".equals(field.getName())) {
                if (field instanceof PdfCheckBoxWidgetFieldWidget) {
                    PdfCheckBoxWidgetFieldWidget checkBoxField = (PdfCheckBoxWidgetFieldWidget) field;
                    backColorTransparent = checkBoxField.getBackColor();
                    borderColorTransparent = checkBoxField.getBorderColor();
                }
            }
        }
        for (int i = 0; i < formWidgetCollection.getCount(); i++) {

            PdfField field = formWidgetCollection.get(i);
            String name = field.getName();
            field.setReadOnly(false);

            if (field instanceof PdfCheckBoxWidgetFieldWidget) {
                PdfCheckBoxWidgetFieldWidget checkBoxField = (PdfCheckBoxWidgetFieldWidget) field;
                checkBoxField.setSize(new Dimension2dDouble(15, 15));
                checkBoxField.setBorderColor(borderColorTransparent);
                Point2D oldLocation = checkBoxField.getLocation();
                // 以前的框长宽大概为6.3  和 8  当前大小为15  x 轴减小 （15-6.3）/2 = 4.35  y轴减小 （15-8）/2 = 3.5
                Point2D newLocation = new Point2D.Double(oldLocation.getX() - 4.35, oldLocation.getY() - 3.5);
                checkBoxField.setLocation(newLocation);

                checkBoxField.setChecked(true);

            }
            field.setReadOnly(true);

        }
    }

    /**
     * 测试各种pdf 域操作
     *
     * @param formWidgetCollection
     */
    private static void testAllKindOfPdfFeild(PdfFormFieldWidgetCollection formWidgetCollection) {
        //遍历域控件并填充数据
        for (int i = 0; i < formWidgetCollection.getCount(); i++) {

            PdfField field = formWidgetCollection.get(i);
            String name = field.getName();
            if (field instanceof PdfTextBoxFieldWidget) {
                PdfTextBoxFieldWidget textBoxField = (PdfTextBoxFieldWidget) field;

                Font font = new Font("宋体", Font.PLAIN, 10);
//                PdfTrueTypeFont pdfTrueTypeFont = new PdfTrueTypeFont(font, true);
                PdfCjkStandardFont cjkFont = new PdfCjkStandardFont(PdfCjkFontFamily.Sino_Type_Song_Light, 9f);

                textBoxField.setFont(cjkFont);
                textBoxField.setText("吴敏");
            }
            if (field instanceof PdfRadioButtonListFieldWidget) {
                PdfRadioButtonListFieldWidget radioButtonListField = (PdfRadioButtonListFieldWidget) field;
                radioButtonListField.setSelectedIndex(1);
            }
            if (field instanceof PdfListBoxWidgetFieldWidget) {
                PdfListBoxWidgetFieldWidget listBox = (PdfListBoxWidgetFieldWidget) field;
                listBox.setSelectedIndex(1);
            }
            if (field instanceof PdfCheckBoxWidgetFieldWidget) {
                PdfCheckBoxWidgetFieldWidget checkBoxField = (PdfCheckBoxWidgetFieldWidget) field;
                checkBoxField.setChecked(true);

            }
            if (field instanceof PdfComboBoxWidgetFieldWidget) {
                PdfComboBoxWidgetFieldWidget comboBoxField = (PdfComboBoxWidgetFieldWidget) field;
                comboBoxField.setSelectedIndex(1);
            }
            if (field instanceof PdfButtonWidgetFieldWidget) {
                PdfButtonWidgetFieldWidget buttonWidgetField = (PdfButtonWidgetFieldWidget) field;
                buttonWidgetField.setButtonImage(PdfImage.fromFile("/Users/chaoxu/Documents/word-adenfin/trs/标记过-type 9 ind ac open 23092021/images/1+2+3 xml4_img_8.jpg"));
            }
            field.setReadOnly(true);
        }
    }
}
