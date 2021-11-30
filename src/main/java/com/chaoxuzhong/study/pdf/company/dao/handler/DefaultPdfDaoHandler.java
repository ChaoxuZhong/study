package com.chaoxuzhong.study.pdf.company.dao.handler;

import com.chaoxuzhong.study.pdf.company.dao.SequenceStr;
import com.chaoxuzhong.study.pdf.company.util.MapUtil;
import org.springframework.util.CollectionUtils;

import java.util.*;

public abstract class DefaultPdfDaoHandler implements IPdfDaoHandler {

    private static final ArrayList EMPTY_LIST = new ArrayList();

    @Override
    public void handle(String memberNo, HashMap<String, Object> dbDataMap) {
        Object dbData = getDataFromDb(memberNo);
        HashMap<String, Object> dbMapInThisHandler = new HashMap<>();
        if (dbData instanceof Collection) {
            SequenceStr sequenceStr = SequenceStr.init();
            HashMap<String, Object> finalDbMapInThisHandler = dbMapInThisHandler;
            ((Collection<?>) dbData).stream().forEach(o -> {
                HashMap<String, Object> itemMap = convertDbDataToMap(o);
                finalDbMapInThisHandler.putAll(MapUtil.appendKeysSuffix(itemMap, sequenceStr.add().getSequence()));
            });
        }else{
            dbMapInThisHandler = convertDbDataToMap(dbData);
        }
        convertDictCodeToChName(dbDataMap);
        dealSpecialKeys(dbMapInThisHandler);

        dbMapInThisHandler = MapUtil.appendKeysPrefix(dbMapInThisHandler, getDbName());
        dbDataMap.putAll(dbMapInThisHandler);
    }

    /**
     * 处理需要处理的特殊数据——默认什么都不做，继承类自己决定
     * @param dbDataMap
     */
    protected void dealSpecialKeys(HashMap<String, Object> dbDataMap){
        return;
    }

    /**
     * 转换字典为对应中文——默认什么都不做，继承类自己决定干什么
     * @param dbDataMap
     */
    protected void convertDictCodeToChName(HashMap<String, Object> dbDataMap){
        return;
    }

    protected abstract String getDbName();

    protected abstract Object getDataFromDb(String memberNo);

    /**
     * 需要特殊处理的数据库域 —— 不直接转换成map
     * 默认没有需要特殊处理的域
     *
     * @return
     */
    protected List<String> specialDbField() {
        return EMPTY_LIST;
    }


    private HashMap<String, Object> convertDbDataToMap(Object dbData) {
        return hasSpecialDbField() ? MapUtil.objToMap(dbData, specialDbField()) : MapUtil.objToMap(dbData);
    }

    private boolean hasSpecialDbField() {
        return !CollectionUtils.isEmpty(specialDbField());
    }
}
