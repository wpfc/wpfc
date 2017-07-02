package cn.edu.ntu.utils;

public class HelperLoader {

	/**
	 * 加载相应的helper类
	 */
	public static void init() {
		
		Class<?>[] initClazz = {
			ClassHelperUtil.class,
			BeanHelper.class,
			IocHelper.class,
			ControllerHelper.class
		};
		
		//加载相应的类
		for(Class<?> clazz : initClazz){
			ClassLoaderUtil.loadClass(clazz.getName());
		}
	}
	
}
