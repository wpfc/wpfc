package cn.edu.ntu.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Proxy {
	
	static final Logger logger = LoggerFactory.getLogger(Proxy.class);
	
	public Object doProxy(ProxyChain proxyChain);
	
}
