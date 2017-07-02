package cn.edu.ntu.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ClassLoaderUtil {

	//private static final Logger logger = LoggerFactory.getLogger(ClassLoaderUtil.class);
	
	/**
	 * 获取类加载器
	 */
	public static ClassLoader getClassLoader(){
		//获取当前线程中的类加载器
		return Thread.currentThread().getContextClassLoader();
	}
	
	
	/**
	 * 加载类
	 * @param className   类名
	 * @param isInitialized   提高加载性能  false   该参数影响加载顺序ClassHelperUtil、BeanHelper
	 * @return
	 */
	public static Class<?> loadClass(String className, boolean isInitialized){
		Class<?> clazz = null;
		try {
			clazz = Class.forName(className, isInitialized, getClassLoader());
		} catch (ClassNotFoundException e) {
			//logger.error("load class fail");
			e.printStackTrace();
		}
		return clazz;
	}
	
	/**
	 * 获取置顶包下面的类
	 */
	public static Set<Class<?>> getClassSet(String packageName){
		Set<Class<?>> clazzSet = new HashSet<Class<?>>();
		try {
			Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
			while(urls.hasMoreElements()){
				URL url = urls.nextElement();
				if(url != null){
					String protocal = url.getProtocol();
					if("file".equals(protocal)){
						String packagePath = url.getPath().replace("%20", " ");
						addClass(clazzSet, packagePath, packageName);
					}else if("jar".equals(protocal)){
						JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
						if(jarURLConnection != null){
							JarFile jarFile = jarURLConnection.getJarFile();
							if(jarFile != null){
								Enumeration<JarEntry> jarEntities = jarFile.entries();
								while(jarEntities.hasMoreElements()){
									JarEntry jarEntry = jarEntities.nextElement();
									if(jarEntry != null){
										String jarEntryName = jarEntry.getName();
										if(jarEntryName.endsWith(".class")){
											String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replace("/", ".");
											doAddClass(clazzSet, className);
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return clazzSet;
	}
	
	private static void addClass(Set<Class<?>> clazzSet, String packagePath, String packageName){
		File[] files = new File(packagePath).listFiles(new FileFilter(){
			public boolean accept(File file) {
				return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
			}
		});
		if(files !=null && files.length >0){
			for(File file : files){
				String fileName = file.getName();
				if(file.isFile()){
					String clazzName = fileName.substring(0, fileName.lastIndexOf("."));
					if(!StringUtils.isEmpty(packageName)){
						clazzName = packageName + "." + clazzName;
					}
					doAddClass(clazzSet, clazzName);
				}else{
					String subPackagePath = fileName;
					if(!StringUtils.isEmpty(packagePath)){
						subPackagePath = packagePath + "/" + subPackagePath;
					}
					String subPackageName = fileName;
					if(!StringUtils.isEmpty(packageName)){
						subPackageName = packageName + "." + fileName;
					}
					addClass(clazzSet, subPackagePath, subPackageName);
				}
			}
		}
	}
	
	private static void doAddClass(Set<Class<?>> clazzSet, String className){
		Class<?> clazz = loadClass(className, false);
		clazzSet.add(clazz);
	}


	public static void loadClass(String className) {
		loadClass(className, true);
	}
	
	
	public static void main(String[] args){
		Set<Class<?>> abb = getClassSet("cn.edu.ntu");
		System.out.println(abb.size());
	}
	
}











