package cn.edu.ntu.service;

import java.util.Map;

import cn.edu.ntu.wpfc.entity.FileParam;

public interface CustomerService {

	void createCustomer(Map<String, Object> fieldMap, FileParam fileParam);
	
}
