package cn.edu.ntu.utils;

import java.util.Properties;

import cn.edu.ntu.cons.ConfigConstant;

public class ConfigUtil {

	
	private final static Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);
	
	
	/**
     * 获取应用基础包名
     *
     * @return
     */
    public static String getAppBasePackage() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.BASE_PACKAGE);
    }

    /**
     * 获取JSP路径
     *
     * @return
     */
    public static String getAppJspPath() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JSP_PATH);
    }

    /**
     * 获取应用静态资源路径
     *
     * @return
     */
    public static String getAppAssetPath() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.ASSET_PATH, "/asset/");
    }
    
    /**
     * 获取数据库驱动
     * @return
     */
    public static String getJdbcDriver(){
    	return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_DRIVER);
    }
    
    /**
     * 获取数据库url
     * @return
     */
    public static String getJdbcUrl(){
    	return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_URL);
    }
    
    /**
     * 获取数据库用户名
     * @return
     */
    public static String getJdbcUSerName(){
    	return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_USERNAME);
    }
    
    /**
     * 获取数据库密码
     * @return
     */
    public static String getJdbcPassword(){
    	return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_PASSWORD);
    }
    
    /**
     * 获取文件上传数量限制
     * @return
     */
    public static int getAppUploadLimit(){
    	return PropsUtil.getInt(CONFIG_PROPS, ConfigConstant.APP_UPLOAD_LIMIT, 10);
    }
}
