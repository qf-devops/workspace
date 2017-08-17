package com.att.oce.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

/**
 * Created by jt316g on 8/15/2017.
 */
public class ScheduleJobListener implements JobListener {

    private static final Logger LOG = LoggerFactory.getLogger(ScheduleJobListener.class);

    private String jobInsertQuery = "INSERT INTO JOB_HISTORY (JOB_NAME, JOB_DESC, FIRE_TIME, CORRELATION_ID , STATUS) VALUES (?, ?, ?, ?, ?)";
    private String jobUpdateQuery = "UPDATE JOB_HISTORY SET STATUS = ? where CORRELATION_ID = ?";

    private JdbcTemplate jobJdbcTemplate;

    public ScheduleJobListener(JdbcTemplate jobJdbcTemplate) {
        this.jobJdbcTemplate = jobJdbcTemplate;
    }

    @Override
    public String getName() {
        return "ScheduleJobListener";
    }

    @Async
    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        LOG.info("Inserting job execution initialization data in to the store");
        String correlationId = UUID.randomUUID().toString();
        jobExecutionContext.put("correlationId", correlationId);
        String jobName = jobExecutionContext.getJobDetail().getKey().getName();
        String jobDescription = jobExecutionContext.getJobDetail().getDescription();
        Date fireTime = getGMTTime(jobExecutionContext.getFireTime());
        try {
            jobJdbcTemplate.update(jobInsertQuery, new Object[]{jobName,
                    jobDescription, fireTime, correlationId , "TRIGGERED"
            });
        } catch (DataAccessException ex) {
            LOG.error("Failed to insert job initialization data to store.", ex);
        }
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {

    }

    @Async
    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        LOG.info("Updating job execution end date time in the store");
        String correlationId = (String) jobExecutionContext.get("correlationId");
        try {
            jobJdbcTemplate.update(jobUpdateQuery, new Object[]{"STARTED", correlationId});
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