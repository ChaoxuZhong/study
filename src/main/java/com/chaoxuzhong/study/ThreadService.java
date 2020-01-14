package com.chaoxuzhong.study;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ThreadService {
    private Logger logger = LoggerFactory.getLogger(ThreadService.class);

    public void createException(String threadName) {
        List<Integer> testList = new ArrayList();
        for(int i=0; i<= 2000; i++){
            testList.add(i);
        }

        for (int item : testList) {
            try {
                if (1000 != (item)) {
                    throw new IllegalStateException("Connection pool shut down");
            }
            logger.info(threadName + ":" + item);
            } catch (Exception e) {
                logger.error(threadName + ":" + item + ":{}", e.getMessage());
            }
        }
    }
}
