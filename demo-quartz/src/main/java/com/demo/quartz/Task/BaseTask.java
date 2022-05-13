package com.demo.quartz.Task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @Description 定时任务
 * @Author 故人听雨
 * @Date 2022/5/13 23:56
 */
public class BaseTask implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("定时任务执行.....: " + new Date());
    }


}
