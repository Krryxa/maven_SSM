package com.krry.controller.index;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.krry.entity.User;
import com.krry.mapper.UserMapper;
import com.krry.util.TmStringUtils;

/**
 * KrryController
 * controller层，作为请求转发
 * @author asusaad
 *
 */
@Controller  //表示是多例模式，每个用户返回的web层是不一样的
public class KrryController {
	
	@RequestMapping("/index")
	public String index(){
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("index/login"); //跳到此页面
//		return modelAndView;
		return "index/index";
	}
	
}



