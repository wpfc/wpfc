package cn.edu.ntu.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import cn.edu.ntu.wpfc.entity.CodecUtil;
import cn.edu.ntu.wpfc.entity.FieldParam;
import cn.edu.ntu.wpfc.entity.Param;

/**
 * 
 * @author wpfc
 *
 */
public class RequestHelper {

	/**
	 * 创建请求对象（适用普通form表单提交）
	 * @return
	 */
	public static Param createParam(HttpServletRequest request){
		List<FieldParam> formParamList = new ArrayList<FieldParam>();
		formParamList.addAll(parseParameterNames(request));
		formParamList.addAll(parseInputStream(request));
		return null;
	}

	/**
	 * 从请求流里获取数据
	 * @param request
	 * @return
	 */
	private static List<FieldParam> parseInputStream(HttpServletRequest request) {
		List<FieldParam> parameterList = new ArrayList<FieldParam>();
		try {
			String body = CodecUtil.decodeURL(StreamUtil.getStringFromStrean(request.getInputStream()));
			if(!StringUtils.isEmpty(body)){
				String[] kvs = StringUtil.splitString(body, "&");
				if(ArrayUtils.isNotEmpty(kvs)){
					for(String item : kvs){
						if(StringUtils.isEmpty(item)){
							continue;
						}
						String[] array = StringUtil.splitString(item, "=");
						if(ArrayUtils.isNotEmpty(array) && array.length == 2){
							parameterList.add(new FieldParam(array[0], array[1]));
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return parameterList;
	}

	/**
	 * 从请求参数中获取请求值
	 * @param request
	 * @return
	 */
	private static List<FieldParam> parseParameterNames(HttpServletRequest request) {
		List<FieldParam> parameterList = new ArrayList<FieldParam>();
		Enumeration<String> parameterNames = request.getParameterNames();
		while(parameterNames.hasMoreElements()){
			String fieldName = parameterNames.nextElement();
			String[] values = request.getParameterValues(fieldName);
			if(ArrayUtils.isNotEmpty(values)){
				Object fieldValue = null;
				if(values.length == 1){
					fieldValue = values[0];
				}else{
					StringBuffer sb = new StringBuffer("");
					for(int i=0; i<values.length; i++){
						sb.append(values[i]);
						if(i != values.length -1){
							sb.append(StringUtil.SAPARATOR);
						}
					}
					fieldValue = sb.toString();
				}
				parameterList.add(new FieldParam(fieldName, fieldValue));
			}
		}
		
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
