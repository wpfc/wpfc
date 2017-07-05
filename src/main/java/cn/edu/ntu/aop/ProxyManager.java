package cn.edu.ntu.aop;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class ProxyManager {

	@SuppressWarnings("unchecked")
	public <T> T getProxy(final Class<T> targetClazz, final List<Proxy> proxyList){
		return (T) Enhancer.create(targetClazz, new MethodInterceptor() {
			
			@Override
			public Object intercept(Object targetObj, Method targetMethod, 
					                Object[] methodParams, MethodProxy methodProxy) throws Throwable {
				return new ProxyChain(targetClazz, targetObj, targetMethod, methodProxy, 
						              methodParams, proxyList).doProxyChain();
			}
		});
	}
	
}
