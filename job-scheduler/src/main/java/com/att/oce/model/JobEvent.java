package com.att.oce.model;

public class JobEvent {

	private String jobName;
	private String apiURL;
	private String jobSchedule;
	private String apiMethod;
	private String jobDescription;
	
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getApiURL() {
		return apiURL;
	}
	public void setApiURL(String apiURL) {
		this.apiURL = apiURL;
	}
	public String getJobSchedule() {
		return jobSchedule;
	}
	public void setJobSchedule(String jobSchedule) {
		this.jobSchedule = jobSchedule;
	}
	public String getApiMethod() {
		return apiMethod;
	}
	public void setApiMethod(String apiMethod) {
		this.apiMethod = apiMethod;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
}
