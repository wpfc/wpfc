package cn.edu.ntu.service.impl;

import java.util.Map;

import cn.edu.ntu.annotation.Service;
import cn.edu.ntu.service.CustomerService;
import cn.edu.ntu.utils.DBUtil;
import cn.edu.ntu.utils.DatabaseUtil;
import cn.edu.ntu.wpfc.entity.Customer;
import cn.edu.ntu.wpfc.entity.FileParam;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Override
	public void createCustomer(Map<String, Object> fieldMap, FileParam fileParam) {
		DatabaseUtil.insertEntity(Customer.class, fieldMap);
		
	}

}
