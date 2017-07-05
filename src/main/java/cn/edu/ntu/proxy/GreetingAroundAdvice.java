package cn.edu.ntu.proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GreetingAroundAdvice implements MethodInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(GreetingAroundAdvice.class);
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		logger.info("before arround");
		Object result = invocation.proceed();
		logger.info("after arround");
		return result;
	}

}
