package com.chaoxuzhong.study.pdf.company.mockdao;

import org.springframework.stereotype.Service;

@Service
public class TestMockDaoSerivce {

    public MemberInfo getMemberInfo() {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setMemberNo("testMemberNo");
        memberInfo.setRealname("testChName");
//        memberInfo.s

        return memberInfo;
    }

    public MemberCoInfo getMemberCoInfo(){
        MemberCoInfo memberCoInfo = new MemberCoInfo();
        memberCoInfo.setAccountType("02");
        memberCoInfo.setAccountService("02");
        memberCoInfo.setBusiness("01");
        memberCoInfo.setBusinessAddress("经营地址");

//        memberCoInfo.setEn
        return memberCoInfo;
    }

    public MemberInfoDetail getMemberInfoDetail() {
        MemberInfoDetail memberInfoDetail = new MemberInfoDetail();
        memberInfoDetail.setSurname("testEnName");
        memberInfoDetail.setAddress("注册地址");
        memberInfoDetail.setCompanyAddress("办事地址");
        return memberInfoDetail;
    }


}
