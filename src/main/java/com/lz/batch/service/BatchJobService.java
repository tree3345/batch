package com.lz.batch.service;

import java.util.HashMap;

import com.lz.batch.response.BatchResponse;

public interface BatchJobService {
	BatchResponse doJob(HashMap<String, String> paramMap);
}
