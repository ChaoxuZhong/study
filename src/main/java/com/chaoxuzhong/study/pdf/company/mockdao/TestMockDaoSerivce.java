package com.chaoxuzhong.study.pdf.company.mockdao;

import com.chaoxuzhong.study.pdf.company.dao.SequenceStr;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TestMockDaoSerivce {

    public MemberInfo getMemberInfo() {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMemberNo("testMemberNo");
        memberInfo.setRealname("testChName");
        memberInfo.setEmail("30000@test.com");
        memberInfo.setSex("0");

//        memberInfo.s

        return memberInfo;
    }

    public MemberCoInfo getMemberCoInfo(){
        MemberCoInfo memberCoInfo = new MemberCoInfo();
        memberCoInfo.setAccountType("02");
        memberCoInfo.setAccountService("02");
        memberCoInfo.setBusiness("01");
        memberCoInfo.setBusinessAddress("经营地址");
        memberCoInfo.setCoType("01,02,03,04,05");
        memberCoInfo.setCoTypeExtend("公司类别其它");
        memberCoInfo.setFdFundSource("HK,CHN,US,OTH");
        memberCoInfo.setFdFundSourceExtend("资金来源其它");
        memberCoInfo.setRegistrationNo("sydjz123");
        memberCoInfo.setBeneficiary("C");
        memberCoInfo.setEquityLegal("法定股本");
        memberCoInfo.setEquityParValue("法定股本每股面值");
        memberCoInfo.setEquityIssued("已发行股本");
        memberCoInfo.setEquityIssuedParValue("已发型股本每股面值");
        // 1-7财务状况
        memberCoInfo.setFdProfitAfterTaxLately(BigDecimal.TEN);
        memberCoInfo.setFdProfitAfterTaxPrevious(BigDecimal.TEN);
        memberCoInfo.setFdCurrentAssets(BigDecimal.TEN);
        memberCoInfo.setFdCapitalSource("01,02,03,04,05");
        memberCoInfo.setFdCapitalSourceExtend("资本来源-其他");
        memberCoInfo.setFdAnnualNetProfit("01,02,03,04,05,06,07");
        memberCoInfo.setFdAnnualNetProfitExtend("全年净利润-其他");
        memberCoInfo.setFdFundSource("HK,CHN,US,OTH");
        memberCoInfo.setFdFundSourceExtend("资金来源地-其他");
        // 1-8投资经验
        memberCoInfo.setIvStock("01,02,03,04,05");
        memberCoInfo.setIvFutures("01,02,03,04,05");
        memberCoInfo.setIvDerivative("01,02,03,04,05");
        memberCoInfo.setIvBond("01,02,03,04,05");
        memberCoInfo.setIvTrust("01,02,03,04,05");
        memberCoInfo.setIvEtf("01,02,03,04,05");
        memberCoInfo.setIvBond("01,02,03,04,05");
        memberCoInfo.setIvForeignCurrency("01,02,03,04,05");
        memberCoInfo.setIvOther("投资经验—其它");
        return memberCoInfo;
    }

    public MemberInfoDetail getMemberInfoDetail() {
        MemberInfoDetail memberInfoDetail = new MemberInfoDetail();
        memberInfoDetail.setSurname("testEnName");
        memberInfoDetail.setAddress("注册地址");
        memberInfoDetail.setCompanyAddress("办事地址");
        memberInfoDetail.setCompanyTel("13612312312");
        memberInfoDetail.setBirthCountry("中国");
        memberInfoDetail.setBirthDate(new Date());
        memberInfoDetail.setIdCard("gszch123");
        memberInfoDetail.setFaxNo("123123123");
        // 1-7 财务状况
        memberInfoDetail.setAssetWorth("12345");
//        memberInfoDetail.set
        return memberInfoDetail;
    }


    public List<MemberCoPersonnel> getMemberCoPersonnels() {
        List<MemberCoPersonnel> results = new ArrayList<>();
        SequenceStr sequenceStr = SequenceStr.init();
        // 董事
        for (int i = 0; i < 8; i++) {
            sequenceStr.add();
            MemberCoPersonnel resultItem = new MemberCoPersonnel();
            resultItem.setEnName("enName" + sequenceStr.getSequence());
            resultItem.setRealName("realName" + sequenceStr.getSequence());
            resultItem.setIdentityNo("identityNo" + sequenceStr.getSequence());
            resultItem.setBirthDate(new Date());
            resultItem.setAddress("address" + sequenceStr.getSequence());
            resultItem.setNationality("nationality" + sequenceStr.getSequence());
            resultItem.setPhone("phone" + sequenceStr.getSequence());
            resultItem.setType(CoPersonnelType.director.getKey());
            results.add(resultItem);
        }
        // 注册股东
        SequenceStr shareSeq = SequenceStr.init();
        for (int i = 0; i < 5; i++) {
            shareSeq.add();
            MemberCoPersonnel resultItem = new MemberCoPersonnel();
            resultItem.setEnName("name" + shareSeq.getSequence());
            resultItem.setRealName("name" + shareSeq.getSequence());
            resultItem.setIdentityNo("idCard" + shareSeq.getSequence());
            resultItem.setBirthDate(new Date());
            resultItem.setAddress("address" + shareSeq.getSequence());
            resultItem.setNationality("nationality" + shareSeq.getSequence());
            resultItem.setPhone("phone" + shareSeq.getSequence());
            resultItem.setShareRatio(BigDecimal.ONE);
            if (i == 4) {
                resultItem.setShareFamily("就是有你猜猜是谁");
            }
            resultItem.setType(CoPersonnelType.shareholder.getKey());
            results.add(resultItem);
        }

        // 最终权益拥有人
        MemberCoPersonnel resultItem = new MemberCoPersonnel();
        resultItem.setEnName("enenName" );
        resultItem.setRealName("realName");
        // 0-内地  1-港澳台  2 其他国家或者护照
        resultItem.setIdentityType("1");
        resultItem.setIdentityNo("identityNo");
        resultItem.setIdentityPlace("identityPlace");
        resultItem.setBirthDate(new Date());
        resultItem.setAddress("address");
        resultItem.setNationality("国籍");
        resultItem.setBirthCountry("中国");
        resultItem.setPhone("phone");
        resultItem.setMaritalStatus("01");
        resultItem.setNowAddress("现居住地");
        resultItem.setNowCountry("现居住国家");
        resultItem.setPerpetualAddress("永久地址");
        resultItem.setHomePhone("住宅电话");
        resultItem.setRegion("省市区");
        resultItem.setPhone("手提电话");
        resultItem.setEmail("test@test.com");
        resultItem.setShareRatio(BigDecimal.ONE);
        resultItem.setEstimatedControl(BigDecimal.TEN);


        resultItem.setType(CoPersonnelType.beneficial.getKey());
        results.add(resultItem);

        return results;
    }


}
