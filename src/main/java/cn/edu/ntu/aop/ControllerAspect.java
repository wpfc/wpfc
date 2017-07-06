package cn.edu.ntu.aop;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.edu.ntu.annotation.Aspect;
import cn.edu.ntu.annotation.Controller;


@Aspect(Controller.class)
public class ControllerAspect extends AbstractProxy {

	private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);
	
	private long time;
	
	@Override
	public void before(Class<?> targetClazz, Method targetMethod, Object[] params) {
		logger.debug("-----------------start--------------------");
		logger.debug(String.format("class : %s", targetClazz.getName()));
		logger.debug(String.format("method : %s", targetMethod.getName()));
		time = System.currentTimeMillis();
	}
	
	
	@Override
	public void after(Class<?> targetClazz, Method targetMethod, Object[] params) {
		logger.debug(String.format("time : %dms", System.currentTimeMillis() - time));
		logger.debug("------------------end---------------------");
	}
	
}
