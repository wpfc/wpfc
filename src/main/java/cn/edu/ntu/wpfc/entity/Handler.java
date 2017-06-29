package cn.edu.ntu.wpfc.entity;

import java.lang.reflect.Method;

public class Handler {

	/**
	 * controller类
	 */
	private Class<?> controllerClazz;
	
	/**
	 * Action请求方法
	 */
	private Method actionMethod;

	
	public Handler(){}
	
	public Handler(Class<?> controllerClazz, Method actionMethod) {
		super();
		this.controllerClazz = controllerClazz;
		this.actionMethod = actionMethod;
	}

	public Class<?> getControllerClazz() {
		return controllerClazz;
	}

	public Method getActionMethod() {
		return actionMethod;
	}
	
}
