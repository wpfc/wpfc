package cn.edu.ntu.wpfc.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 视图类
 * @author Administrator
 *
 */
public class View {

	private String path;
	
	private Map<String, Object> model = new HashMap<String, Object>();
	
	public View(){}
	
	public View(String path,Map<String, Object> model) {
		this.path = path;
		this.model = model;
	}
	
	public View(String path) {
		this.path = path;
		this.model = new HashMap<String, Object>();
	}

	public View addModel(String key, String value){
		model.put(key, value);
		return this;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(Map<String, Object> model) {
		this.model = model;
	}
	
	
}
