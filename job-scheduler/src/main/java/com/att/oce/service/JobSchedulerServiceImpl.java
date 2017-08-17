package com.att.oce.service;

import java.util.Date;

import com.att.oce.listener.ScheduleJobListener;
import org.quartz.*;
import org.quartz.impl.matchers.KeyMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.att.oce.job.ScheduleJob;
import com.att.oce.model.JobData;
import com.att.oce.model.JobEvent;
import com.att.oce.model.JobSchedulerRequest;
import com.att.oce.model.JobSchedulerResponse;
import com.att.oce.util.JobSchedulerConstant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JobSchedulerServiceImpl implements JobSchedulerService {
	
	private static final Logger LOG = LoggerFactory.getLogger(JobSchedulerServiceImpl.class);
	
	@Autowired
	private Scheduler scheduler;

	@Autowired
	private JdbcTemplate jobJdbcTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public JobSchedulerResponse doScheduleJob(
			JobSchedulerRequest jobSchedulerRequest) {
		
		JobSchedulerResponse jobSchedulerResponse = new JobSchedulerResponse();
		JobData jobData = jobSchedulerRequest.getData();
		JobEvent jobEvent = jobSchedulerRequest.getEvent();
		JobDataMap newJobDataMap = createJobData(jobData,jobEvent);
		
		JobDetail jobDetail = JobBuilder.newJob(ScheduleJob.class).withIdentity(jobEvent.getJobName()).withDescription(jobEvent.getJobDescription())
				.setJobData(newJobDataMap).storeDurably(true).build();

		Trigger trigger = TriggerBuilder.newTrigger().forJob(jobDetail)
				.withIdentity(jobEvent.getJobName()).withDescription(jobEvent.getJobDescription()).startNow()
				.withSchedule(craeteSchedule(jobEvent.getJobSchedule())).build();

		try {
			// Set up the listener
			JobListener listener = new ScheduleJobListener(jobJdbcTemplate);
			Matcher<JobKey> matcher = KeyMatcher.keyEquals(jobDetail.getKey());
			scheduler.getListenerManager().addJobListener(listener, matcher);

			Date schedulingInfo = scheduler.scheduleJob(jobDetail, trigger);
			jobSchedulerResponse.set("Status", "Success");
			jobSchedulerResponse.set("SchedulingInfo", schedulingInfo);

		} catch (SchedulerException e) {
			e.printStackTrace();
			jobSchedulerResponse.set("Status", "Failed");
		}
		jobSchedulerResponse.set("JobEvent", jobEvent);
		
		return jobSchedulerResponse;
	}
	
	
	private JobDataMap createJobData(JobData jobData,JobEvent jobEvent ){
		
		JobDataMap newJobDataMap = new JobDataMap();
		
		newJobDataMap.put(JobSchedulerConstant.JOB_NAME, jobEvent.getJobName());
		newJobDataMap.put(JobSchedulerConstant.API_URL, jobEvent.getApiURL());
		newJobDataMap.put(JobSchedulerConstant.API_METHOD, jobEvent.getApiMethod());
		newJobDataMap.put(JobSchedulerConstant.JOB_SCHEDULE, jobEvent.getJobSchedule());	
		
		try {
			if (null != jobData) {
				ObjectMapper mapper = new ObjectMapper();
				String inputData;
				inputData = (String) mapper.writeValueAsString(jobData.any());
				newJobDataMap.put(JobSchedulerConstant.JOB_DATA, inputData);
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return newJobDataMap;

	}
	
	@SuppressWarnings("rawtypes")
	private static ScheduleBuilder craeteSchedule(String cronExpression) {
		CronScheduleBuilder builder = CronScheduleBuilder
				.cronSchedule(cronExpression);
		return builder;
	}


	@Override
	public void stopScheduler() {
		try {
			scheduler.shutdown();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void startScheduler() {
		try {
			scheduler.start();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
