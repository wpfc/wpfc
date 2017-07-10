package cn.edu.ntu.service.impl;

import cn.edu.ntu.annotation.Service;
import cn.edu.ntu.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	public int code = 1;
	
	public String abc = "hello";
	
	public void getUserInfoById(long id){
		System.out.println("get user info by id : " + id);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getAbc() {
		return abc;
	}

	public void setAbc(String abc) {
		this.abc = abc;
	}
	
}
