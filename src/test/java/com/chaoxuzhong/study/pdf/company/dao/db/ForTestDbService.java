package com.chaoxuzhong.study.pdf.company.dao.db;


import com.chaoxuzhong.study.pdf.company.TestConstantForCompare;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ForTestDbService {

    public DbNormal getNormalDb(){
        DbNormal dbNormal = new DbNormal();
        dbNormal.setId(TestConstantForCompare.DBNORMAL_ID);
        dbNormal.setInteger(TestConstantForCompare.DBNORMAL_INTEGER);
        dbNormal.setBigDecimal(TestConstantForCompare.DBNORMAL_BIGDECIMAL);
        dbNormal.setString(TestConstantForCompare.DBNORMAL_STRING);
        return dbNormal;
    }

    public List<DbNormal> getDbNormalList() {
        List<DbNormal> result = new ArrayList<>();

        DbNormal dbNormal = new DbNormal();
        dbNormal.setId(TestConstantForCompare.DBNORMAL_ID_ITEM_ONE);
        dbNormal.setInteger(TestConstantForCompare.DBNORMAL_INTEGER_ITEM_ONE);
        dbNormal.setBigDecimal(TestConstantForCompare.DBNORMAL_BIGDECIMAL_ITEM_ONE);
        dbNormal.setString(TestConstantForCompare.DBNORMAL_STRING_ITEM_ONE);

        DbNormal dbNormal2 = new DbNormal();
        dbNormal2.setId(TestConstantForCompare.DBNORMAL_ID_ITEM_TWO);
        dbNormal2.setInteger(TestConstantForCompare.DBNORMAL_INTEGER_ITEM_TWO);
        dbNormal2.setBigDecimal(TestConstantForCompare.DBNORMAL_BIGDECIMAL_ITEM_TWO);
        dbNormal2.setString(TestConstantForCompare.DBNORMAL_STRING_ITEM_TWO);

        result.add(dbNormal);
        result.add(dbNormal2);
        return result;
    }

    public DbInChain getDbOneInChain() {
        DbInChain dbInChain = new DbInChain();
        dbInChain.setChainStr(TestConstantForCompare.DBINCHAIN_STRING_ONE);
        return dbInChain;
    }

    public DbInChain getDbTwoInChain() {
        DbInChain dbInChain = new DbInChain();
        dbInChain.setChainStr(TestConstantForCompare.DBINCHAIN_STRING_TWO);
        return dbInChain;
    }

}
