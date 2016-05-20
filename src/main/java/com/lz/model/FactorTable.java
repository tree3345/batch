package com.lz.model;

import java.util.Date;

public class FactorTable {

    /**
     * 系统因子主键
     */
    private Integer factorId;

    /**
     * 是否可生成因子文件：1可生成
     */
    private Byte enable;

    /**
     * 因子表数据最小日期
     */
    private Date dataBeginDate;

    /**
     * 因子表数据最大日期
     */
    private Date dataEndDate;

    /**
     * 因子文件更新时间
     */
    private Date lastGenerateDate;
    private Date dataUpdateDate;

    /**
     */
    private Date regenerateDate;

    /**
     * 因子文件名
     */
    private String fileName;

    /**
     * 因子名称
     */
    private String factorName;

    /**
     * 因子代码
     */
    private String factorCode;

    /**
     * 因子分类ID
     */
    private Integer factorCategoryId;

    /**
     * 因子简介
     */
    private String description;

    /**
     * 因子备注
     */
    private String remark;

    /**
     * 因子订阅：0否，1是
     */
    private Byte isSubscribe;

    /**
     * 因子表最大序列
     */
    private Long maxSeq;

    /**
     * 因子组，作用：1.根据该字段决定因子生成先后顺序；2.多台服务器共同运行取值条件根据。赋值说明：0.特殊因子，其它，值小先生成。
     */
    private Byte factorGroup;

    /**
     * 因子文件执行标记，默认值为0，如果值小于DATA_EXECUTE_FLAG则执行生成因子文件。
     */
    private Integer factorExecuteFlag;

    /**
     * Datapipeline驱动标记，默认值为0，如果值大于FACTOR_EXECUTE_FLAG，则执行生成因子文件。
     */
    private Integer dataExecuteFlag;


    /**
     * <pre>
     * 系统因子主键
     * </pre>
     * 
     * @return the factorId
     */
    public Integer getFactorId() {

        return factorId;
    }

    /**
     * <pre>
     * 系统因子主键
     * </pre>
     * 
     * @param factorId the factorId to set
     */
    public void setFactorId(Integer factorId) {

        this.factorId = factorId;
    }

    /**
     * <pre>
     * 是否可生成因子文件：1可生成
     * </pre>
     * 
     * @return the enable
     */
    public Byte getEnable() {

        return enable;
    }

    /**
     * <pre>
     * 是否可生成因子文件：1可生成
     * </pre>
     * 
     * @param enable the enable to set
     */
    public void setEnable(Byte enable) {

        this.enable = enable;
    }

    /**
     * <pre>
     * 因子表数据最小日期
     * </pre>
     * 
     * @return the dataBeginDate
     */
    public Date getDataBeginDate() {

        return dataBeginDate;
    }

    /**
     * <pre>
     * 因子表数据最小日期
     * </pre>
     * 
     * @param dataBeginDate the dataBeginDate to set
     */
    public void setDataBeginDate(Date dataBeginDate) {

        this.dataBeginDate = dataBeginDate;
    }

    /**
     * <pre>
     * 因子表数据最大日期
     * </pre>
     * 
     * @return the dataEndDate
     */
    public Date getDataEndDate() {

        return dataEndDate;
    }

    /**
     * <pre>
     * 因子表数据最大日期
     * </pre>
     * 
     * @param dataEndDate the dataEndDate to set
     */
    public void setDataEndDate(Date dataEndDate) {

        this.dataEndDate = dataEndDate;
    }

    /**
     * <pre>
     * 因子文件更新时间
     * </pre>
     * 
     * @return the lastGenerateDate
     */
    public Date getLastGenerateDate() {

        return lastGenerateDate;
    }

