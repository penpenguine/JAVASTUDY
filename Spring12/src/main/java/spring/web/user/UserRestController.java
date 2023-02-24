package spring.web.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import spring.domain.User;

@RestController
@RequestMapping("/user/*")
public class UserRestController {
 

	public UserRestController() {
		System.out.println("==>UserRestController default Constructor call............");
	}
	
	@RequestMapping(value="json/{value}", method=RequestMethod.GET)
	public Map getUser(@PathVariable String value,@RequestParam("name") String name, @RequestParam("age") int age )throws Exception{
		
		System.out.println();
		System.out.println(value);
		System.out.println(name);
		System.out.println(age);
		
		User user = new User();
		user.setUserId("aaa");
		user.setUserName("bbb");
		user.setAge(100);
		System.out.println(user);
		
		Map map =new HashMap();
		map.put("user", user);
		map.put("message","ok");
		
		return map;
	}
}

