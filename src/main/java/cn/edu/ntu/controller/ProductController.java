package cn.edu.ntu.controller;

import cn.edu.ntu.annotation.Action;
import cn.edu.ntu.annotation.Controller;
import cn.edu.ntu.annotation.Inject;
import cn.edu.ntu.cons.SysConstant.RequestMethod;
import cn.edu.ntu.service.ProductService;
import cn.edu.ntu.wpfc.entity.Param;

@Controller
public class ProductController {

	@Inject
	private ProductService productService;
	
	@Action(value="/addproduct", method=RequestMethod.POST)
	public void addproduct(Param param){
		try {
			try {
				productService.testTransaction();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
