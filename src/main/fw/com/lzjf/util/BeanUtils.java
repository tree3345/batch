package com.lzjf.util;
import java.util.List;

public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {
	public static List<String> getBeanFieldNames(Object bean) {
		Class<?> clazz = bean.getClass();
		return ReflectionUtils.findBeanNames(clazz);
	}

	public static Object getValue(Object bean, String fieldName) {
		String[] tokens = fieldName.split("\\.");
		return getValue(bean, tokens);
	}

	public static boolean contains(Object bean, String fieldName) {
		String[] fieldNames = fieldName.split("\\.");
		return contains(bean, fieldNames);
	}

	public static Object getValue(Object bean, String[] fieldNames) {
		return getValue(bean, fieldNames, 0);
	}

	public static Object getValue(Object bean, String[] fieldNames, int offset) {
		Object fieldValue = bean;
		for (int i = offset; i < fieldNames.length; i++) {
			fieldValue = ReflectionUtils.invokeGetMethod(fieldValue, fieldNames[i]);
			if (fieldValue == null) {
				break;
			}
		}
		return fieldValue;
	}

	public static boolean contains(Object object, String[] tokens) {
		boolean is = true;
		Object fieldValue = object;
		for (int i = 0; i < tokens.length; i++) {
			if (!ReflectionUtils.hasGetMethod(object, tokens[i])) {
				is = false;
				break;
			}

			fieldValue = ReflectionUtils.invokeGetMethod(fieldValue, tokens[i]);
			if (fieldValue == null) {
				break;
			}
		}
		return is;
	}
}