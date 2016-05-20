package com.lz.qz.mapper;

import com.lz.qz.model.QrtzCronTriggers;

import java.util.HashMap;
import java.util.List;

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
public interface QrtzCronTriggersMapper {

    /**
     * 插入
     * 
     * @param qrtzCronTriggers
     * @return returnCode
     */
    int insert(QrtzCronTriggers qrtzCronTriggers);

    /**
     * 删除通过主键 <br>
     * PrimaryKey: schedName, triggerName, triggerGroup
     * 
     * @param qrtzCronTriggers
     * @return returnCode
     */
    int deleteByPrimaryKey(QrtzCronTriggers qrtzCronTriggers);

    /**
     * 单件更新通过主键(全更新) <br>
     * PrimaryKey: schedName, triggerName, triggerGroup
     * 
     * @param qrtzCronTriggers
     * @return returnCode
     */
    int updateAllByPrimaryKey(QrtzCronTriggers qrtzCronTriggers);

    /**
     * 单件更新通过主键(部分更新) <br>
     * PrimaryKey: schedName, triggerName, triggerGroup
     * 
     * @param qrtzCronTriggers
     * @return returnCode
     */
    int updateByPrimaryKey(QrtzCronTriggers qrtzCronTriggers);

    /**
     * 单件检索通过主键 <br>
     * PrimaryKey: schedName, triggerName, triggerGroup
     * 
     * @param qrtzCronTriggers
     * @return qrtzCronTriggers
     */
    QrtzCronTriggers selectOneByPrimaryKey(QrtzCronTriggers qrtzCronTriggers);

    /**
     * 检索列表
     * 
     * @param paramMap
     * @return qrtzCronTriggersList
     */
    List<QrtzCronTriggers> selectList(HashMap<String, String> paramMap);

    /**
     * 检索件数
     * 
     * @param qrtzCronTriggers
     * @return count
     */
    int selectCount(QrtzCronTriggers qrtzCronTriggers);
}
