package com.att.oce.model;

public class JobSchedulerRequest {

	private JobEvent event;
	private JobData data;
	public JobEvent getEvent() {
		return event;
	}
	public void setEvent(JobEvent event) {
		this.event = event;
	}
	public JobData getData() {
		return data;
	}
	public void setData(JobData data) {
		this.data = data;
	}
}
