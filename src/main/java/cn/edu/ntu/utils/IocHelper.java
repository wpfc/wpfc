package cn.edu.ntu.utils;

import java.lang.reflect.Field;
import java.util.Map;

import cn.edu.ntu.annotation.Inject;

public class IocHelper {

	static{
		//获取所有的bean的映射关系
		Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
		if(!(beanMap == null || beanMap.size() == 0)){
			for(Map.Entry<Class<?>, Object> entry : beanMap.entrySet()){
				Class<?> clazz = entry.getKey();
				Object instance = entry.getValue();
				Field[] fields = clazz.getDeclaredFields();
				if(fields!=null && fields.length >0){
					for(Field field : fields){
						if(field.isAnnotationPresent(Inject.class)){
							Class<?> fieldClass = field.getType();
							Object injectObj = beanMap.get(fieldClass);
							if(injectObj != null){
								ReflectionUtil.setField(instance, field, injectObj);
							}
						}
					}
				}
			}
		}
		
	}
	
}
