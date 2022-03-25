package com.chaoxuzhong.study.pdf.company.mockdao;

import cn.hutool.json.JSONUtil;
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
        // 1-9 投资目的
        memberCoInfo.setObjective("01,02,03,04,05,06,07,08");
        memberCoInfo.setObjectiveInvestment("01,02,03,04,05");
        memberCoInfo.setObjectiveInvestmentPeriod("01,02,03");
        memberCoInfo.setObjectiveRisk("01,02,03,04");
//        memberCoInfo.setObjectivesOther("");
        // 1-12 通讯方式
        memberCoInfo.setPostalType("01,02,03,04");
        memberCoInfo.setPostalOtherAddress("其他通讯方式");
        // 1-14 披露关联账户
        memberCoInfo.setIdsRelativesWhether("Y");
        memberCoInfo.setIdsRelativesName("董事姓名");
        memberCoInfo.setIdsRelativesRelation("关系啊关系");
        memberCoInfo.setIdsAdenCustWhether("Y");
        memberCoInfo.setIdsAdenCustName("客户姓名");
        memberCoInfo.setIdsAdenCustAccount("123321123");
        memberCoInfo.setIdsControlVoteWhether("Y");
        memberCoInfo.setIdsControlVoteName("受控制客户名称");
        memberCoInfo.setIdsControlVoteAccount("受控制客户账号");
        memberCoInfo.setIdsOpenAccountWhether("Y");
        memberCoInfo.setIdsOrganDirectorWhether("N");
        memberCoInfo.setIdsOrganDirectorName("注册机构名称");
        memberCoInfo.setIdsOrganDirectorAccount("注册机构号码");
        memberCoInfo.setIdsPartyWhether("N");

        // 自我证明
        memberCoInfo.setProofLegalName("法定名称");
        memberCoInfo.setProofJurisdiction("税务管辖区");
        memberCoInfo.setProofRegisterNo("123321222");
        memberCoInfo.setProofBusinessAddress("现时营业地址");
        memberCoInfo.setProofBusinessArea("现时营业省市");
        memberCoInfo.setProofPostalCode("现时邮编");
        memberCoInfo.setProofPostalAddress("通讯地址");
        memberCoInfo.setProffPostalExtend("通讯邮政编码");
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
        memberInfoDetail.setIdCard("identityNo");
        memberInfoDetail.setFaxNo("123123123");
        // 1-7 财务状况
        memberInfoDetail.setAssetWorth("12345");
        // 1-10 衍生品认识
        memberInfoDetail.setDerivative("01,02,03,04");
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
        MemberCoPersonnel benificialItem = new MemberCoPersonnel();
        benificialItem.setEnName("enenName" );
        benificialItem.setRealName("realName");
        // 0-内地  1-港澳台  2 其他国家或者护照
        benificialItem.setIdentityType("1");
        benificialItem.setIdentityNo("identityNo");
        benificialItem.setIdentityPlace("identityPlace");
        benificialItem.setBirthDate(new Date());
        benificialItem.setAddress("address");
        benificialItem.setNationality("国籍");
        benificialItem.setBirthCountry("中国");
        benificialItem.setPhone("phone");
        benificialItem.setMaritalStatus("01");
        benificialItem.setNowAddress("现居住地");
        benificialItem.setNowCountry("现居住国家");
        benificialItem.setPerpetualAddress("永久地址");
        benificialItem.setHomePhone("住宅电话");
        benificialItem.setRegion("省市区");
        benificialItem.setPhone("手提电话");
        benificialItem.setEmail("test@test.com");
        benificialItem.setShareRatio(BigDecimal.ONE);
        benificialItem.setEstimatedControl(BigDecimal.TEN);
        benificialItem.setType(CoPersonnelType.beneficial.getKey());
        results.add(benificialItem);


        // 获授权人
        SequenceStr grantSeq = SequenceStr.init();
        for (int i = 0; i < 3; i++) {
            grantSeq.add();
            MemberCoPersonnel resultItem = new MemberCoPersonnel();
            resultItem.setEnName("name" + grantSeq.getSequence());
            resultItem.setRealName("name" + grantSeq.getSequence());
            resultItem.setIdentityNo("idCard" + grantSeq.getSequence());
            resultItem.setBirthDate(new Date());
            resultItem.setAddress("address" + grantSeq.getSequence());
            resultItem.setNationality("nationality" + grantSeq.getSequence());
            resultItem.setPhone("phone" + grantSeq.getSequence());
            resultItem.setShareRatio(BigDecimal.ONE);
            resultItem.setAuthorizedProject("A");
            resultItem.setWechat("wechat" + grantSeq.getSequence());

            resultItem.setType(CoPersonnelType.grant.getKey());
            results.add(resultItem);
        }

        // 控权人
        SequenceStr controlSeq = SequenceStr.init();
        for (int i = 0; i < 8; i++) {
            controlSeq.add();
            MemberCoPersonnel resultItem = new MemberCoPersonnel();
            resultItem.setRealName("控权人" + controlSeq.getSequence());
            resultItem.setType(CoPersonnelType.control.getKey());

            results.add(resultItem);
        }


        return results;
    }


    public MemberCoUsa getMemberUsa() {
        // 1-16 美国人身份证明
        MemberCoUsa memberCoUsa = new MemberCoUsa();
        memberCoUsa.setUsaWhether("Y");
        memberCoUsa.setUsaTaxpayerNo("1233211234567");
        memberCoUsa.setUsaMeetCondition("1,2,3,4,5,6,7");
        // 1-17 外国账户税务合规
        memberCoUsa.setUsaControlPower("N");
        memberCoUsa.setUsaNoOverseas("N");
        memberCoUsa.setUsaOverseasIdentity("1,2,3,4");
        memberCoUsa.setUsaTax("2389");
        memberCoUsa.setUsaMiddleman("43589");
        // 1-18 自行认证
        memberCoUsa.setUsaSelfAppoint("Y");
        memberCoUsa.setUsaSelfFinancial("Y");
        memberCoUsa.setUsaSelfTax("usaSelf");

        return memberCoUsa;
    }


    public MemberCoAml getMemberCoAml() {
        MemberCoAml memberCoAml = new MemberCoAml();
        memberCoAml.setAmlFatca(JSONUtil.parseArray("[{\"index\":\"1\",\"val\":\"Y\",\"desc\":\"原因:val为N时填写\"},{\"index\":\"2\",\"val\":\"N\",\"desc\":\"原因:val为N时填写\"}]"));
        return memberCoAml;
    }

    public Object getJurisdiction() {
        List<MemberJurisdiction> memberJurisdictions = new ArrayList<>();
        SequenceStr sequenceStr = SequenceStr.init();
        for (int i = 0; i < 5; i++) {
            sequenceStr.add();
            MemberJurisdiction memberJurisdiction = new MemberJurisdiction();
            memberJurisdiction.setJurisdiction("jurisdiction" + sequenceStr.getSequence());
            memberJurisdiction.setTaxNo("taxNo" + sequenceStr.getSequence());
            memberJurisdiction.setTaxCauseSelect("select" + sequenceStr.getSequence());
            memberJurisdiction.setTaxDesc("taxDesc" + sequenceStr.getSequence());
            memberJurisdictions.add(memberJurisdiction);
        }
        return memberJurisdictions;


    }
}
