package com.att.oce.service;

import com.att.oce.model.JobSchedulerRequest;
import com.att.oce.model.JobSchedulerResponse;
import com.att.oce.model.JobStatus;

public interface JobSchedulerService {

	public JobSchedulerResponse doScheduleJob(JobSchedulerRequest jobSchedulerRequest);
	public void stopScheduler();
	public void startScheduler();
	public void updateSchedulerResponse(JobStatus jobStatus);
}
