package com.chaoxuzhong.study.pdf.company.dao.handler;

import java.util.HashMap;

public interface IPdfDaoHandler {

    /**
     * PDF数据层生成
     * @param memberNo
     * @param dbDataMap
     */
    void handle(String memberNo, HashMap<String, Object> dbDataMap);
}
