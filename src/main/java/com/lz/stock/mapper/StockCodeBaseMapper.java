package com.lz.stock.mapper;

import java.util.List;

import com.lz.stock.model.StockCodeBase;

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
public interface StockCodeBaseMapper {

    /**
     * 插入
     * 
     * @param stockCodeBase
     * @return returnCode
     */
    int insert(StockCodeBase stockCodeBase);

    /**
     * 删除通过主键
     * 
     * @param stockCode
     * @return returnCode
     */
    int deleteByPrimaryKey(String stockCode);

    /**
     * 单件更新通过主键(全更新)
     * 
     * @param stockCodeBase
     * @return returnCode
     */
    int updateAllByPrimaryKey(StockCodeBase stockCodeBase);

    /**
     * 单件更新通过主键(部分更新)
     * 
     * @param stockCodeBase
     * @return returnCode
     */
    int updateByPrimaryKey(StockCodeBase stockCodeBase);

    /**
     * 单件检索通过主键
     * 
     * @param stockCode
     * @return stockCodeBase
     */
    StockCodeBase selectOneByPrimaryKey(String stockCode);

    /**
     * 检索列表
     * 
     * @param stockCodeBase
     * @return stockCodeBaseList
     */
    List<StockCodeBase> selectList(StockCodeBase stockCodeBase);

    /**
     * 检索件数
     * 
     * @param stockCodeBase
     * @return count
     */
    int selectCount(StockCodeBase stockCodeBase);
}
