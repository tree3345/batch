package com.lz.batch.request;

import java.util.HashMap;

public class BatchRequest {

    private HashMap<String, String> requestHm = new HashMap<String, String>();

    /**
     * @return the requestHm
     */
    public HashMap<String, String> getRequestHm() {

        return requestHm;
    }

    /**
     * @param requestHm the requestHm to set
     */
    public void setRequestHm(HashMap<String, String> requestHm) {

        this.requestHm = requestHm;
    }
}
