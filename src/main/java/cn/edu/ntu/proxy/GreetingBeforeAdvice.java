package cn.edu.ntu.proxy;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;

public class GreetingBeforeAdvice implements MethodBeforeAdvice {

	private static final Logger logger = LoggerFactory.getLogger(GreetingBeforeAdvice.class);
	
	@Override
	public void before(Method method, Object[] arg1, Object arg2) throws Throwable {

		logger.info("MethodBeforeAdvice");
	
	}

}
