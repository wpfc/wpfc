package cn.edu.ntu.controller;

import cn.edu.ntu.annotation.Action;
import cn.edu.ntu.annotation.Controller;
import cn.edu.ntu.cons.SysConstant;
import cn.edu.ntu.wpfc.entity.Data;
import cn.edu.ntu.wpfc.entity.Param;
import cn.edu.ntu.wpfc.entity.View;

@Controller
public class UserController {

	@Action(value = "/getUserInfoList", method = SysConstant.RequestMethod.GET)
	public View getUserInfoList(Param param){
		
		View view = new View();
		view.setPath("userList");
		return view;
	}
	
	@Action(value = "/getUserInfoById", method = SysConstant.RequestMethod.GET)
	public Data getUserInfoById(Param param){
		
		Data data = new Data(null);
		data.setMsg("根据用户ID查询用户信息");
		return data;
	}
	
}
