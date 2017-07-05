package cn.edu.ntu.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AspectProxy implements Proxy {

	private Logger logger = LoggerFactory.getLogger(AspectProxy.class);
	
	@Override
	public Object doProxy(ProxyChain proxyChain) {
		return null;
	}

	
}
