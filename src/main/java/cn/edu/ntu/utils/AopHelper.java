package cn.edu.ntu.utils;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.edu.ntu.annotation.Aspect;
import cn.edu.ntu.annotation.Service;
import cn.edu.ntu.aop.AbstractProxy;
import cn.edu.ntu.aop.Proxy;
import cn.edu.ntu.aop.ProxyManager;
import cn.edu.ntu.transactional.TransactionProxy;

public class AopHelper {
	
	private static final Logger logger = LoggerFactory.getLogger(AopHelper.class);
	
	static{
		try {
			Map<Class<?>, Set<Class<?>>> proxyMap = createProxyMap();
			//addTransactionProxy(proxyMap);
			Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);
			
			if(!targetMap.isEmpty()){
				for(Map.Entry<Class<?>, List<Proxy>> entry : targetMap.entrySet()){
					Class<?> targetClazz = entry.getKey();
					List<Proxy> proxyList = entry.getValue();
					Object proxy = ProxyManager.getInstance().getProxy(targetClazz, proxyList);
					BeanHelper.setBean(targetClazz, proxy);
				}
			}
		} catch (Exception e) {
			logger.error("aop error", e);
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
		addAspectProxy(PROXY_MAP);
		addTransactionProxy(PROXY_MAP);
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
	
	//获取Aspect代理类与目标类之间的映射关系
	private static void addAspectProxy(Map<Class<?>, Set<Class<?>>> proxyMap){
		Set<Class<?>> proxyClassSet = ClassHelperUtil.getClassSetBySuper(Proxy.class);
		if(!proxyClassSet.isEmpty()){
			for(Class<?> clazz : proxyClassSet){
				if(clazz.isAnnotationPresent(Aspect.class)){
					Aspect aspect = clazz.getAnnotation(Aspect.class);
					Set<Class<?>> targetClazzSet = createTargetClassSet(aspect);
					proxyMap.put(clazz, targetClazzSet);
				}
			}
		}
	}

	//获取Transaction(事务)代理类与目标类之间的映射关系
	private static void addTransactionProxy(Map<Class<?>, Set<Class<?>>> proxyMap){
		Set<Class<?>> transactionClassSet = ClassHelperUtil.getClassSetByAnnotation(Service.class);
		proxyMap.put(TransactionProxy.class, transactionClassSet);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
