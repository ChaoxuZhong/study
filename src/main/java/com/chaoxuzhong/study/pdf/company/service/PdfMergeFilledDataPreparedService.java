package com.chaoxuzhong.study.pdf.company.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Pair;
import cn.hutool.json.JSONUtil;
import com.chaoxuzhong.study.pdf.company.config.FileVoName;
import com.chaoxuzhong.study.pdf.company.pojo.PdfTemplate;
import com.chaoxuzhong.study.pdf.company.pojo.TmpPdfPath;
import com.chaoxuzhong.study.pdf.company.util.PdfFieldCompareUtil;
import com.chaoxuzhong.study.pdf.company.util.PdfFileUtil;
import com.chaoxuzhong.study.pdf.company.util.PdfITextUtil;
import com.chaoxuzhong.study.pdf.company.vo.PdfFieldVo;
import com.itextpdf.text.DocumentException;
import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class PdfMergeFilledDataPreparedService {

    private static final String SLASH = "/";


    @Autowired
    private PdfFieldFillService pdfFieldFillService;


    /**
     * 填充PDF并上传
     *
     * @param voEnums
     * @param memberNo
     * @return
     */
    public Pair<Boolean, String> fillPdfAndSave(FileVoName voEnums, String memberNo, HashMap<String, HashMap<String, PdfFieldVo>> pdfFieldVoMap) {
        String pdfFileEnName = voEnums.getFileNameEnums().getEnVoName()+".pdf";
        try (
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ) {
            PdfFileUtil.deleteTmpFile(fillAndMergePdf(voEnums, memberNo, pdfFieldVoMap, outputStream));
            String filePath;
            if (voEnums.getIsSign()) {
                filePath = "sign/" + memberNo + "/" + pdfFileEnName;
            } else {
                filePath = "nonSign/" + memberNo + "/" + pdfFileEnName;
            }
            // TODO PDF
//            filePath = OSSUtils.putFile(filePath, IoUtil.toStream(outputStream));
            FileUtil.writeFromStream(IoUtil.toStream(outputStream.toByteArray()), new File("src/main/resources/pdf/" + filePath));
            PdfFieldCompareUtil.comparePdf("src/main/resources/pdf/" + filePath, "src/main/resources/pdf/test-for-compare/" + pdfFileEnName);
            return new Pair<>(true, filePath);
        } catch (Exception e) {
            log.error("生成文件异常 memberNo = {}, 文件类型={} ERROR==>", memberNo, pdfFileEnName, e);
            return new Pair<>(false, "文件生成失败");
        }
    }


    /**
     * 填充PDF 生成pdf流
     *
     * @param voEnums
     * @param memberNo
     * @param outputStream
     * @return
     * @throws IOException
     * @throws DocumentException
     * @throws InterruptedException
     */
    public TmpPdfPath fillAndMergePdf(FileVoName voEnums, String memberNo, HashMap<String, HashMap<String, PdfFieldVo>> pdfFieldVoMap, OutputStream outputStream) throws IOException, DocumentException, InterruptedException {
        String pdfEnName = voEnums.getFileNameEnums().getEnVoName();
        TmpPdfPath tmpPdfPath = fillPdf(voEnums, memberNo, pdfFieldVoMap);
        log.info("填充PDF[{}-{}]  合并PDF --START ，待合并文件：{}", memberNo, pdfEnName, JSONUtil.toJsonStr(tmpPdfPath.getFilePaths()));
        PdfITextUtil.mergePdf(tmpPdfPath.getFilePaths(), outputStream);
        log.info("填充PDF[{}-{}]  合并PDF --END 完成", memberNo, pdfEnName);
        return tmpPdfPath;
    }


    /**
     * 填充pdf并保存到临时路径
     *
     * @param voEnums
     * @param memberNo
     * @param pdfFieldVoMap FileVoName.pdfTemplates 中pdfDaoGeneratorEnums类型为PREPARED_FIELD_NOT_NEED_TO_GENERATE（上层代码准备好数据，不由generator生成）时，上层代码准备好的数据为此map
     *                      其中key 为pdfTemplates的模板路径，value为需要填充的值
     * @return
     * @throws IOException
     */
    public TmpPdfPath fillPdf(FileVoName voEnums, String memberNo, HashMap<String, HashMap<String, PdfFieldVo>> pdfFieldVoMap) throws IOException {
        String pdfEnName = voEnums.getFileNameEnums().getEnVoName();
        TmpPdfPath tmpPdfPath = new TmpPdfPath();

        log.info("填充PDF[{}-{}]  填充 --START", memberNo, pdfEnName);
        // 需要合并的pdf路径集合
        List<String> toMergePdfPaths = new ArrayList<>();

        // 每个用户临时路径前缀
        String memberNoTmpDirPrefix = PdfTemplate.FILE_TEMP_DIR + memberNo + SLASH + LocalDateTime.now().toString() + SLASH;
        Boolean createdTmp = false;
        PdfDocument doc = new PdfDocument();
        for (PdfTemplate pdfTemplate : voEnums.getPdfTemplates()) {
            String toMergePath;
            if (pdfTemplate.getTheWayToFillEnum() == PdfTemplate.TheWayToFillEnum.NON_FILL) {
                log.info("填充PDF[{}-{}] ，子文件：{}无需填充，等待拼接", memberNo, pdfEnName, pdfTemplate.getTemplatePath());
                // 无需生成数据的pdf页直接使用模板pdf拼接
                toMergePath = pdfTemplate.getTemplatePath();
                toMergePdfPaths.add(toMergePath);
                // 无需生成新的pdf，直接返回
                continue;
            } else if (pdfTemplate.getTheWayToFillEnum() == PdfTemplate.TheWayToFillEnum.FILL_BY_PREPARED_DATA) {
                log.info("填充PDF[{}-{}] ，子文件：{}需填充，开始填充", memberNo, pdfEnName, pdfTemplate.getTemplatePath());
                // 需要填充域的pdf页填充后保存到临时路径
                doc = pdfFieldFillService.fillByPdfPath(pdfTemplate.getTemplatePath(),
                        voEnums.getIsSign(),
                        pdfFieldVoMap.get(pdfTemplate.getTemplatePath()));
                log.info("填充PDF[{}-{}] 子文件模板 {}填充完成 完成)", memberNo, pdfEnName, pdfTemplate.getTemplatePath());
            } else {
                log.info("填充PDF[{}-{}] ，子文件：{}需填充，开始填充", memberNo, pdfEnName, pdfTemplate.getTemplatePath());
                // 需要填充域的pdf页填充后保存到临时路径
                doc = pdfFieldFillService.fillByPdfPath(pdfTemplate.getTemplatePath(),
                        pdfTemplate.getTemplateName(),
                        memberNo,
                        voEnums.getIsSign());
            }
            // 创建临时路径
            if (!createdTmp) {
                createdTmp = PdfFileUtil.createTemDir(memberNoTmpDirPrefix, createdTmp);
                tmpPdfPath.setFolder(memberNoTmpDirPrefix);
                log.info("填充PDF[{}-{}] 创建临时路径{}完成", memberNo, pdfEnName, memberNoTmpDirPrefix);
            }
            // 保存文档
            toMergePath = memberNoTmpDirPrefix + pdfTemplate.getTemplateName();
            // 存放临时填充好的文件
            doc.saveToFile(toMergePath, FileFormat.PDF);
            doc.close();
            toMergePdfPaths.add(toMergePath);
        }
        tmpPdfPath.setFilePaths(toMergePdfPaths);

        // 文件生成完成，合并文件
        return tmpPdfPath;
    }

}
