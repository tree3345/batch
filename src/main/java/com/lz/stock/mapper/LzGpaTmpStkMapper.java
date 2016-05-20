package com.lz.stock.mapper;

import java.util.List;

import com.lz.stock.model.LzGpaTmpStk;

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
public interface LzGpaTmpStkMapper {

    /**
     * 插入
     * 
     * @param lzGpaTmpStk
     * @return returnCode
     */
    int insert(LzGpaTmpStk lzGpaTmpStk);

    /**
     * 删除通过主键
     * 
     * @param innerCode
     * @return returnCode
     */
    int deleteByPrimaryKey(Integer innerCode);

    /**
     * 单件更新通过主键(全更新)
     * 
     * @param lzGpaTmpStk
     * @return returnCode
     */
    int updateAllByPrimaryKey(LzGpaTmpStk lzGpaTmpStk);

    /**
     * 单件更新通过主键(部分更新)
     * 
     * @param lzGpaTmpStk
     * @return returnCode
     */
    int updateByPrimaryKey(LzGpaTmpStk lzGpaTmpStk);

    /**
     * 单件检索通过主键
     * 
     * @param innerCode
     * @return lzGpaTmpStk
     */
    LzGpaTmpStk selectOneByPrimaryKey(Integer innerCode);

    /**
     * 检索列表
     * 
     * @param lzGpaTmpStk
     * @return lzGpaTmpStkList
     */
    List<LzGpaTmpStk> selectList(LzGpaTmpStk lzGpaTmpStk);

    /**
     * 检索件数
     * 
     * @param lzGpaTmpStk
     * @return count
     */
    int selectCount(LzGpaTmpStk lzGpaTmpStk);
}
