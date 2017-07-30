package cn.edu.ntu.wpfc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import cn.edu.ntu.cons.ConfigConstant;
import cn.edu.ntu.utils.ControllerHelper;
import cn.edu.ntu.utils.HelperLoader;
import cn.edu.ntu.utils.JsonUtil;
import cn.edu.ntu.utils.ReflectionUtil;
import cn.edu.ntu.utils.StreamUtil;
import cn.edu.ntu.wpfc.entity.CodecUtil;
import cn.edu.ntu.wpfc.entity.Data;
import cn.edu.ntu.wpfc.entity.Handler;
import cn.edu.ntu.wpfc.entity.Param;
import cn.edu.ntu.wpfc.entity.View;

/**
 * 请求转发器
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispathcerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		//加载所有配置项及类
		HelperLoader.init();
		//获取ServetContext对象，初始化servlet
		ServletContext sc = servletConfig.getServletContext();
		//???
		//注册处理JSP的Servlet
		ServletRegistration jspServlet = sc.getServletRegistration("jsp");
		jspServlet.addMapping(ConfigConstant.APP_JSP_PATH_PREFIX + "*");
		//注册处理静态文件的Servlet
		ServletRegistration defalutServlet = sc.getServletRegistration("default");
		defalutServlet.addMapping(ConfigConstant.APP_ASSET_PATH + "*");
		Map<String, ? extends ServletRegistration> map = sc.getServletRegistrations();
		for(Entry<String, ? extends ServletRegistration> entry : map.entrySet()){
			System.out.println("registrname : " + entry.getKey());
		}
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取请求路径，请求方法
		String requestMethod = req.getMethod().toUpperCase();
		String requestPath = req.getPathInfo();
		//获取处理Handler
		Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
		if(handler != null){
			//容器中每个对象都是单例的，故初始化前先到BEAN_MAP中看下实例是否已经存在
			Object controllerInstance = ReflectionUtil.newInstance(handler.getControllerClazz());
			Method actionMethod = handler.getActionMethod();
			//处理请求对象
			Map<String, Object> paramMap = new HashMap<String, Object>();
			Enumeration<String> paramNames = req.getParameterNames();
			while(paramNames.hasMoreElements()){
				String paramName = paramNames.nextElement();
				Object paramValue = req.getParameterValues(paramName);
				paramMap.put(paramName, paramValue);
			}
			String body = CodecUtil.decodeURL(StreamUtil.getStringFromStrean(req.getInputStream()));
			if(!StringUtils.isEmpty(body)){
				String[] params = StringUtils.split(body, "&");
				if(params != null && params.length > 0){
					for(String param : params){
						String[] temp = param.split("=");
						if(temp !=null && 2 == temp.length){
							paramMap.put(temp[0], temp[1]);
						}
					}
				}
			}
			//处理参数
			Param param = new Param();
			if(!paramMap.isEmpty()){
				param = new Param(paramMap);
			}
			//调用方法
			Object result = null;
			if(param.isEmpty()){
				result = ReflectionUtil.invokeMethod(controllerInstance, actionMethod);
			}else{
				result = ReflectionUtil.invokeMethod(controllerInstance, actionMethod, param);
			}
			//返回值类型
			if(result instanceof View){
				View view = (View) result;
				String path = view.getPath();
				if(!StringUtils.isEmpty(path)){
					//重定向的path请求
					if(path.startsWith("/")){
						resp.sendRedirect(req.getContextPath() + path);
					}else{
						Map<String, Object> model = ((View) result).getModel();
						if(!model.isEmpty()){
							for(Map.Entry<String, Object> entry : model.entrySet()){
								req.setAttribute(entry.getKey(), entry.getValue());
							}
						}
						req.getRequestDispatcher(ConfigConstant.APP_JSP_PATH_PREFIX + path + ConfigConstant.APP_JSP_PATH_SUFFIX).forward(req, resp);
					}
				}
			}else{
				Data data = (Data) result;
				if(data != null){
					resp.setContentType("application/json");
					resp.setCharacterEncoding("UTF-8");
					PrintWriter out = resp.getWriter();
					String str = JsonUtil.toJson(data);
					out.write(str);
					out.flush();
					out.close();
				}
			}
		}
	}
	
	@Override
	public void destroy() {
		super.destroy();
	}
}
