package com.chaoxuzhong.study.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SourceDateReader extends AnalysisEventListener<SourceData> {


    public void simpleRead(String fileName) {
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(fileName, SourceData.class, new SourceDateReader()).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }
    }

    private Logger LOGGER = LoggerFactory.getLogger(SourceDateReader.class);
    // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        /**
         * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
         */
        private static final int BATCH_COUNT = 5;

    public List<SourceData> getList() {
        return list;
    }

    public void setList(List<SourceData> list) {
        this.list = list;
    }

    private List<SourceData> list = new ArrayList<>();
        /**
         * 这个每一条数据解析都会来调用
         *
         * @param data
         *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
         * @param context
         */
        @Override
        public void invoke(SourceData data, AnalysisContext context) {
            LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
            CacheList.addSourceList(data);
        }
        /**
         * 所有数据解析完成了 都会来调用
         *
         * @param context
         */
        @Override
        public void doAfterAllAnalysed(AnalysisContext context) {
            // 这里也要保存数据，确保最后遗留的数据也存储到数据库
//            saveData();
            LOGGER.info("所有数据解析完成！");
        }
}
