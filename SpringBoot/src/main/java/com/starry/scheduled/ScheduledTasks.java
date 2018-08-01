package com.starry.scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName ScheduledTasks
 * @Description 创建Spring定时任务
 * @author yafei.qin
 * @date 2018年4月4日 下午6:18:11
 * 
 * @Copyright: 2012-2018 www.hirain.com Inc. All rights reserved.
 */
@Component
public class ScheduledTasks {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * @Scheduled注解定义一个方法的执行周期
     * @Description TODO(每5s定时任务)
     * @author yafei.qin void
     */
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        LOGGER.info("The time is now {}", dateFormat.format(new Date()));
    }
}
