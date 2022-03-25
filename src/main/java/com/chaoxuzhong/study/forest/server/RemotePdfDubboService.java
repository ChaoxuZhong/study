package com.chaoxuzhong.study.forest.server;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
@Slf4j
public class RemotePdfDubboService{

    //经过测试,dpi为96,100,105,120,150,200中,105显示效果较为清晰,体积稳定,dpi越高图片体积越大,一般电脑显示分辨率为96
    public static final float DEFAULT_DPI = 105;
    //默认转换的图片格式为jpg
    public static final String DEFAULT_FORMAT = "jpg";


    public byte[] pdfToImage(ByteArrayInputStream inputStream) {
        try (
                // 用于接收生成的pic流
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ) {
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
            ImageIO.write(imageResult, DEFAULT_FORMAT, byteArrayOutputStream);
            log.info("转图片完成");
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            log.error("pdf convert exception", e);
            throw new RuntimeException("convert error ==> " + e.getMessage());
        }
    }


    public byte[] pdfToImage(byte[] pdfByteArray) {
        return pdfToImage(new ByteArrayInputStream(pdfByteArray));
    }
}

