package com.lz.stock.step;

import com.lz.stock.model.LzGpaTmpStk;
import com.lz.stock.model.StockCodeBase;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by Administrator on 2016/5/20.
 */

public class LzGpaTmpStkProcessor implements ItemProcessor<LzGpaTmpStk,StockCodeBase> {
    public int inc = 0;
    private int pageSize;

    @Override
    public StockCodeBase process(LzGpaTmpStk lzGpaTmpStk) throws Exception {
        StockCodeBase stockCodeBase = new StockCodeBase();
        String[] code_ =lzGpaTmpStk.getLzjfCode().split("\\.");

        if(code_.length==0||code_==null)return null;
        stockCodeBase.setStockCode(code_[1].toLowerCase()+code_[0]);
        int groupNo = inc++ / pageSize;
        stockCodeBase.setGroupFlag(groupNo);
        return stockCodeBase;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }
}
