package org.geilove.timedTask;

/**
 * 测试定时任务
 */

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskDemo {

    @Scheduled(cron = "0 54 15 ? * *")
    public void show(){
        System.out.println("Annotation：is show run");
    }

    @Scheduled(fixedRate = 1000*2)
    public void print(){
        //System.out.println("Annotation：print run");
    }
}