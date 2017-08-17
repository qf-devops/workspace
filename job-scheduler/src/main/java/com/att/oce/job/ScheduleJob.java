package com.att.oce.job;


import com.att.oce.service.RestClientService;
import com.att.oce.util.JobSchedulerConstant;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ScheduleJob implements Job {
	
	private static final Logger LOG = LoggerFactory.getLogger(ScheduleJob.class);

    @Autowired
    private RestClientService restClientService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {    	
    	
    	JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
    	
    	restClientService.invokeRestService(jobDataMap.getString(JobSchedulerConstant.API_URL), jobDataMap.getString(JobSchedulerConstant.API_METHOD) , 
    			jobDataMap.getString(JobSchedulerConstant.JOB_DATA) , (String) jobExecutionContext.get("correlationId"));
        
    }
}
