package com.att.oce.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class JobData {

	private Map<String, Object> dataMap = new HashMap<String, Object>();

	@JsonAnyGetter
	public Map<String, Object> any() {
	return dataMap;
	}

	@JsonAnySetter
	public void set(String name, Object value) {
	this.dataMap.put(name, value);
	}
}
