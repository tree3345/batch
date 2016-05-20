package com.lz.batch.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class BeanToMapUtil {

    /**
     * 将一个 Map 对象转化为一个 JavaBean  只适合Map<String,String>
     * @param type 要转化的类型
     * @param map 包含属性值的 map
     * @return 转化出来的 JavaBean 对象
     * @throws IntrospectionException
     *             如果分析类属性失败
     * @throws IllegalAccessException
     *             如果实例化 JavaBean 失败
     * @throws InstantiationException
     *             如果实例化 JavaBean 失败
     * @throws InvocationTargetException
     *             如果调用属性的 setter 方法失败
     */
    public static Object convertMap(Class type, Map<String,String> map)
            throws IntrospectionException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
        BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
        Object obj = type.newInstance(); // 创建 JavaBean 对象
        boolean flag=false;

        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i< propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            String propertyType = descriptor.getPropertyType().getName();

            if (map.containsKey(propertyName)) {
                flag=true;
                Object value = map.get(propertyName);
                Object[] args = new Object[1];

                if(propertyType.equals(Integer.class.getName())){
                    args[0] = Integer.parseInt(value.toString());
                }else if(propertyType.equals(Long.class.getName())){
                    args[0]=Long.parseLong(value.toString());
                }else if(propertyType.equals(Double.class.getName())){
                    args[0]=Double.parseDouble(value.toString());
                }else if(propertyType.equals(BigDecimal.class.getName())){
                    args[0]=new BigDecimal(value.toString());
                }
                else if(propertyType.equals(java.util.Date.class.getName())){
                    try {
                        String formatStr="yyyy-MM-dd HH:mm:ss";
                        args[0] = new SimpleDateFormat(formatStr.substring(0,value.toString().length())).parse(value.toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else
                    args[0] = value;

                try {
                    descriptor.getWriteMethod().invoke(obj, args);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (flag)
         return obj;
        else
            return null;
    }
    public static Object convertMapObj(Class type, Map<String,Object> map)
            throws IntrospectionException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
        BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
        Object obj = type.newInstance(); // 创建 JavaBean 对象
        boolean flag=false;

        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i< propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            propertyName=propertyName.toLowerCase();//转换成小写，实现大小写不区分
            String propertyType = descriptor.getPropertyType().getName();

            if (map.containsKey(propertyName)) {
                flag=true;
                Object value = map.get(propertyName);
                Object[] args = new Object[1];
                args[0] = value;
                try {
                    descriptor.getWriteMethod().invoke(obj, args);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (flag)
            return obj;
        else
            return null;
    }
    /**
     * 将一个 JavaBean 对象转化为一个  Map
     * @param bean 要转化的JavaBean 对象
     * @return 转化出来的  Map 对象
     * @throws IntrospectionException 如果分析类属性失败
     * @throws IllegalAccessException 如果实例化 JavaBean 失败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */
    public static Map convertBean(Object bean)
            throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        Class type = bean.getClass();
        Map returnMap = new HashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i< propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }

}