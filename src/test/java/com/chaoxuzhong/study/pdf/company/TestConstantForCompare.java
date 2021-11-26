package com.chaoxuzhong.study.pdf.company;

import java.math.BigDecimal;
import java.util.Random;

public class TestConstantForCompare {
    public static final Random RANDOM_GENERATOR = new Random();

    // MOCK TEST DB NAME
    public static final String TESTDAO_NORMAL_DBNAME = "TESTDAO_NORMAL_DBNAME";
    public static final String TESTDAO_RETURNLIST_DBNAME = "TESTDAO_RETURNLIST_DBNAME";
    public static final String TESTDAO_IN_CHAIN_ONE_DBNAME = "TESTDAO_IN_CHAIN_ONE_DBNAME";
    public static final String TESTDAO_IN_CHAIN_TWO_DBNAME = "TESTDAO_IN_CHAIN_TWO_DBNAME";

    // MOCK TEST HANDLER NAME
    public static final String TESTDAO_NORMAL_HANDLER = "TESTDAO_NORMAL_HANDLER";
    public static final String TESTDAO_RETURNLIST_HANDLER = "TESTDAO_RETURNLIST_HANDLER";
    public static final String TESTDAO_IN_CHAIN_ONE_HANDLER = "TESTDAO_IN_CHAIN_ONE_HANDLER";
    public static final String TESTDAO_IN_CHAIN_TWO_HANDLER = "TESTDAO_IN_CHAIN_TWO_HANDLER";

    // 数据库普通单层数据对象返回值
    public static final String DBNORMAL_STRING = "DBNORMAL_STRING";
    public static final Integer DBNORMAL_INTEGER = getRandomInteger();
    public static final BigDecimal DBNORMAL_BIGDECIMAL = getRandomBigDecimal();
    public static final Long DBNORMAL_ID = Long.valueOf(RANDOM_GENERATOR.nextInt());

    // 数据库List对象返回值 item1
    public static final String DBNORMAL_STRING_ITEM_ONE = "DBNORMAL_STRING_ITEM_ONE";
    public static final Integer DBNORMAL_INTEGER_ITEM_ONE = getRandomInteger();
    public static final BigDecimal DBNORMAL_BIGDECIMAL_ITEM_ONE = getRandomBigDecimal();
    public static final Long DBNORMAL_ID_ITEM_ONE = getRandomLong();

    // 数据库List对象返回值 item2
    public static final String DBNORMAL_STRING_ITEM_TWO = "DBNORMAL_STRING_ITEM_ONE";
    public static final Integer DBNORMAL_INTEGER_ITEM_TWO = getRandomInteger();
    public static final BigDecimal DBNORMAL_BIGDECIMAL_ITEM_TWO = getRandomBigDecimal();
    public static final Long DBNORMAL_ID_ITEM_TWO = getRandomLong();

    // chain handler1 返回数据
    public static final String DBINCHAIN_STRING_ONE = getRandomInteger().toString();
    // chain hander2 返回数据
    public static final String DBINCHAIN_STRING_TWO = getRandomInteger().toString();

    private static Integer getRandomInteger() {
        return RANDOM_GENERATOR.nextInt();
    }

    private static BigDecimal getRandomBigDecimal() {
        return BigDecimal.valueOf(getRandomInteger());
    }

    private static Long getRandomLong() {
        return Long.valueOf(getRandomInteger());
    }

}
