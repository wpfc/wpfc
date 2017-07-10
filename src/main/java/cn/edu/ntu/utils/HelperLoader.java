package cn.edu.ntu.utils;

import cn.edu.ntu.aop.AopHelper;

public class HelperLoader {

	/**
	 * 加载相应的helper类
	 */
	public static void init() {
		
		Class<?>[] initClazz = {
			ClassHelperUtil.class,
			BeanHelper.class,
			AopHelper.class,
			IocHelper.class,
			ControllerHelper.class
		};
		
		//加载相应的类
		for(Class<?> clazz : initClazz){
			ClassLoaderUtil.loadClass(clazz.getName());
		}
	}
	
}
