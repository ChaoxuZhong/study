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

public class TargetDataReader extends AnalysisEventListener<TargetData> {


    public List<TargetData> getList() {
        return list;
    }

    public void setList(List<TargetData> list) {
        this.list = list;
    }

    public void simpleRead(String fileName) {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
//        // 写法1：
//        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
//        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
//        EasyExcel.read(fileName, TargetData.class, new DemoDataListener()).sheet().doRead();

        // 写法2：
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(fileName, TargetData.class, new TargetDataReader()).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }
    }

    private Logger LOGGER = LoggerFactory.getLogger(TargetDataReader.class);
    // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<TargetData> list = new ArrayList<>();
//        /**
//         * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
//         */
//        private DemoDAO demoDAO;
//        public DemoDataListener() {
//            // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数
//            demoDAO = new DemoDAO();
//        }
//        /**
//         * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
//         *
//         * @param demoDAO
//         */
//        public DemoDataListener(DemoDAO demoDAO) {
//            this.demoDAO = demoDAO;
//        }
    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(TargetData data, AnalysisContext context) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        CacheList.addTargetList(data);
//            // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
//            if (list.size() >= BATCH_COUNT) {
//                saveData();
//                // 存储完成清理 list
//                list.clear();
//            }
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
    /**
     * 加上存储数据库
     */
//        private void saveData() {
//            LOGGER.info("{}条数据，开始存储数据库！", list.size());
//            demoDAO.save(list);
//            LOGGER.info("存储数据库成功！");
//        }


}
