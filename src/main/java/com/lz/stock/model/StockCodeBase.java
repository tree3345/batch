package com.lz.stock.model;

/**
 * <pre>
 * =============================================================================
 * 日期：yyyyMMdd  担当:
 * 内容:
 * =============================================================================
 * </pre>
 * 
 * @author 君融贷-
 */
public class StockCodeBase {

    /**
     * 股票代码
     */
    private String stockCode;

    /**
     * 动态分组标识
     */
    private Integer groupFlag;

    /**
     * <pre>
     * 股票代码
     * </pre>
     * 
     * @return the stockCode
     */
    public String getStockCode() {

        return stockCode;
    }

    /**
     * <pre>
     * 股票代码
     * </pre>
     * 
     * @param stockCode the stockCode to set
     */
    public void setStockCode(String stockCode) {

        this.stockCode = stockCode;
    }

    /**
     * <pre>
     * 动态分组标识
     * </pre>
     * 
     * @return the groupFlag
     */
    public Integer getGroupFlag() {

        return groupFlag;
    }

    /**
     * <pre>
     * 动态分组标识
     * </pre>
     * 
     * @param groupFlag the groupFlag to set
     */
    public void setGroupFlag(Integer groupFlag) {

        this.groupFlag = groupFlag;
    }
}
