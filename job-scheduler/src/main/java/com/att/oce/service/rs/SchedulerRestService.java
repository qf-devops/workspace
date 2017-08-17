package com.att.oce.service.rs;

import javax.ws.rs.core.Response;

import com.att.oce.model.JobSchedulerRequest;
import com.att.oce.model.JobStatus;

public interface SchedulerRestService {
	    
    public Response scheduleJob(JobSchedulerRequest jobSchedulerRequest) ;
    public Response doStopScheduler() ;
    public Response doStartScheduler() ;

    public Response updateSchedulerResponse(JobStatus jobStatus) ;
}
