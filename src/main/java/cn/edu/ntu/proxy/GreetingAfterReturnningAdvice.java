package cn.edu.ntu.proxy;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;

public class GreetingAfterReturnningAdvice implements AfterReturningAdvice {

	private static final Logger logger = LoggerFactory.getLogger(GreetingAfterReturnningAdvice.class);
	
	@Override
	public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {
		
		logger.info("AfterReturningAdvice");

	}

}
