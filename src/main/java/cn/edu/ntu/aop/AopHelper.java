package cn.edu.ntu.aop;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cn.edu.ntu.annotation.Aspect;
import cn.edu.ntu.utils.ClassHelperUtil;

public class AopHelper {

	public static Set<Class<?>> createTargetClassSet(Aspect aspect){
		Set<Class<?>> targetClassSet = new HashSet<Class<?>>();
		Class<? extends Annotation> annotationClazz = aspect.value();
		if(annotationClazz != null && !annotationClazz.equals(Aspect.class)){
			targetClassSet.addAll(ClassHelperUtil.getClassSetByAnnotation(annotationClazz));
		}
		return targetClassSet;
	}
	
	
	/**
	 * 获取代理类及其目标类之间的关系  一个代理类对应多个目标类
	 */
	public Map<Class<?>, Set<Class<?>>> createProxyMap(){
		Map<Class<?>, Set<Class<?>>> PROXY_MAP = new HashMap<Class<?>, Set<Class<?>>>();
		//获取所有的代理类
		Set<Class<?>> proxyClassSet = ClassHelperUtil.getClassSetBySuper(AbstractProxy.class);
		if(proxyClassSet != null && proxyClassSet.size() >0){
			for(Class clazz : proxyClassSet){
				if(clazz.isAnnotationPresent(Aspect.class)){
					Aspect aspect = (Aspect) clazz.getAnnotation(Aspect.class);
					Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
					PROXY_MAP.put(clazz, targetClassSet);
				}
			}
		}
		return PROXY_MAP;
	}
	
}
