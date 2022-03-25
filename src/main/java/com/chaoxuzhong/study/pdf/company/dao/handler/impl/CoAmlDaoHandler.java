package com.chaoxuzhong.study.pdf.company.dao.handler.impl;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.chaoxuzhong.study.pdf.company.dao.handler.IPdfDaoHandler;
import com.chaoxuzhong.study.pdf.company.dao.handler.PdfDaoDbNameConstant;
import com.chaoxuzhong.study.pdf.company.dao.handler.PdfDaoHandlerNameConstant;
import com.chaoxuzhong.study.pdf.company.mockdao.MemberCoAml;
import com.chaoxuzhong.study.pdf.company.mockdao.TestMockDaoSerivce;
import com.chaoxuzhong.study.pdf.company.util.MapUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service(PdfDaoHandlerNameConstant.CO_AML)
@Slf4j
public class CoAmlDaoHandler implements IPdfDaoHandler {

    @Autowired
    private TestMockDaoSerivce testMockDaoSerivce;


    @Override
    public void handle(String memberNo, HashMap<String, Object> dbDataMap) {
        MemberCoAml memberCoAml = testMockDaoSerivce.getMemberCoAml();

        HashMap<String, Object> dbMapInThisHandler = new HashMap<>();
        dealJsonArray(dbMapInThisHandler, memberCoAml.getAmlLaw(),"amlLaw");
        dealJsonArray(dbMapInThisHandler, memberCoAml.getAmlFatca(),"amlFatca");
        dealJsonArray(dbMapInThisHandler, memberCoAml.getAmlDiscretionary(),"amlDiscretionary");
        dealJsonArray(dbMapInThisHandler, memberCoAml.getAmlMonit(),"amlMonit");
        dealJsonArray(dbMapInThisHandler, memberCoAml.getAmlKycPolicy(),"amlKycPolicy");
        dealJsonArray(dbMapInThisHandler, memberCoAml.getAmlProtection(),"amlProtection");
        dealJsonArray(dbMapInThisHandler, memberCoAml.getAmlTradeSuitability(),"amlTradeSuitability");
        dealJsonArray(dbMapInThisHandler, memberCoAml.getAmlPolicy(), "amlPolicy");
        dbMapInThisHandler = MapUtil.appendKeysPrefix(dbMapInThisHandler, PdfDaoDbNameConstant.CO_AML);
        dbDataMap.putAll(dbMapInThisHandler);
    }

    private void dealJsonArray(HashMap<String, Object> dbMapInThisHandler, JSONArray jsonArray, String colunm) {
        if (ObjectUtils.isEmpty(jsonArray)) {
            log.error("dealJsonArray Error ==>"+ colunm + "jsonArray is null");
            return;
        }
        // 需要勾选Yes，存放数据的key
        String columnY = colunm + "Y";
        List<String> yesKeys = new ArrayList<>();
        // 需要勾选No，存放的数据key
        String columnN = colunm + "N";
        List<String> noKeys = new ArrayList<>();
        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            Integer i = Integer.valueOf((String)jsonObject.get("index"));
            String val = (String) jsonObject.get("val");
            if (ObjectUtils.isEmpty(i) || ObjectUtils.isEmpty(val)) {
                log.error("dealJsonArray Error ==>"+ colunm + "jsonArray field index or val is null");
                continue;
            }
            // 当前的大小都小于10，加前缀'0'
            if ("Y".equals(val)) {
                yesKeys.add("0" + i);
            } else if ("N".equals(val)) {
                noKeys.add("0" + i);
            }
            log.error("dealJsonArray Error ==>column:" + colunm + "index:" + i + "value nither Y or N");
        }
        dbMapInThisHandler.put(columnY, String.join(",", yesKeys));
        dbMapInThisHandler.put(columnN, String.join(",", noKeys));
    }

    public static void main(String[] args) {
        CoAmlDaoHandler coAmlDaoHandler = new CoAmlDaoHandler();
        coAmlDaoHandler.handle("any", new HashMap<>());
    }


}
