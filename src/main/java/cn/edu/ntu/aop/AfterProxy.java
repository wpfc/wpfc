package cn.edu.ntu.aop;

import java.lang.reflect.Method;

public class AfterProxy extends AbstractProxy {

	@Override
	public void before(Class<?> targetClazz, Method targetMethod, Object[] params) {
		logger.debug("-----------------start--------------------");
		logger.debug(String.format("class : %s", targetClazz.getName()));
		logger.debug(String.format("method : %s", targetMethod.getName()));
	}
	
	
	@Override
	public void after(Class<?> targetClazz, Method targetMethod, Object[] params) {
		logger.debug(String.format("time : %dms", System.currentTimeMillis()));
		logger.debug("------------------end---------------------");
	}
}
