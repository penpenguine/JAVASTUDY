package spring.web.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.domain.User;


@Controller
@RequestMapping("/user/*")
public class UserController {
 

	public UserController() {
		System.out.println("==>User004Controller default Constructor call............");
	}
	
	@RequestMapping("testView")
	public ModelAndView testView(){
		return new ModelAndView("/test/testView.jsp");
	}
	
	@RequestMapping(value="getUser/{userId}", method=RequestMethod.GET)
	public ModelAndView getUser(@PathVariable String userId){
		
		System.out.println();
		System.out.println(userId);
		User user = new User();
		user.setUserId(userId);
		
		return new ModelAndView("/test/testView.jsp");
	}
	
	@RequestMapping(value="getUser/{userId}", method=RequestMethod.POST)
	public ModelAndView getUser(@PathVariable String userId,@ModelAttribute("user")User user){
		
		System.out.println();
		System.out.println(userId);
		System.out.println(user);
		user.setUserId(userId);
		
		return new ModelAndView("/test/testView.jsp");
	}

}
