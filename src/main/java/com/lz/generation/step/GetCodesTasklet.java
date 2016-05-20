package com.lz.generation.step;

import com.lz.mapper.FactorTableMapper;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/28.
 */
public class GetCodesTasklet implements Tasklet{

    @Autowired
    private FactorTableMapper factorTableMapper;
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        List<Map<String, Object>> mapList = factorTableMapper.selectHeader();
        chunkContext.getStepContext().getStepExecution().getExecutionContext().put("header",mapList);
        return RepeatStatus.FINISHED;
    }
}
