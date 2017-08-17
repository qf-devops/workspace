package com.att.oce.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.att.oce.model.JobStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Repository
public class JobSchedulerTemplate  {

    private static final Logger LOG = LoggerFactory.getLogger(JobSchedulerTemplate.class);

    private String jobUpdateQuery = "UPDATE JOB_HISTORY SET END_TIME = ? , STATUS = ? , SUMMARY = ? where CORRELATION_ID = ?";

    @Autowired
    private JdbcTemplate jobJdbcTemplate;

    public void updateJobStatus(JobStatus jobStatus) {
        LOG.info("Updating job execution end date time in the store");
        String correlationId = jobStatus.getCorrelationId();
        String status = jobStatus.getJobStatus();
        String summary = jobStatus.getJobSummary();
        Date endTime = new Date();
        Date endTimeInGmt = getGMTTime(endTime);
        try {
            jobJdbcTemplate.update(jobUpdateQuery, new Object[]{endTimeInGmt, status, summary, correlationId});
        } catch (DataAccessException ex) {
            LOG.error("Failed to update job completion data to store.", ex);
        }
    }

    private Date getGMTTime(Date date) {
        Date gmtTime = null;
        if(null != date) {
            SimpleDateFormat dateFormatGmt = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
            dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
            //Local time zone
            SimpleDateFormat dateFormatLocal = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
            //Time in GMT
            try {
                gmtTime = dateFormatLocal.parse(dateFormatGmt.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return gmtTime;
    }
}