package com.chaoxuzhong.study.pdf.company.service;

import cn.hutool.core.date.DateUtil;
import com.chaoxuzhong.study.pdf.company.config.PdfCompanyFieldNameDaoConfig;
import com.chaoxuzhong.study.pdf.company.config.PdfDaoConfig;
import com.chaoxuzhong.study.pdf.company.dao.SimplePdfDaoFactory;
import com.chaoxuzhong.study.pdf.company.pojo.FieldTypeEnums;
import com.chaoxuzhong.study.pdf.company.util.PdfFileUtil;
import com.chaoxuzhong.study.pdf.company.vo.PdfFieldVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

/**
 * PDF 数据层，根据配置获取数据库数据，并根据配置转换成填充PDF所需数据
 */
@Service
@Slf4j
public class PdfFieldDataService {

    private static final SimpleDateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 获取填充pdf需要的数据FieldVo
     * @param memberNo
     * @param templateFileName
     * @return
     */
    public HashMap<String, PdfFieldVo> getPdfFieldVo(String memberNo, String templateFileName) {
        // 获取配置
        HashMap<String, PdfDaoConfig> pdfDaoConfigMap = PdfCompanyFieldNameDaoConfig.getConfig(templateFileName);
        // 获取数据库数据
        HashMap<String, Object> pdfDaoMap = getPdfDao(memberNo, templateFileName);
        HashMap<String, PdfFieldVo> result = new HashMap<>();
        pdfDaoConfigMap.forEach((k, v) -> {
            try {
                result.put(k, convertConfigToVo(k, v, pdfDaoMap));
            } catch (Exception e) {
                return;
            }
        });
        return result;
    }

    /**
     * 将配置转换成展示层VO
     * 展示层VO map key = config key
     * 展示层VO map value 为配置对应数据库返回数据
     *
     * @param key
     * @param config
     * @param daoMap
     * @return
     */
    private PdfFieldVo convertConfigToVo(String key, PdfDaoConfig config, HashMap<String, Object> daoMap) {
        PdfFieldVo pdfFieldVo;
        // 是复选框切是默认勾选
        if (isCheckBox(config) && config.getCheckBoxDefault()) {
            pdfFieldVo = new PdfFieldVo(config.getType(), "true");
        } else {
            // 获取字段值
            String feildValue = daoValueToString(daoMap.get(config.getDaoFieldName()));
            // 数据库字段为空，不执行，进行下一条数据处理
            if (StringUtils.isEmpty(feildValue)) {
                log.info(String.format("field %s 's value is null", config.getDaoFieldName()));
                throw new NullPointerException(String.format("field %s 's value is null", config.getDaoFieldName()));
            }
            if (isCheckBox(config)) {
                pdfFieldVo = new PdfFieldVo(config.getType(), PdfFileUtil.isBoxCheck(key, feildValue));
            } else {
                // 生成PDF域展示对象
                pdfFieldVo = new PdfFieldVo(config.getType(), feildValue);
            }
        }
        return pdfFieldVo;
    }

    private String daoValueToString(Object daoValue) {
        if (daoValue instanceof LocalDate) {
            return ((LocalDate) daoValue).format(DateTimeFormatter.ISO_LOCAL_DATE);
        } else if (daoValue instanceof Integer) {
            return ((Integer) daoValue).toString();
        } else if (daoValue instanceof Date) {
            return DateUtil.date((Date)daoValue).toString(YYYY_MM_DD);
        }
        return daoValue.toString();
    }

    private boolean isCheckBox(PdfDaoConfig value) {
        return value.getType() == FieldTypeEnums.CHECKBOX;
    }

    /**
     * 获取pdf数据层数据
     *
     * @param memberNo
     * @param templateFileName
     */
    private HashMap<String, Object> getPdfDao(String memberNo, String templateFileName) {
        return SimplePdfDaoFactory.getPdfDaoHandlerChain(templateFileName).handle(memberNo);
    }
}
