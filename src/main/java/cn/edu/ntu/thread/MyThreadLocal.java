package cn.edu.ntu.thread;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MyThreadLocal<T> {

	private Map<Thread, T> containerMap = Collections.synchronizedMap(new HashMap<Thread, T>());
	
	public T get(){
		Thread current = Thread.currentThread();
		T result = containerMap.get(current);
		if(result == null){
			result = initialValue();
			containerMap.put(current, result);
		}
		return result;
	}
	
	public void set(T value){
		containerMap.put(Thread.currentThread(), value);
	}
	
	public void remove(){
		if(containerMap.containsKey(Thread.currentThread())){
			containerMap.remove(Thread.currentThread());
		}
	}
	
	protected T initialValue(){
		return null;
	}
	
}
