package com.att.oce.service;

import com.att.oce.model.JobSchedulerRequest;
import com.att.oce.model.JobSchedulerResponse;

public interface JobSchedulerService {

	public JobSchedulerResponse doScheduleJob(JobSchedulerRequest jobSchedulerRequest);
	public void stopScheduler();
	public void startScheduler();
}
