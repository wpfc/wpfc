package cn.edu.ntu.controller;

import java.util.HashMap;
import java.util.Map;

import cn.edu.ntu.annotation.Action;
import cn.edu.ntu.annotation.Controller;
import cn.edu.ntu.annotation.Inject;
import cn.edu.ntu.cons.SysConstant.RequestMethod;
import cn.edu.ntu.wpfc.entity.Data;
import cn.edu.ntu.wpfc.entity.FileParam;
import cn.edu.ntu.wpfc.entity.Param;
import cn.edu.ntu.wpfc.entity.View;

@Controller
public class CustomerController {

	@Inject
	private CustomerService customerService;
	
	@Action(value="/addCustomerPage", method=RequestMethod.GET)
	public View addCustomerPage(){
		View view = new View();
		view.setPath("customer/add");
		/**
		 * TODO   优化点1:获取项目发布路径
		 */
		view.addModel("BASE", "/wpfc");
		return view;
	}
	
	@Action(value="/addCustomerPage", method=RequestMethod.POST)
	public Data addCustomerPage(Param param){
		Map<String, Object> fieldParamMap = param.getFieldParamMap();
		FileParam fileParam = param.getFileParamByFieldName("photo");
		return null;
	}

}