    /**
     * <pre>
     * 因子文件更新时间
     * </pre>
     * 
     * @param lastGenerateDate the lastGenerateDate to set
     */
    public void setLastGenerateDate(Date lastGenerateDate) {

        this.lastGenerateDate = lastGenerateDate;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @return the regenerateDate
     */
    public Date getRegenerateDate() {

        return regenerateDate;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param regenerateDate the regenerateDate to set
     */
    public void setRegenerateDate(Date regenerateDate) {

        this.regenerateDate = regenerateDate;
    }

    /**
     * <pre>
     * 因子文件名
     * </pre>
     * 
     * @return the fileName
     */
    public String getFileName() {

        return fileName;
    }

    /**
     * <pre>
     * 因子文件名
     * </pre>
     * 
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {

        this.fileName = fileName;
    }

    /**
     * <pre>
     * 因子名称
     * </pre>
     * 
     * @return the factorName
     */
    public String getFactorName() {

        return factorName;
    }

    /**
     * <pre>
     * 因子名称
     * </pre>
     * 
     * @param factorName the factorName to set
     */
    public void setFactorName(String factorName) {

        this.factorName = factorName;
    }

    /**
     * <pre>
     * 因子代码
     * </pre>
     * 
     * @return the factorCode
     */
    public String getFactorCode() {

        return factorCode;
    }

    /**
     * <pre>
     * 因子代码
     * </pre>
     * 
     * @param factorCode the factorCode to set
     */
    public void setFactorCode(String factorCode) {

        this.factorCode = factorCode;
    }

    /**
     * <pre>
     * 因子分类ID
     * </pre>
     * 
     * @return the factorCategoryId
     */
    public Integer getFactorCategoryId() {

        return factorCategoryId;
    }

    /**
     * <pre>
     * 因子分类ID
     * </pre>
     * 
     * @param factorCategoryId the factorCategoryId to set
     */
    public void setFactorCategoryId(Integer factorCategoryId) {

        this.factorCategoryId = factorCategoryId;
    }

    /**
     * <pre>
     * 因子简介
     * </pre>
     * 
     * @return the description
     */
    public String getDescription() {

        return description;
    }

    /**
     * <pre>
     * 因子简介
     * </pre>
     * 
     * @param description the description to set
     */
    public void setDescription(String description) {

        this.description = description;
    }

    /**
     * <pre>
     * 因子备注
     * </pre>
     * 
     * @return the remark
     */
    public String getRemark() {

        return remark;
    }

    /**
     * <pre>
     * 因子备注
     * </pre>
     * 
     * @param remark the remark to set
     */
    public void setRemark(String remark) {

        this.remark = remark;
    }

    /**
     * <pre>
     * 因子订阅：0否，1是
     * </pre>
     * 
     * @return the isSubscribe
     */
    public Byte getIsSubscribe() {

        return isSubscribe;
    }

    /**
     * <pre>
     * 因子订阅：0否，1是
     * </pre>
     * 
     * @param isSubscribe the isSubscribe to set
     */
    public void setIsSubscribe(Byte isSubscribe) {

        this.isSubscribe = isSubscribe;
    }

    /**
     * <pre>
     * 因子表最大序列
     * </pre>
     * 
     * @return the maxSeq
     */
    public Long getMaxSeq() {

        return maxSeq;
    }

    /**
     * <pre>
     * 因子表最大序列
     * </pre>
     * 
     * @param maxSeq the maxSeq to set
     */
    public void setMaxSeq(Long maxSeq) {

        this.maxSeq = maxSeq;
    }

    /**
     * <pre>
     * 因子组，作用：1.根据该字段决定因子生成先后顺序；2.多台服务器共同运行取值条件根据。赋值说明：0.特殊因子，其它，值小先生成。
     * </pre>
     * 
     * @return the factorGroup
     */
    public Byte getFactorGroup() {

        return factorGroup;
    }

    /**
     * <pre>
     * 因子组，作用：1.根据该字段决定因子生成先后顺序；2.多台服务器共同运行取值条件根据。赋值说明：0.特殊因子，其它，值小先生成。
     * </pre>
     * 
     * @param factorGroup the factorGroup to set
     */
    public void setFactorGroup(Byte factorGroup) {

        this.factorGroup = factorGroup;
    }

    /**
     * <pre>
     * 因子文件执行标记，默认值为0，如果值小于DATA_EXECUTE_FLAG则执行生成因子文件。
     * </pre>
     * 
     * @return the factorExecuteFlag
     */
    public Integer getFactorExecuteFlag() {

        return factorExecuteFlag;
    }

    /**
     * <pre>
     * 因子文件执行标记，默认值为0，如果值小于DATA_EXECUTE_FLAG则执行生成因子文件。
     * </pre>
     * 
     * @param factorExecuteFlag the factorExecuteFlag to set
     */
    public void setFactorExecuteFlag(Integer factorExecuteFlag) {

        this.factorExecuteFlag = factorExecuteFlag;
    }

    /**
     * <pre>
     * Datapipeline驱动标记，默认值为0，如果值大于FACTOR_EXECUTE_FLAG，则执行生成因子文件。
     * </pre>
     * 
     * @return the dataExecuteFlag
     */
    public Integer getDataExecuteFlag() {

        return dataExecuteFlag;
    }

    /**
     * <pre>
     * Datapipeline驱动标记，默认值为0，如果值大于FACTOR_EXECUTE_FLAG，则执行生成因子文件。
     * </pre>
     * 
     * @param dataExecuteFlag the dataExecuteFlag to set
     */
    public void setDataExecuteFlag(Integer dataExecuteFlag) {

        this.dataExecuteFlag = dataExecuteFlag;
    }

    public Date getDataUpdateDate() {
        return dataUpdateDate;
    }

    public void setDataUpdateDate(Date dataUpdateDate) {
        this.dataUpdateDate = dataUpdateDate;
    }
}
