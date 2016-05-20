package com.lz.qz.model;

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
public class QrtzCronTriggers {

    /**
     */
    private String schedName;

    /**
     */
    private String triggerName;

    /**
     */
    private String triggerGroup;

    /**
     */
    private String cronExpression;

    /**
     */
    private String timeZoneId;

    /**
     * <pre>
     * </pre>
     * 
     * @return the schedName
     */
    public String getSchedName() {

        return schedName;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param schedName the schedName to set
     */
    public void setSchedName(String schedName) {

        this.schedName = schedName;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @return the triggerName
     */
    public String getTriggerName() {

        return triggerName;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param triggerName the triggerName to set
     */
    public void setTriggerName(String triggerName) {

        this.triggerName = triggerName;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @return the triggerGroup
     */
    public String getTriggerGroup() {

        return triggerGroup;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param triggerGroup the triggerGroup to set
     */
    public void setTriggerGroup(String triggerGroup) {

        this.triggerGroup = triggerGroup;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @return the cronExpression
     */
    public String getCronExpression() {

        return cronExpression;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param cronExpression the cronExpression to set
     */
    public void setCronExpression(String cronExpression) {

        this.cronExpression = cronExpression;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @return the timeZoneId
     */
    public String getTimeZoneId() {

        return timeZoneId;
    }

    /**
     * <pre>
     * </pre>
     * 
     * @param timeZoneId the timeZoneId to set
     */
    public void setTimeZoneId(String timeZoneId) {

        this.timeZoneId = timeZoneId;
    }
}
