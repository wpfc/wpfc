package cn.edu.ntu.cons;

public interface ConfigConstant {

	
	final String CONFIG_FILE = "smart.properties";
	
	final String JDBC_DRIVER = "smart.framework.jdbc.driver";
	final String JDBC_URL = "smart.framework.jdbc.url";
	final String JDBC_USERNAME = "smart.framework.jdbc.username";
	final String JDBC_PASSWORD = "smart.framework.jdbc.password";
	
	final String BASE_PACKAGE = "smart.framework.app.base_package";
	final String JSP_PATH = "smart.framework.app.jsp_path";
	final String ASSET_PATH = "smart.framework.app.asset_path";
	
	final String APP_BASE_PACKAGE = "cn.edu.ntu";
	final String APP_JSP_PATH_PREFIX = "/WEB-INF/jsp/";
	final String APP_JSP_PATH_SUFFIX = ".jsp";
	final String APP_ASSET_PATH = "/asset/";
	
	final String APP_UPLOAD_LIMIT = "smart.framework.app.upload_limit";
}
