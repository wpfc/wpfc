package cn.edu.ntu.aop;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import cn.edu.ntu.annotation.Aspect;
import cn.edu.ntu.utils.ClassHelperUtil;

public class AopHelper {
	
	static{
		try {
			Map<Class<?>, Set<Class<?>>> proxyMap = createProxyMap();
			Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);
			
			if(!targetMap.isEmpty()){
				for(Map.Entry<Class<?>, List<Proxy>> entry : targetMap.entrySet()){
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

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
	public static Map<Class<?>, Set<Class<?>>> createProxyMap(){
		Map<Class<?>, Set<Class<?>>> PROXY_MAP = new HashMap<Class<?>, Set<Class<?>>>();
		//获取所有的代理类
		Set<Class<?>> proxyClassSet = ClassHelperUtil.getClassSetBySuper(AbstractProxy.class);
		if(proxyClassSet != null && proxyClassSet.size() >0){
			for(Class<?> clazz : proxyClassSet){
				if(clazz.isAnnotationPresent(Aspect.class)){
					Aspect aspect = (Aspect) clazz.getAnnotation(Aspect.class);
					Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
					PROXY_MAP.put(clazz, targetClassSet);
				}
			}
		}
		return PROXY_MAP;
	}
	
	
	/**
	 * 获取目标类与代理对象之间的关系     一对多
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>, Set<Class<?>>> proxyMap) throws InstantiationException, IllegalAccessException{
		Map<Class<?>, List<Proxy>> TARGET_MAP = new HashMap<Class<?>, List<Proxy>>();
		if(!proxyMap.isEmpty()){
			for(Map.Entry<Class<?>, Set<Class<?>>> entry : proxyMap.entrySet()){
				Class<?> proxyClazz = entry.getKey();
				if(!entry.getValue().isEmpty()){
					Proxy proxy = (Proxy) proxyClazz.newInstance();
					for(Class<?> targetClazz : entry.getValue()){
						if(TARGET_MAP.containsKey(targetClazz)){
							TARGET_MAP.get(targetClazz).add(proxy);
						}else{
							List<Proxy> proxyList = new ArrayList<Proxy>();
							proxyList.add(proxy);
							TARGET_MAP.put(targetClazz, proxyList);
						}
					}
				}
			}
		}
		return TARGET_MAP;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
