package cn.edu.ntu.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BeanHelper {

	/**
	 * 定义BEAN的映射（储存bean的类与实例的映射关系）
	 */
	private static Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object>();
	
	
	static {
		Set<Class<?>> beanClassSet = ClassHelperUtil.getBeanClazzSet();
		for(Class<?> clazz : beanClassSet){
			Object obj = ReflectionUtil.newInstance(clazz);
			BEAN_MAP.put(clazz, obj);
		}
	}
	
	/**
	 * 获取bean映射
	 * @return
	 */
	public static Map<Class<?>, Object> getBeanMap(){
		return BEAN_MAP;
	}
	
	/**
	 * 根据类名获取实例
	 * @param clazz
	 * @return  实例对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz){
		if(!BEAN_MAP.containsKey(clazz)){
			throw new RuntimeException("the bean is not found");
		}
		return (T) BEAN_MAP.get(clazz);
	}
	
}
