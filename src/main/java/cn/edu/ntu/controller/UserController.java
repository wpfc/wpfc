package cn.edu.ntu.controller;

import cn.edu.ntu.annotation.Action;
import cn.edu.ntu.annotation.Controller;
import cn.edu.ntu.annotation.Inject;
import cn.edu.ntu.cons.SysConstant;
import cn.edu.ntu.service.UserService;
import cn.edu.ntu.wpfc.entity.Data;
import cn.edu.ntu.wpfc.entity.Param;
import cn.edu.ntu.wpfc.entity.View;

@Controller
public class UserController {

	@Inject
	private UserService userService;
	
	
	@Action(value = "/getUserInfoList", method = SysConstant.RequestMethod.GET)
	public View getUserInfoList(Param param){
		
		View view = new View();
		view.setPath("userList");
		return view;
	}
	
	@Action(value = "/getUserInfoById", method = SysConstant.RequestMethod.GET)
	public Data getUserInfoById(Param param){
		
		Data data = new Data(null);
		userService.getUserInfoById(1);
		return data;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
}
