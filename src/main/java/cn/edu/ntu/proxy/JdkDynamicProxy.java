package cn.edu.ntu.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JDK动态代理需要实现接口
 * @author wpfc
 */
public class JdkDynamicProxy implements InvocationHandler {

	private static final Logger logger = LoggerFactory.getLogger(HelloImpl.class);
	
	private Object target;
	
	public JdkDynamicProxy(Object target){
		this.target = target;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		logger.info("before carry out the method");
		Object result = method.invoke(target, args);
		logger.info("after carry out the method");
		return result;
	}

	@SuppressWarnings("unchecked")
	public <T> T getProxy(){
		return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), 
									      target.getClass().getInterfaces(), 
										  this);
	}
}
