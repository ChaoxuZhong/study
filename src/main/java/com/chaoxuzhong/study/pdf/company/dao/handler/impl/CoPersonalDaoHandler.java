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
        directorPersonelsList.forEach(director->{
            directorSeq.add();
            HashMap<String, Object> itemDirectorMap = MapUtil.objToMap(director);
            itemDirectorMap = MapUtil.appendKeysPrefix(itemDirectorMap, CoPersonnelType.director.getKey());
            itemDirectorMap = MapUtil.appendKeysPrefix(itemDirectorMap, PdfDaoDbNameConstant.CO_PERSONAL);
            itemDirectorMap = MapUtil.appendKeysSuffix(itemDirectorMap, directorSeq.getSequence());
            dbDataMap.putAll(itemDirectorMap);
        });

        // 股东
        List<MemberCoPersonnel> shareholderPersonelsList = coPersonels.stream().
                filter(item -> CoPersonnelType.shareholder.getKey().equals(item.getType())).collect(Collectors.toList());
        SequenceStr shareholerSeq = SequenceStr.init();
        shareholderPersonelsList.forEach(shareholder->{
            shareholerSeq.add();
            HashMap<String, Object> itemShareholderMap = MapUtil.objToMap(shareholder);
            // 是否有亲属关系  复选框：key 中contain value ，自动勾选本复选框
            if (StringUtils.isEmpty(shareholder.getShareFamily())) {
                itemShareholderMap.put("shareholderFamilyNo", shareholerSeq.getSequence());
            }else{
                itemShareholderMap.put("shareholderFamilyYes", shareholerSeq.getSequence());
            }
            // 默认勾选不是美国人
            itemShareholderMap.put("shareholderRelativeUsNo", shareholerSeq.getSequence());

            itemShareholderMap = MapUtil.appendKeysPrefix(itemShareholderMap, CoPersonnelType.shareholder.getKey());
            itemShareholderMap = MapUtil.appendKeysPrefix(itemShareholderMap, PdfDaoDbNameConstant.CO_PERSONAL);
            itemShareholderMap = MapUtil.appendKeysSuffix(itemShareholderMap, shareholerSeq.getSequence());
            dbDataMap.putAll(itemShareholderMap);
        });

        // 最终实际利益拥有人
        coPersonels.stream().
                filter(item -> CoPersonnelType.beneficial.getKey().equals(item.getType())).findFirst().ifPresent(coPersonnel -> {
            HashMap<String, Object>  beneficialMap = MapUtil.objToMap(coPersonnel);
            String identityType = (String)beneficialMap.get("identityType");
            if (!StringUtils.isEmpty(identityType)) {
                if (identityType.equals("2")) { // 护照的枚举
                    beneficialMap.put("passportNo", coPersonnel.getIdentityNo());
                    beneficialMap.put("passportPlace", coPersonnel.getIdentityPlace());
                }else{
                    beneficialMap.put("idCard", coPersonnel.getIdentityNo());
                    beneficialMap.put("idcardPlace", coPersonnel.getIdentityPlace());
                }
            }
            beneficialMap = MapUtil.appendKeysPrefix(beneficialMap, CoPersonnelType.beneficial.getKey());
            beneficialMap = MapUtil.appendKeysPrefix(beneficialMap, PdfDaoDbNameConstant.CO_PERSONAL);
            dbDataMap.putAll(beneficialMap);
        });

        return;


    }



}
