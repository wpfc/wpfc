package cn.edu.ntu.utils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;

import cn.edu.ntu.annotation.Action;
import cn.edu.ntu.wpfc.entity.Handler;
import cn.edu.ntu.wpfc.entity.Request;

public class ControllerHelper {

	private static Map<Request, Handler> REQUEST_HANDLER_MAP = new HashMap<Request, Handler>();
	
	static{
		Set<Class<?>> controllerClassSet = ClassHelperUtil.getControllerClazzSet();
		if(!controllerClassSet.isEmpty()){
			for(Class<?> clazz : controllerClassSet){
				Method[] methods = clazz.getDeclaredMethods();
				if(!ArrayUtils.isEmpty(methods)){
					for(Method method : methods){
						if(method.isAnnotationPresent(Action.class)){
							Action action = method.getAnnotation(Action.class);
							String requestMethod = action.method();
							String requestPath = action.value();
							Request request = new Request(requestMethod, requestPath);
							Handler handler = new Handler(clazz, method);
							REQUEST_HANDLER_MAP.put(request, handler);
						}
					}
				}
			}
		}
		
	}
	
	/**
	 * 获取handler
	 * @param request
	 * @return
	 */
	public static Handler getHandler(Request request){
		return REQUEST_HANDLER_MAP.get(request);
	}
	
	/**
	 * 获取handler
	 * @param requestMethod
	 * @param requestPath
	 * @return
	 */
	public static Handler getHandler(String requestMethod, String requestPath){
		Request request = new Request(requestMethod, requestPath);
		return getHandler(request);
	}
}
