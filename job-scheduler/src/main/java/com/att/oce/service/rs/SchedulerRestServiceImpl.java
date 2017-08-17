package com.att.oce.service.rs;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.att.oce.model.JobSchedulerRequest;
import com.att.oce.model.JobSchedulerResponse;
import com.att.oce.model.JobStatus;
import com.att.oce.service.JobSchedulerService;

@RestController
@RequestMapping("/schedulerService")
public class SchedulerRestServiceImpl implements SchedulerRestService {
	
	private static final Logger LOG = LoggerFactory.getLogger(SchedulerRestServiceImpl.class);
	
	@Autowired
	private JobSchedulerService jobSchedulerService;
	
	@Override
	@RequestMapping(method = RequestMethod.POST, value = "/schedule")
	public Response scheduleJob(@RequestBody JobSchedulerRequest jobSchedulerRequest) {
		JobSchedulerResponse jobSchedulerResponse = jobSchedulerService.doScheduleJob(jobSchedulerRequest);
		
		return Response.ok().entity(jobSchedulerResponse)
				.build();
	}

	@Override
	@RequestMapping(method = RequestMethod.POST, value = "/stop")
	public Response doStopScheduler() {

		jobSchedulerService.stopScheduler();
		return Response.ok().entity("Scheduler stopped succesfully!").build();
	}

	
	@Override
	@RequestMapping(method = RequestMethod.POST, value = "/start")
	public Response doStartScheduler() {

		jobSchedulerService.startScheduler();
		return Response.ok().entity("Scheduler started succesfully!").build();
	}

	
	@Override
	@RequestMapping(method = RequestMethod.POST, value = "/updateStatus")
	public Response updateSchedulerResponse(@RequestBody JobStatus jobStatus) {

		jobSchedulerService.updateSchedulerResponse(jobStatus);
		return Response.ok().entity(jobStatus).build();
	}
	
}
