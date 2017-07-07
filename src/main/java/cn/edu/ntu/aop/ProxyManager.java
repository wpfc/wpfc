package cn.edu.ntu.aop;

import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import sun.security.jca.GetInstance;

public class ProxyManager {

	private static ProxyManager instance = null;
	
	public static ProxyManager getInstance(){
		if(instance == null){
			instance = new ProxyManager();
		}
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getProxy(Class<?> targetClazz, List<Proxy> proxyList){
		
		return (T) Enhancer.create(targetClazz, new MethodInterceptor() {
			//intercept的参数解释：Object为由CGLib动态生成的代理类实例，
			//Method为上文中实体类所调用的被代理的方法引用，Object[]为参数值列表，
			//MethodProxy为生成的代理类对方法的代理引用。
			@Override
			public Object intercept(Object targetObj, Method targetMethod, 
					                Object[] methodParams, MethodProxy methodProxy) throws Throwable {
				return new ProxyChain(targetClazz, targetObj, targetMethod, methodProxy, 
						              methodParams, proxyList).doProxyChain();
			}
		});
	}
	
}
