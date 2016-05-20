package com.lz.batch.response;

import java.util.HashMap;

public class BatchResponse {

    private String rtn;

    private boolean success = true;

    private String errorMessage;

    private String param;

    // 多个job的结果合集
    private HashMap<String, BatchResponse> batchResponseHm = new HashMap<>();

    /**
     * @return the rtn
     */
    public String getRtn() {

        return rtn;
    }

    /**
     * @param rtn the rtn to set
     */
    public void setRtn(String rtn) {

        this.rtn = rtn;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {

        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {

        this.errorMessage = errorMessage;
    }

    /**
     * @return the success
     */
    public boolean isSuccess() {

        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {

        this.success = success;
    }

    /**
     * @return the param
     */
    public String getParam() {

        return param;
    }

    /**
     * @param param the param to set
     */
    public void setParam(String param) {

        this.param = param;
    }

    /**
     * @return the batchResponseHm
     */
    public HashMap<String, BatchResponse> getBatchResponseHm() {

        return batchResponseHm;
    }

    
    /**
     * @param batchResponseHm the batchResponseHm to set
     */
    public void setBatchResponseHm(HashMap<String, BatchResponse> batchResponseHm) {
    
        this.batchResponseHm = batchResponseHm;
    }
}
