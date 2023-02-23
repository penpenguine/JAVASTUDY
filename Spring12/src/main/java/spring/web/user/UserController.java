package spring.web.user;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.domain.User;
import spring.service.user.UserService;



@Controller
@RequestMapping("/user")
public class UserController {
 
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	public UserController() {
		System.out.println("==>UserController default Constructor call............");
	}
	
	@RequestMapping("/logon")
	public ModelAndView logon(HttpSession session){
		
		System.out.println("\n :: ==>logon()logon() start.....");
		
		String message ="[logon()] 아이디 패스워트 3자이상";
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/user/logon.jsp");
		modelAndView.addObject("message",message);
		
		System.out.println("\n :: ==>logon()logon() end.....");

		return modelAndView;
	}
	
	@RequestMapping("/home")
	public ModelAndView home(HttpSession session){
		
		System.out.println("\n :: ==>home() start.....");

		String message = "[home()] WELCOME ";
	
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/user/home.jsp");
		modelAndView.addObject("message",message);
		
		System.out.println("\n :: ==>home() end.....");

		return modelAndView;
	}
	
	@RequestMapping(value="logonAction",method=RequestMethod.GET)
	public ModelAndView logonAction() {
		
		System.out.println("\n :: ==>logonAction() method =RequestMethod GET start.....");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/logon.do");
		System.out.println("\n :: ==>logonAction() method =RequestMethod GET end.....");
		return modelAndView;
	}
	
	@RequestMapping(value="logonAction",method=RequestMethod.POST)
	public ModelAndView logonAction(@ModelAttribute("user") User user,HttpSession session) throws Exception{
		
		System.out.println("\n :: ==>logonAction() method =RequestMethod POST start.....");
		
		String viewName = "/user/logon.jsp";
		
		User returnUser = userService.getUser(user.getUserId());
		if(returnUser.getPassword().equals(user.getPassword())) {
			returnUser.setActive(true);
			user = returnUser;
		}
			
		if(user.isActive()) {
			viewName = "/user/home.jsp";
			session.setAttribute("sessionUser", user);
		}	
		
		System.out.println("[action : " + viewName + "]");
		
		String message = null;
		if (viewName.equals("/user/home.jsp")) {
			message = "[logonAction()  ] WELCOME";
		}else {
			message = "[logonAction()] 아이디 패스워트 3자이상";
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(viewName);
		modelAndView.addObject("message",message);
		
		System.out.println("\n :: ==>logonAction() method =RequestMethod POST end.....");

		return modelAndView;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpSession session){
		
		System.out.println("\n :: ==>logout() start.....");
		
		session.removeAttribute("sessionUser");
		
		String message = "[logout()] 아이디 패스워트 3자이상";
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/user/logon.jsp");
		modelAndView.addObject("message",message);
		
		System.out.println("\n :: ==>logout() end.....");

		return modelAndView;
	}
}
