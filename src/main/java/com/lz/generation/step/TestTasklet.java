package com.lz.generation.step;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * Created by Administrator on 2016/3/26.
 */
public class TestTasklet implements Tasklet  {

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        Thread.sleep(1000);
        String keyName   = (String)chunkContext.getStepContext().getStepExecutionContext().get("code");
        System.out.println("keyName:"+keyName);
        return RepeatStatus.FINISHED;
    }

}
