package cn.edu.ntu.aop;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.sf.cglib.proxy.MethodProxy;

public class ProxyChain {

	private Class<?> targetClazz;
	private Object targetObj;
	private Method targetMethod;
	private MethodProxy methodProxy;
	private Object[] methodParams;
	
	private int proxyIndex = 0;
	private List<Proxy> proxyList = new ArrayList<Proxy>();
	
	public ProxyChain(Class<?> targetClazz, Object targetObj, Method targetMethod, 
			          MethodProxy methodProxy, Object[] methodParams, List<Proxy> proxyList){
		this.targetClazz = targetClazz;
		this.targetObj = targetObj;
		this.targetMethod = targetMethod;
		this.methodProxy = methodProxy;
		this.methodParams = methodParams;
		this.proxyList = proxyList;
	}
	
	public Class<?> getTargetClazz() {
		return targetClazz;
	}

	public Object getTargetObj() {
		return targetObj;
	}

	public Method getTargetMethod() {
		return targetMethod;
	}

	public Object[] getMethodParams() {
		return methodParams;
	}

	public List<Proxy> getProxyList() {
		return proxyList;
	}

	public Object doProxyChain() throws Throwable {
		Object result = null;
		if(proxyList != null && proxyList.size() >0 && proxyIndex < proxyList.size()){
			result = proxyList.get(proxyIndex++).doProxy(this);
		}else{
			result = methodProxy.invokeSuper(targetObj, methodParams);
		}
		return result;
	}
}
