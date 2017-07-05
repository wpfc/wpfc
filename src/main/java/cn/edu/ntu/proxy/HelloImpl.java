package cn.edu.ntu.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloImpl implements Hello {

	private static final Logger logger = LoggerFactory.getLogger(HelloImpl.class);
	
	@Override
	public void sayHello(String content) {
		logger.info("say " + content + " to you");
	}

}
