package com.lz.generation.step;

import com.lz.constant.FactorTableGeneratorConstant;
import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

/**
 * Created by Administrator on 2016/3/24.
 */
public class SwitchListener implements StepExecutionListener {
    private final Logger log = Logger.getLogger(SwitchListener.class);

    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        String nextStepType = (String) stepExecution.getJobExecution().getExecutionContext().get("nextStepType");

        log.info("    step 'next'=" + nextStepType);

        if (FactorTableGeneratorConstant.JEN_TYPE_0.equals(nextStepType)) {
            return new ExitStatus(FactorTableGeneratorConstant.JEN_TYPE_0);
        } else {
            return new ExitStatus(FactorTableGeneratorConstant.JEN_TYPE_1);
        }
    }
}