package com.chaoxuzhong.study.pdf.company.dao.handler.impl;

import com.chaoxuzhong.study.pdf.company.dao.SequenceStr;
import com.chaoxuzhong.study.pdf.company.dao.handler.IPdfDaoHandler;
import com.chaoxuzhong.study.pdf.company.dao.handler.PdfDaoDbNameConstant;
import com.chaoxuzhong.study.pdf.company.dao.handler.PdfDaoHandlerNameConstant;
import com.chaoxuzhong.study.pdf.company.mockdao.CoPersonnelType;
import com.chaoxuzhong.study.pdf.company.mockdao.MemberCoPersonnel;
import com.chaoxuzhong.study.pdf.company.mockdao.TestMockDaoSerivce;
import com.chaoxuzhong.study.pdf.company.util.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service(PdfDaoHandlerNameConstant.CO_PERSONAL)
public class CoPersonalDaoHandler implements IPdfDaoHandler {

    @Autowired
    private TestMockDaoSerivce testMockDaoSerivce;

    @Override
    public void handle(String memberNo, HashMap<String, Object> dbDataMap) {

        // 董事
        List<MemberCoPersonnel> coPersonels = testMockDaoSerivce.getMemberCoPersonnels();
        List<MemberCoPersonnel> directorPersonelsList = coPersonels.stream().
                filter(item -> CoPersonnelType.director.getKey().equals(item.getType())).collect(Collectors.toList());
        SequenceStr directorSeq = SequenceStr.init();
        directorPersonelsList.forEach(director -> {
            directorSeq.add();
            HashMap<String, Object> itemDirectorMap = MapUtil.objToMap(director);
            itemDirectorMap = listModifyKey(itemDirectorMap, CoPersonnelType.director, directorSeq);
            dbDataMap.putAll(itemDirectorMap);
        });

        // 股东
        List<MemberCoPersonnel> shareholderPersonelsList = coPersonels.stream().
                filter(item -> CoPersonnelType.shareholder.getKey().equals(item.getType())).collect(Collectors.toList());
        SequenceStr shareholerSeq = SequenceStr.init();
        shareholderPersonelsList.forEach(shareholder -> {
            shareholerSeq.add();
            HashMap<String, Object> itemShareholderMap = MapUtil.objToMap(shareholder);
            // 是否有亲属关系  复选框：key 中contain value ，自动勾选本复选框
            if (StringUtils.isEmpty(shareholder.getShareFamily())) {
                itemShareholderMap.put("shareholderFamilyNo", shareholerSeq.getSequence());
            } else {
                itemShareholderMap.put("shareholderFamilyYes", shareholerSeq.getSequence());
            }
            // 默认勾选不是美国人
            itemShareholderMap.put("shareholderRelativeUsNo", shareholerSeq.getSequence());
            itemShareholderMap = listModifyKey(itemShareholderMap, CoPersonnelType.shareholder, shareholerSeq);
            dbDataMap.putAll(itemShareholderMap);
        });

        // 最终实际利益拥有人
        coPersonels.stream().
                filter(item -> CoPersonnelType.beneficial.getKey().equals(item.getType())).findFirst().ifPresent(coPersonnel -> {
            HashMap<String, Object> beneficialMap = MapUtil.objToMap(coPersonnel);
            String identityType = (String) beneficialMap.get("identityType");
            if (!StringUtils.isEmpty(identityType)) {
                if (identityType.equals("2")) { // 护照的枚举
                    beneficialMap.put("passportNo", coPersonnel.getIdentityNo());
                    beneficialMap.put("passportPlace", coPersonnel.getIdentityPlace());
                } else {
                    beneficialMap.put("benificialIdCard", coPersonnel.getIdentityNo());
                    beneficialMap.put("idCardPlace", coPersonnel.getIdentityPlace());
                }
            }
            beneficialMap = singleModifyKey(beneficialMap, CoPersonnelType.beneficial);
            dbDataMap.putAll(beneficialMap);
        });

        // 获授权人
        List<MemberCoPersonnel> grantPersonelsList = coPersonels.stream().
                filter(x -> CoPersonnelType.grant.getKey().equals(x.getType())).collect(Collectors.toList());
        SequenceStr grantSeq = SequenceStr.init();
        grantPersonelsList.forEach(coPersonel -> {
            grantSeq.add();
            HashMap<String, Object> grantMap = MapUtil.objToMap(coPersonel);
            grantMap = listModifyKey(grantMap, CoPersonnelType.grant, grantSeq);
            dbDataMap.putAll(grantMap);
        });

        // 控权人
        List<MemberCoPersonnel> controlPersonelsList = coPersonels.stream().
                filter(x -> CoPersonnelType.control.getKey().equals(x.getType())).collect(Collectors.toList());
        SequenceStr controlSeq = SequenceStr.init();
        controlPersonelsList.forEach(coPersonel -> {
            controlSeq.add();
            HashMap<String, Object> controlMap = MapUtil.objToMap(coPersonel);
            controlMap = listModifyKey(controlMap, CoPersonnelType.control, controlSeq);
            dbDataMap.putAll(controlMap);
        });
        return;

    }

    private HashMap<String, Object> singleModifyKey(HashMap<String, Object> map, CoPersonnelType type) {
        map = MapUtil.appendKeysPrefix(map, type.getKey());
        map = MapUtil.appendKeysPrefix(map, PdfDaoDbNameConstant.CO_PERSONAL);
        return map;
    }

    private HashMap<String, Object> listModifyKey(HashMap<String, Object> map, CoPersonnelType type, SequenceStr sequenceStr) {
        map = MapUtil.appendKeysPrefix(map, type.getKey());
        map = MapUtil.appendKeysPrefix(map, PdfDaoDbNameConstant.CO_PERSONAL);
        map = MapUtil.appendKeysSuffix(map, sequenceStr.getSequence());
        return map;
    }

}
