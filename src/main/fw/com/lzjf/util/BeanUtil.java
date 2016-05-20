package com.lzjf.util;

import java.lang.reflect.Method;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class BeanUtil implements ApplicationContextAware {

	private static ApplicationContext context;

	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		context = arg0;
	}

	public static Object getBean(String name) throws BeansException {
		return context.getBean(name);
	}

	public static <T> T getBean(String name, Class<T> requiredType) throws BeansException {
		return context.getBean(name, requiredType);
	}

	public static boolean containsBean(String name) {
		return context.containsBean(name);
	}

	public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
		return context.isSingleton(name);
	}

	public static  Class<?> getType(String name) throws NoSuchBeanDefinitionException {
		return context.getType(name);
	}

	public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
		return context.getAliases(name);
	}
	
	public static Method getMethod(Class<?> executer, String name) throws NoSuchMethodException {
		
		for (Method m : executer.getDeclaredMethods()) {
			if (m.getName().toLowerCase().equals(name.toLowerCase())) {
				return m;
			}
		}
		throw new NoSuchMethodException("找不到指定方法");
	}
}
