package com.lz.batch;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.lz.batch.utils.SpringBeanUtils;

public class SpringApplicationContextAware implements ApplicationContextAware {
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringBeanUtils.setApplicationContext(applicationContext);
	}
}
