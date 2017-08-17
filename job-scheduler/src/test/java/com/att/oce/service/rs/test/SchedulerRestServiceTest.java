package com.att.oce.service.rs.test;

import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.att.oce.model.*;
import com.att.oce.service.rs.SchedulerRestService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchedulerRestServiceTest {

	 	@Autowired
	    private SchedulerRestService service;

	    @Test
	    public void scheduleJobTest() throws Exception {
	    	JobSchedulerRequest jobSchedulerRequest = new JobSchedulerRequest();
	    	JobData jobData = new JobData();
	    	JobEvent jobEvent = new JobEvent();
	    	
	    	jobEvent.setApiMethod("GET");
	    	jobEvent.setJobName("SampleRestJobABC1");
	    	jobEvent.setJobDescription("SampleRestJobAB1");
	    	jobEvent.setApiURL("http://localhost:9011/oce/api/orders");
	    	jobEvent.setJobSchedule("0/11 * * 1/1 * ? *");   	
	    	
	    	jobSchedulerRequest.setEvent(jobEvent);
	    	jobSchedulerRequest.setData(jobData);

	    	Response response = service.scheduleJob(jobSchedulerRequest);
	    	System.out.println("response "+response);
	    	
	    	Thread.sleep(100000);
	    	
	    	service.doStopScheduler();
	    }
}
