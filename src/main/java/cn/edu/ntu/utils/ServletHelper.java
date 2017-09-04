package cn.edu.ntu.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletHelper {

	private static ThreadLocal<ServletHelper> SERVLET_INFO_HOLDER = new ThreadLocal<ServletHelper>();
	
	private HttpServletRequest request;
	
	private HttpServletResponse response;
	
	private ServletHelper(HttpServletRequest request, HttpServletResponse response){
		this.request = request;
		this.response = response;
	}
	
	public static void init(HttpServletRequest request, HttpServletResponse response){
		SERVLET_INFO_HOLDER.set(new ServletHelper(request, response));
	}
	
	public static void distory(){
		SERVLET_INFO_HOLDER.remove();
	}
	
	public static HttpServletRequest getHttpServletRequest(){
		return SERVLET_INFO_HOLDER.get().request;
	}
	
	public static HttpServletResponse getHttpServletResponse(){
		return SERVLET_INFO_HOLDER.get().response;
	}
	
	public static HttpSession getHttpSession(){
		return getHttpServletRequest().getSession();
	}
}
