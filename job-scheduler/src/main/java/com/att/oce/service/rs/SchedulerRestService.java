package com.att.oce.service.rs;

import javax.ws.rs.core.Response;

import com.att.oce.model.JobSchedulerRequest;

public interface SchedulerRestService {
	    
    public Response scheduleJob(JobSchedulerRequest jobSchedulerRequest) ;
    public Response doStopScheduler() ;
    public Response doStartScheduler() ;

    
}
