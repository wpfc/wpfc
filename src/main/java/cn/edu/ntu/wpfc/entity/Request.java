package cn.edu.ntu.wpfc.entity;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Request {

	/**
	 * 请求方法   GET/POST
	 */
	private String method;
	
	/**
	 * 请求路径
	 */
	private String path;
	
	public Request(){}
	
	public Request(String method, String path){
		this.method = method;
		this.path = path;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	 @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
    
    
    public static void main(String[] args){
    	Map<Request, String> map = new HashMap<Request, String>();
    	Request r1 = new Request("123", "123");
    	Request r2 = new Request("123", "123");
    	map.put(r1, "helloworld");
    	System.out.println(r1.equals(r2));
    	System.out.println(map.get(r2));
    }
}
