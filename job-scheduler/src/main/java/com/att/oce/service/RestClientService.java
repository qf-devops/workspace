package com.att.oce.service;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.att.oce.util.JobSchedulerConstant;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RestClientService {

    private static final Logger LOG = LoggerFactory.getLogger(RestClientService.class);

    @SuppressWarnings("unused")
	public void invokeRestService(String apiUrl,String apiMethod, String data) {
    	DefaultHttpClient httpClient = new DefaultHttpClient();
    	ObjectMapper mapper = new ObjectMapper();
    	
    	if(apiMethod.equalsIgnoreCase(JobSchedulerConstant.METHOD_POST)){
    		HttpPost httpRequest = new HttpPost(apiUrl);
        	String inputData;
    		try {
    			if (!StringUtils.isEmpty(data)) {
    				StringEntity entity = new StringEntity(data, "UTF-8");
    				entity.setContentType("application/json;charset=UTF-8");
    				// Set the request post body
    				httpRequest.setEntity(entity);
    			}
    			HttpResponse response = httpClient.execute(httpRequest);
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}else if (apiMethod.equalsIgnoreCase(JobSchedulerConstant.METHOD_GET)){
    		try {
    			HttpGet httpRequest = new HttpGet(apiUrl);
    			HttpResponse response = httpClient.execute(httpRequest);
    			System.out.println("response : "+response.getEntity());
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}else if (apiMethod.equalsIgnoreCase(JobSchedulerConstant.METHOD_PUT)){
    		HttpPut httpRequest = new HttpPut(apiUrl);
        	String inputData;
    		try {
    			if (!StringUtils.isEmpty(data)) {
    				StringEntity entity = new StringEntity(data, "UTF-8");
    				entity.setContentType("application/json;charset=UTF-8");
    				// Set the request post body
    				httpRequest.setEntity(entity);
    			}
    			HttpResponse response = httpClient.execute(httpRequest);
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    		
    	
    
		
    	
    }
}
