package cn.edu.ntu.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

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
							Object injectObj = null;
							if(!fieldClass.isInterface()){
								injectObj = beanMap.get(fieldClass);
							}else{
								Set<Class<?>> childClazzSet = ClassHelperUtil.getClassSetBySuper(fieldClass);
								if(childClazzSet !=null && 1 == childClazzSet.size()){
									Object[] classArray = childClazzSet.toArray();
									injectObj = beanMap.get(classArray[0]);
								}else{
									throw new RuntimeException("inject fail");
								}
							}
							if(injectObj != null){
								ReflectionUtil.setField(instance, field, injectObj);
							}
							System.out.println(instance);
						}
					}
				}
			}
		}
	}
}
