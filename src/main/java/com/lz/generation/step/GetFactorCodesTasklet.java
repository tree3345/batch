package com.lz.generation.step;

import com.lz.mapper.FactorTableMapper;
import com.lz.model.FactorTable;
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
 * Created by Administrator on 2016/3/29.
 */
public class GetFactorCodesTasklet implements Tasklet {

    @Autowired
    private FactorTableMapper factorTableMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        FactorTable factorParams = new FactorTable();
        List<FactorTable> factorTables = factorTableMapper.selectList(factorParams);
        String[] codes = new String[factorTables.size()];
        for(int i =0;i<factorTables.size();i++){
            codes[i]=factorTables.get(i).getFactorCode();
        }
//        chunkContext.getStepContext().getStepExecution().getExecutionContext().put("factorTabels", factorTables);
        chunkContext.getStepContext().getStepExecution().getExecutionContext().put("factorCodes", codes);
        return RepeatStatus.FINISHED;
    }
}