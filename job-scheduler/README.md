# spring-boot-quartz-scheduler
Spring Boot Scheduler application that uses the Quartz framework


	
		API : /schedulerService/start
		
		Method : POST
		
		Request
				{
					"event": {
						"jobName": "SampleRestJobPOST",
						"apiURL": "http://localhost:9020/oce/api/createorder",
						"jobSchedule": "0/25 * * 1/1 * ? *",
						"apiMethod": "POST",
						"jobDescription": "SampleRestJobPOST"
					},
					"data": {
				
					}
				
				}