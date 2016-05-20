package com.lz.mapper;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lz.model.FactorTable;
import com.lz.model.JenTableModel;

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

public interface FactorTableMapper {

    /**
     * 插入
     * 
     * @param factorTable
     * @return returnCode
     */
    int insert(FactorTable factorTable);

    /**
     * 删除通过主键
     * 
     * @param factorId
     * @return returnCode
     */
    int deleteByPrimaryKey(Integer factorId);

    /**
     * 单件更新通过主键(全更新)
     * 
     * @param factorTable
     * @return returnCode
     */
    int updateAllByPrimaryKey(FactorTable factorTable);

    /**
     * 单件更新通过主键(部分更新)
     * 
     * @param factorTable
     * @return returnCode
     */
    int updateByPrimaryKey(FactorTable factorTable);

    /**
     * 单件检索通过主键
     * 
     * @param factorId
     * @return factorTable
     */
    FactorTable selectOneByPrimaryKey(Integer factorId);

    /**
     * 检索列表
     * 
     * @param factorTable
     * @return factorTableList
     */
    List<FactorTable> selectList(FactorTable factorTable);

    List<Map<String,String>> selectJenDataList(Map<String,String> map);

    List<Map<String,Object>> selectHeader();

    /**
     * 检索件数
     * 
     * @param factorTable
     * @return count
     */
    int selectCount(FactorTable factorTable);

    void insertCalendar(Date endDate);
}
