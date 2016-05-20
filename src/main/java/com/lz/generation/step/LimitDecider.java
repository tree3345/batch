package com.lz.generation.step;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

/**
 * Created by Administrator on 2016/3/24.
 */
public class LimitDecider implements JobExecutionDecider {

    private int limit;

    private int count=0;
    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        jobExecution.getExecutionContext().put("key_a", "value_" + count);
        if (++count >= limit) {
            return new FlowExecutionStatus("COMPLETED");
        }
        else {
            return new FlowExecutionStatus("CONTINUE");
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
