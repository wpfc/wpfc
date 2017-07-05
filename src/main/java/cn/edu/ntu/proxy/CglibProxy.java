package cn.edu.ntu.proxy;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;


/**
 * cglib对象创建比较慢，代理很快，因此使用单例模式
 * @author wpfc
 *
 */
public class CglibProxy implements MethodInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(CglibProxy.class);
	
	private static CglibProxy instance = null;
	
	public static CglibProxy getInstance(){
		if(instance == null){
			instance = new CglibProxy();
		}
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getProxy(Class<T> clazz){
		return (T) Enhancer.create(clazz, this);
	}
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		logger.info("before cglib proxy");
		Object result = proxy.invokeSuper(obj, args);
		logger.info("after cglib proxy");
		return result;
	}

}
