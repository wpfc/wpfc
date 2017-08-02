package cn.edu.ntu.controller;

import cn.edu.ntu.annotation.Action;
import cn.edu.ntu.annotation.Controller;
import cn.edu.ntu.cons.SysConstant.RequestMethod;
import cn.edu.ntu.wpfc.entity.Customer;
import cn.edu.ntu.wpfc.entity.Data;
import cn.edu.ntu.wpfc.entity.Param;
import cn.edu.ntu.wpfc.entity.View;

@Controller
public class CustomerController {

	@Action(value="/addCustomerPage", method=RequestMethod.GET)
	public View addCustomerPage(Param param){
		View view = new View();
		view.setPath("customer/add");
		/**
		 * TODO   优化点1:获取项目发布路径
		 */
		view.addModel("BASE", "/wpfc");
		return view;
	}
	
	@Action(value="/addCustomerPage", method=RequestMethod.POST)
	public Data addCustomerPage(Customer customer){
		return null;
	}

}
