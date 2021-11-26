//package com.chaoxuzhong.study.pdf.spirepdf;
//
//import com.spire.pdf.FileFormat;
//import com.spire.pdf.PdfDocument;
//import com.spire.pdf.PdfPageBase;
//import com.spire.pdf.fields.*;
//import com.spire.pdf.graphics.PdfRGBColor;
//import com.spire.pdf.graphics.PdfSolidBrush;
//import com.spire.pdf.graphics.PdfTrueTypeFont;
//
//import java.awt.*;
//import java.awt.geom.Point2D;
//import java.awt.geom.Rectangle2D;
//
//public class AddFormFiledToPdf {
//
//    public static void main(String[] args) throws Exception {
//
//        //创建PdfDocument对象
//        PdfDocument doc = new PdfDocument();
//
//        //添加页面
//        PdfPageBase page = doc.getPages().add();
//
//        //初始化位置变量
//        float baseX = 100;
//        float baseY = 0;
//
//        //创建画刷对象
//        PdfSolidBrush brush1 = new PdfSolidBrush(new PdfRGBColor(Color.BLUE));
//        PdfSolidBrush brush2 = new PdfSolidBrush(new PdfRGBColor(Color.black));
//
//        //创建TrueType字体
//        PdfTrueTypeFont font = new PdfTrueTypeFont(new Font("宋体", Font.PLAIN, 12), true);
//
//        //添加文本框
//        String text = "文本框:";//文本框前的文字
//        page.getCanvas().drawString(text, font, brush1, new Point2D.Float(0, baseY));//在PDF中绘制文字
//        Rectangle2D.Float tbxBounds = new Rectangle2D.Float(baseX, baseY, 150, 15);//创建Rectangle2D对象
//        PdfTextBoxField textBox = new PdfTextBoxField(page, "TextBox");//创建文本框对象
//        textBox.setBounds(tbxBounds);//设置文本框的Bounds,包括位置和大小信息
//        textBox.setText("你好");//设置文本框的默认文字
//        textBox.setFont(font);//设置文本框的字体
//        doc.getForm().getFields().add(textBox);//添加文本框到PDF域的集合
//        baseY += 25;
//
////        //添加复选框
////        page.getCanvas().drawString("复选框:", font, brush1, new Point2D.Float(0, baseY));
////        java.awt.geom.Rectangle2D.Float rec1 = new java.awt.geom.Rectangle2D.Float(baseX, baseY, 15, 15);
////        PdfCheckBoxField checkBoxField = new PdfCheckBoxField(page, "CheckBox1");
////        checkBoxField.setBounds(rec1);
////        checkBoxField.setChecked(false);
////        page.getCanvas().drawString("选项1", font, brush2, new Point2D.Float(baseX + 20, baseY));
////        java.awt.geom.Rectangle2D.Float rec2 = new java.awt.geom.Rectangle2D.Float(baseX + 70, baseY, 15, 15);
////        PdfCheckBoxField checkBoxField1 = new PdfCheckBoxField(page, "CheckBox2");
////        checkBoxField1.setBounds(rec2);
////        checkBoxField1.setChecked(false);
////        page.getCanvas().drawString("选项2", font, brush2, new Point2D.Float(baseX + 90, baseY));
////        doc.getForm().getFields().add(checkBoxField);
////        baseY += 25;
////
////        //添加列表框
////        page.getCanvas().drawString("列表框:", font, brush1, new Point2D.Float(0, baseY));
////        java.awt.geom.Rectangle2D.Float rec = new java.awt.geom.Rectangle2D.Float(baseX, baseY, 150, 50);
////        PdfListBoxField listBoxField = new PdfListBoxField(page, "ListBox");
////        listBoxField.getItems().add(new PdfListFieldItem("项目1", "item1"));
////        listBoxField.getItems().add(new PdfListFieldItem("项目2", "item2"));
////        listBoxField.getItems().add(new PdfListFieldItem("项目3", "item3"));
////        ;
////        listBoxField.setBounds(rec);
////        listBoxField.setFont(font);
////        listBoxField.setSelectedIndex(0);
////        doc.getForm().getFields().add(listBoxField);
////        baseY += 60;
////
////        //添加单选按钮
////        page.getCanvas().drawString("单选按钮:", font, brush1, new Point2D.Float(0, baseY));
////        PdfRadioButtonListField radioButtonListField = new PdfRadioButtonListField(page, "Radio");
////        PdfRadioButtonListItem radioItem1 = new PdfRadioButtonListItem("Item1");
////        radioItem1.setBounds(new Rectangle2D.Float(baseX, baseY, 15, 15));
////        page.getCanvas().drawString("选项1", font, brush2, new Point2D.Float(baseX + 20, baseY));
////        PdfRadioButtonListItem radioItem2 = new PdfRadioButtonListItem("Item2");
////        radioItem2.setBounds(new Rectangle2D.Float(baseX + 70, baseY, 15, 15));
////        page.getCanvas().drawString("选项2", font, brush2, new Point2D.Float(baseX + 90, baseY));
////        radioButtonListField.getItems().add(radioItem1);
////        radioButtonListField.getItems().add(radioItem2);
////        radioButtonListField.setSelectedIndex(0);
////        doc.getForm().getFields().add(radioButtonListField);
////        baseY += 25;
////
////        //添加组合框
////        page.getCanvas().drawString("组合框:", font, brush1, new Point2D.Float(0, baseY));
////        Rectangle2D.Float cmbBounds = new Rectangle2D.Float(baseX, baseY, 150, 15);
////        PdfComboBoxField comboBoxField = new PdfComboBoxField(page, "ComboBox");
////        comboBoxField.setBounds(cmbBounds);
////        comboBoxField.getItems().add(new PdfListFieldItem("项目1", "item1"));
////        comboBoxField.getItems().add(new PdfListFieldItem("项目2", "itme2"));
////        comboBoxField.getItems().add(new PdfListFieldItem("项目3", "item3"));
////        comboBoxField.getItems().add(new PdfListFieldItem("项目4", "item4"));
////        comboBoxField.setSelectedIndex(0);
////        comboBoxField.setFont(font);
////        doc.getForm().getFields().add(comboBoxField);
////        baseY += 25;
////
////        //添加签名域
////        page.getCanvas().drawString("签名域:", font, brush1, new Point2D.Float(0, baseY));
////        PdfSignatureField sgnField = new PdfSignatureField(page, "sgnField");
////        Rectangle2D.Float sgnBounds = new Rectangle2D.Float(baseX, baseY, 150, 80);
////        sgnField.setBounds(sgnBounds);
////        doc.getForm().getFields().add(sgnField);
////        baseY += 90;
////        //添加按钮
////        page.getCanvas().drawString("提交按钮:", font, brush1, new Point2D.Float(0, baseY));
////        Rectangle2D.Float btnBounds = new Rectangle2D.Float(baseX, baseY, 50, 15);
////        PdfButtonField buttonField = new PdfButtonField(page, "Button");
////        buttonField.setBounds(btnBounds);
////        buttonField.setText("提交");
////        buttonField.setFont(font);
////        doc.getForm().getFields().add(buttonField);
//
//        //保存文档
//        doc.saveToFile("AddFormField.pdf", FileFormat.PDF);
//    }
//
//}
