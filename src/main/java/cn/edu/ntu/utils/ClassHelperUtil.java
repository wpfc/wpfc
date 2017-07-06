package cn.edu.ntu.utils;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import cn.edu.ntu.annotation.Controller;
import cn.edu.ntu.annotation.Service;


public class ClassHelperUtil {

	private static Set<Class<?>> CLASS_SET = new HashSet<Class<?>>();
	
	static {
		String basePackagePath = ConfigUtil.getAppBasePackage();
		CLASS_SET = ClassLoaderUtil.getClassSet(basePackagePath);
		System.out.println("Class_set :" + CLASS_SET.size());
	}
	
	/**
	 *  获取应用包名下的所有类
	 */
	public static Set<Class<?>> getClazzSet(){
		return CLASS_SET;
	}
	
	/**
	 *  获取应用包名下所有service类
	 */
	public static Set<Class<?>> getServiceClazzSet(){
		Set<Class<?>> clazzSet = new HashSet<Class<?>>();
		for(Class<?> clazz : CLASS_SET){
			if(clazz.isAnnotationPresent(Service.class)){
				clazzSet.add(clazz);
			}
		}
		return clazzSet;
	}
	
	/**
	 *  获取应用包名下所有controller类
	 */
	public static Set<Class<?>> getControllerClazzSet(){
		Set<Class<?>> clazzSet = new HashSet<Class<?>>();
		for(Class<?> clazz : CLASS_SET){
			if(clazz.isAnnotationPresent(Controller.class)){
				clazzSet.add(clazz);
			}
		}
		return clazzSet;
	}
	
	/**
	 *  获取应用包名下的bean
	 */
	public static Set<Class<?>> getBeanClazzSet(){
		Set<Class<?>> clazzSet = new HashSet<Class<?>>();
		clazzSet.addAll(getServiceClazzSet());
		clazzSet.addAll(getControllerClazzSet());
		return clazzSet;
	}
	
	/**
	 * 获取某应用包名下某父类（接口）的所有的子类（实现类）
	 */
	public static Set<Class<?>> getClassSetBySuper(Class<?> superClazz){
		Set<Class<?>> clazzSet = new HashSet<Class<?>>();
		if(CLASS_SET !=null && CLASS_SET.size() >0){
			for(Class<?> clazz : CLASS_SET){
				if(superClazz.isAssignableFrom(clazz) && !superClazz.equals(clazz)){
					clazzSet.add(clazz);
				}
			}
		}
		return clazzSet;
	}
	
	/**
	 * 获取某应用包名下拥有某个注解的所有类
	 */
	public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotation){
		Set<Class<?>> clazzSet = new HashSet<Class<?>>();
		if(CLASS_SET !=null && CLASS_SET.size() >0){
			for(Class<?> clazz : CLASS_SET){
				if(clazz.isAnnotationPresent(annotation)){
					clazzSet.add(clazz);
				}
			}
		}
		return clazzSet;
	}
	
}
