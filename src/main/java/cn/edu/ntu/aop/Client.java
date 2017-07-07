package cn.edu.ntu.aop;

import java.util.ArrayList;
import java.util.List;

import cn.edu.ntu.proxy.Hello;
import cn.edu.ntu.proxy.HelloImpl;

public class Client {

	public static void main(String[] args){
		List<Proxy> proxyList = new ArrayList<Proxy>();
		proxyList.add(new AfterProxy());
		proxyList.add(new BeforeProxy());
		Hello hello = ProxyManager.getInstance().getProxy(HelloImpl.class, proxyList);
		hello.sayHello("new aop");
	}
	
}
