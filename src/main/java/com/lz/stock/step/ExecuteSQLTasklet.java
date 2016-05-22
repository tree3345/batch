package com.lz.stock.step;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Administrator on 2016/5/20.
 */
class ExecuteSQLTasklet implements Tasklet {

    private DruidDataSource dataSource;
    private String sql;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        new JdbcTemplate(this.dataSource).execute(this.sql);
        return RepeatStatus.FINISHED;
    }

    public void setDataSource(DruidDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DruidDataSource getDataSource() {
        return dataSource;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }
}