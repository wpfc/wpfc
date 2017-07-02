package cn.edu.ntu.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  反射工具类
 */
public final class ReflectionUtil {

	//private static final Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);
	
	/**
	 *  创建实例
	 */
	public static Object newInstance(Class<?> clazz){
		Object obj = null;
		try {
			obj = clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * 调用方法
	 * @param obj
	 * @param method
	 * @param args
	 * @return
	 */
	public static Object invokeMethod(Object obj, Method method, Object... args){
		Object result = null;
		try {
			method.setAccessible(true);
			result = method.invoke(obj, args);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 调用无参方法
	 * @param obj
	 * @param method
	 * @param args
	 * @return
	 */
	public static Object invokeMethod(Object obj, Method method){
		Object result = null;
		try {
			method.setAccessible(true);
			result = method.invoke(obj);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 设置成员变量的值
	 * @param obj
	 * @param field
	 * @param value
	 */
	public static void setField(Object obj, Field field, Object value){
		try {
			field.setAccessible(true);
			field.set(obj, value);
		} catch (Exception e) {
			//logger.error("set field failure!");
			e.printStackTrace();
		}
	}
}
