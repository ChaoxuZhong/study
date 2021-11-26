package com.chaoxuzhong.study.pdf.company.dao;

import com.chaoxuzhong.study.pdf.company.dao.handler.IPdfDaoHandler;
import com.chaoxuzhong.study.pdf.company.util.SpringBeanUtil;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * PDF 数据层handler链，用于获取所需数据，灵活组合各handler
 */
@Getter
@Component
@Scope("prototype")
public class PdfDaoHandlerChain{

    /**
     * 链上的handler，因有此全局变量，Scope=prototype，且上层使用此方法时每次都通过ApliactionContext动态获取确保为new chain
     */
    private List<IPdfDaoHandler> pdfDaoHandlers = new ArrayList<>();

    public HashMap<String, Object> handle(String memberNo) {
        HashMap<String, Object> result = new HashMap<>();
        pdfDaoHandlers.forEach(handler -> {
            handler.handle(memberNo, result);
        });
        return result;
    }

    public PdfDaoHandlerChain addHandlers(List<String> daoHandlerNames){
        daoHandlerNames.forEach(daoHandlerName->{
            pdfDaoHandlers.add((IPdfDaoHandler) SpringBeanUtil.getBean(daoHandlerName));
        });
        return this;
    }

}
