package com.lz.batch.service.impl;

import com.lz.batch.response.BatchResponse;
import com.lz.batch.service.BatchJobService;
import com.lz.qz.mapper.QrtzCronTriggersMapper;
import com.lz.qz.model.QrtzCronTriggers;
import com.lzjf.util.JacksonJsonUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

public class QuartzJobQueryServiceImpl implements BatchJobService {

    private Logger log = Logger.getLogger("biz");

    @Autowired
    private QrtzCronTriggersMapper qrtzCronTriggersMapper;

    @Override
    public BatchResponse doJob(HashMap<String, String> paramMap) {

        BatchResponse rtn = new BatchResponse();
        List<QrtzCronTriggers> list=qrtzCronTriggersMapper.selectList(paramMap);
        rtn.setSuccess(true);
        rtn.setRtn(JacksonJsonUtil.beanToJson(list));
        log.info("qz任务查询成功");
        return rtn;
    }

}
