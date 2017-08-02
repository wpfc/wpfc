package cn.edu.ntu.wpfc.entity;

import java.util.Map;

/**
 * 参数包装类
 * @author Administrator
 *
 */
public class Param {

	private Map<String, Object> paramMap;
	
	public Param(){}
	
	public Param(Map<String, Object> paramMap){
		this.paramMap = paramMap;
	}
	
	/**
	 * 根据参数名获取参数值
	 * @param paramName
	 * @return
	 */
	public Object getParamValue(String paramName){
		return paramMap.get(paramName);
	}
	
	public Map<String, Object> getParamMap(){
		return paramMap;
	}
	
	public boolean isEmpty(){
		return paramMap == null || paramMap.isEmpty();
	}
}
