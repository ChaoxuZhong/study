package com.chaoxuzhong.study.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.OutputStream;

public class PdfConvertUtil {

    //经过测试,dpi为96,100,105,120,150,200中,105显示效果较为清晰,体积稳定,dpi越高图片体积越大,一般电脑显示分辨率为96
    public static final float DEFAULT_DPI = 105;
    //默认转换的图片格式为jpg
    public static final String DEFAULT_FORMAT = "jpg";

    /**
     * pdf转图片
     * 1.将pdf每一页转换成BufferedImage，只能单页转换
     * 2.通过BufferedImage读取图片中的rpg信息
     * 3.利用新的 BuffedImageRusult 映射输出图片，将2获取到的rpg信息依次存入BuffedImageRusult
     *
     * @param inputStream
     * @param outputStream
     */
    public static void pdfToImage(InputStream inputStream, OutputStream outputStream) {
        try {

            // 准备Pdf文档及render 利用PdfBox生成图像
            PDDocument pdDocument = PDDocument.load(inputStream);
            PDFRenderer renderer = new PDFRenderer(pdDocument);

            // 图片中rbg 数据临时存储，来源为每一页的pdf，用于输出到最终合成的图片。介于两者之间进行过度
            int[] imgRbgTemp;

            // 总页数
            int pageSize = pdDocument.getNumberOfPages();

            // 页面宽度 所有页面及最后生成的都一样
            int width = 0;

            // 高度，分为单页高度和总高=第一页单页*总页数（简化，不每一页相加）
            int perPageHeight = 0;
            int totalHeight = 0;

            BufferedImage imageResult = null;

            // 1.将pdf每一页转换成BufferedImage，只能单页转换
            for (int i = 0; i < pageSize; i++) {
                // 2.通过 BufferedImage 读取图片中的RGB信息
                BufferedImage image = renderer.renderImageWithDPI(i, DEFAULT_DPI);
                // 根据第一页信息生成
                if (i == 0) {
                    perPageHeight = image.getHeight();
                    totalHeight = image.getHeight() * pageSize;
                    width = image.getWidth();
                    imageResult = new BufferedImage(width, totalHeight, BufferedImage.TYPE_INT_RGB);
                }
                // 读取出 RGB 信息临时存放在此
                imgRbgTemp = new int[width * perPageHeight];
                imgRbgTemp = image.getRGB(0, 0, width, perPageHeight, imgRbgTemp, 0, width);
                imageResult.setRGB(0, perPageHeight * i, width, perPageHeight, imgRbgTemp, 0, width);
            }

            pdDocument.close();
            // 写图片
            ImageIO.write(imageResult, DEFAULT_FORMAT, outputStream);
        } catch (Exception e) {

            e.printStackTrace();
        }
        //OVER
    }
}
