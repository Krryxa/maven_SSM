package com.krry.controller.login;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.krry.entity.User;
import com.krry.service.IUserService;
import com.krry.util.TmStringUtils;

/**
 * Controller层，作为请求转发
 * 页面所有路径的访问方法:控制层的命名空间+@RequestMapping的value
 * 如这里的/login/index.krry(后缀在xml文件配置)
 * */
@Controller  //表示是多例模式，每个用户返回的web层是不一样的
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private IUserService userService;
	
	/**
	 * 若在下面的@RequestMapping前面加上@ResponseBody，
	 * 若方法是String类型则直接返回的是字符串，不会跳转到该字符串的路径jsp文件
	 * 
	 * 所以要想跳转到某一jsp页面，不能加上@ResponseBody
	 * 这个@ResponseBody适合ajax返回的数据
	 * 
	 */
	
	/**
	 * 在控制层不加@ResponseBody的情况下，return值默认是转发到某路径,不会显示转发路径，显示的是未转发前的路径
	 * 若要重定向，加上redirect:这里默认是当前命名空间的转发，要跳转到另一个control层，需要返回上一级../
	 * 
		这里使用重定向，返回命名空间的上一级，重定向到命名空间为Krry下的index 
		return "redirect:../index";
		
		注意：
		转发不会显示转发路径，显示的是未转发前的路径
	 *  重定向显示的是跳转之后的路径
	 */
	
	/**
	 * 进入登录界面
	 * @return
	 */
	@RequestMapping("/index")
	public String index(){
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("index/login"); //跳到此页面
//		return modelAndView;
		return "index/login";   //默认是转发，不会显示转发路径
	}
	
	/**
	 * 点击登录
	 * com.krry.controller.login 
	 * 方法名：login
	 * @author krry 
	 * @param request
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping(method=RequestMethod.POST,value="/logined")
	public String login(HttpServletRequest request){
		//获取用户和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//如果邮箱和密码为null,那么就返回已null标识
		if(TmStringUtils.isEmpty(username) )return "index/allError";
		if(TmStringUtils.isEmpty(password))return "index/allError";
		
		//密码进行加密处理
		password = TmStringUtils.md5Base64(password);
		
		//根据邮箱或昵称查询，用户是否存在
		User user = userService.getLogin(username);
		
		//如果存在
		if(user!=null){
			
			User userpas = userService.getpass(username, password);
			if(userpas!=null){
				//如果密码正确
				//将用户信息放入到会话中...
				request.getSession().setAttribute("user", user);
				
				//这里使用重定向，返回命名空间的上一级，重定向到命名空间为Krry下的index.krry
				return "redirect:../index";
			}else{
				//如果密码错误
				System.out.println("密码错误");
				return "index/error";
			}
		}else{
			//如果不存在，代码邮箱和密码输入有误
			System.out.println("用户不存在");
			return "index/error";
		}
	}
	
	/**
	 * 退出登录控制层
	 * com.krry.controller.login 
	 * 方法名：logout
	 * @author krry 
	 * @param request
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping(method=RequestMethod.GET,value="/logout")
	public String logout(HttpServletRequest request){
		request.getSession().invalidate(); //清空session值
		return "index/index";
	}
	
	/**
	 * 打开注册界面层
	 * @return
	 */
	@RequestMapping("/rege")
	public String rege(){
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("index/login"); //跳到此页面
//		return modelAndView;
		return "index/resgi";
	}
	
	/**
	 * 注册控制层
	 * com.krry.controller.login 
	 * 方法名：resig
	 * @author krry 
	 * @param request
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	@RequestMapping(method=RequestMethod.POST,value="/resig")
	public String resig(HttpServletRequest request){
		//获取用户和密码
		String name = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		//如果邮箱和密码为null,那么就返回已null标识
		if(TmStringUtils.isEmpty(name) )return "index/allError";
		if(TmStringUtils.isEmpty(email))return "index/allError";
		if(TmStringUtils.isEmail(password))return "index/allError";
		
		//密码进行加密处理
		password = TmStringUtils.md5Base64(password);
		//根据昵称查询，用户是否存在
		User user1 = userService.getothernameres(name);
		//根据账号查询，用户是否存在
		User user2 = userService.getemailres(email);
		
		//若存在
		if(user1 != null){ //昵称重复
			return "index/allError";
		}
		if(user2 != null){ //email重复
			return "index/allError";
		}

		//格式化时间类型
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = sdf.format(new Date());
		
		String id = UUID.randomUUID().toString();
		//执行到这里，说明可以注册
		User newUser = new User(id, name, password, email, nowTime);
		//调用注册方法
		userService.saveUser(newUser);
		
		//将信息设置session作用域
		request.getSession().setAttribute("user", newUser);

		/**
		 * 这里使用重定向，返回命名空间的上一级，重定向到index
		 */
		return "redirect:../index";
	}
	
}



