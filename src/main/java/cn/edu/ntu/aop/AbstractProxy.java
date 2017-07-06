package cn.edu.ntu.aop;

import java.lang.reflect.Method;

public class AbstractProxy implements Proxy {

	@Override
	public Object doProxy(ProxyChain proxyChain) {
		
		Object result = null;
		
		Class<?> targetClazz = proxyChain.getTargetClazz();
		Method targetMethod = proxyChain.getTargetMethod();
		Object[] params = proxyChain.getMethodParams();
		
		start();
		try {
			if(filter(targetClazz, targetMethod, params)){
				before(targetClazz, targetMethod, params);
				result = proxyChain.doProxyChain();
				after(targetClazz, targetMethod, params);
			}else{
				result = proxyChain.doProxyChain();
			}
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			end();
		}
		return null;
	}

	public void end() {
		// TODO Auto-generated method stub
		
	}

	public void start() {
		// TODO Auto-generated method stub
		
	}

	public void after(Class<?> targetClazz, Method targetMethod, Object[] params) {
		// TODO Auto-generated method stub
		
	}

	public void before(Class<?> targetClazz, Method targetMethod, Object[] params) {
		// TODO Auto-generated method stub
		
	}

	private boolean filter(Class<?> targetClazz, Method targetMethod, Object[] params) {
		return true;
	}

}
