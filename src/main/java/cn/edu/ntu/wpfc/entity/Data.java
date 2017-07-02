package cn.edu.ntu.wpfc.entity;

import java.util.HashMap;

public class Data {

	private String code;
	
	private String msg;
	
	private HashMap<String, Object> data;
	
	public Data(HashMap<String, Object> data){
		this.data = data;
	}

	public HashMap<String, Object> getData() {
		return data;
	}

	public void setData(HashMap<String, Object> data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
