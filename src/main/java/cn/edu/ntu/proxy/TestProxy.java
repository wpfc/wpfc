package cn.edu.ntu.proxy;

import org.springframework.aop.framework.ProxyFactory;

public class TestProxy {

//	public static void main(String[] args){
//		Hello target = new HelloImpl();
//		JdkDynamicProxy proxy = new JdkDynamicProxy(target);
//		Hello result = (Hello) proxy.getProxy();
//		result.sayHello("happy new year");
		
//		CglibProxy proxy = new CglibProxy();
//		Hello hello = proxy.getProxy(HelloImpl.class);
//		hello.sayHello("hello world");
		
//		Hello hello = CglibProxy.getInstance().getProxy(HelloImpl.class);
//		hello.sayHello("hello world");
//	}
	
	
	public static void main(String[] args){
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(new HelloImpl());
//		proxyFactory.addAdvice(new GreetingBeforeAdvice());
//		proxyFactory.addAdvice(new GreetingAfterReturnningAdvice());
		proxyFactory.addAdvice(new GreetingAroundAdvice());
		Hello result = (Hello) proxyFactory.getProxy();
		result.sayHello("happy every day");
	}
	
}
