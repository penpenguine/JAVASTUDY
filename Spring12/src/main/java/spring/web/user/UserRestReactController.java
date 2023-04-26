package spring.web.user;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.domain.User;

@RestController
@RequestMapping("/user/*")
public class UserRestReactController {
	///Field
	///Constructor
	public UserRestReactController(){
		System.out.println(":: UserRestReactController default Contructor call");
	}
	
	//http://IP:8080/Spring15/user/getUser  : GET
	//http://192.168.0.175:8080/Spring15/user/getUser?name=홍길동&age=111
	@RequestMapping(value="getUser" , method=RequestMethod.GET )
	public User getUser	(	@RequestParam("name") String name,
											@RequestParam("age") int age) throws Exception{
			
		System.out.println("http://IP:8080/Spring15/user/getUser : GET");
		
		//================= GET : QueryString 한글처리 ????? =====================//
		System.out.println(name);
		
		System.out.println(URLEncoder.encode(name, "EUC-KR"));
		System.out.println(URLEncoder.encode(name, "UTF-8"));
		System.out.println(URLDecoder.decode(name, "EUC-KR"));
		System.out.println(URLDecoder.decode(name, "UTF-8"));
		
		System.out.println(convertKo(name));
		//================= GET : QueryString 한글처리 ????? =====================//
		System.out.println(age);
		
		User user = new User();
		user.setUserId("홍길동");
		user.setUserName("1111");
		user.setPassword("1111");
		user.setAge(222);
		
		//==> 비동기로 움직이는 것 확인용.
		Thread.sleep(3000);
			
		System.out.println(user);
		
		return user;
	}

	//http://IP:8080/Spring15/user/getUserList  : GET
	//http://192.168.0.175:8080/Spring15/user/getUserList?name=홍길동&age=111
	@RequestMapping(value="getUserList" , method=RequestMethod.GET )
	public List<User> getUserList(	@RequestParam("name") String name,
															@RequestParam("age") int age) throws Exception{
		
		System.out.println("http://IP:8080/Spring15/user/getUserList  : GET");
		System.out.println(name);
		System.out.println(age);
		
		List<User> list = new ArrayList<User>();
		
		User user01 = new User();
		user01.setUserId("AAA");
		user01.setUserName("AAA");
		user01.setAge(100);
		list.add(user01);
		
		User user02 = new User();
		user02.setUserId("BBB");
		user02.setUserName("BBB");
		user02.setAge(200);
		list.add(user02);
		
		User user03 = new User();
		user03.setUserId("CCC");
		user03.setUserName("CCC");
		user03.setAge(300);
		list.add(user03);
		
		System.out.println(list);

		return list;
	}
	
	//http://IP:8080/Spring15/user/getUser  : POST
	//http://192.168.0.175:8080/Spring15/user/getUser    {"name":"홍길동","age":"1111"}
	@RequestMapping(value="getUser" , method=RequestMethod.POST )
	public User getUser	( @RequestBody User user ) throws Exception{
		System.out.println();
		System.out.println("[ From Client Data ]");
		System.out.println(user);
		
		System.out.println("[To Client Data]");
		User returnUser = new User();
		returnUser.setUserId("AAA");
		returnUser.setUserName("홍길동");
		returnUser.setAge(100);
		if(user.getUserId().equals("홍길동")) {
			returnUser.setActive(true);
		}
		System.out.println(returnUser);
		
		return returnUser;
	}
	
	//http://IP:8080/Spring15/user/getUserList  : POST   {"name":"홍길동","age":"1111"}
	@RequestMapping(value="getUserList" , method=RequestMethod.POST )
	public List<User> getUserList	( @RequestBody User user ) throws Exception{	

		System.out.println("http://IP:8080/Spring15/user/getUserList : POST");
		System.out.println("[ From Client Data ]");
		System.out.println(user);

		List<User> list = new ArrayList<User>();
		
		User user01 = new User();
		user01.setUserId("AAA");
		user01.setUserName("AAA");
		user01.setAge(100);
		list.add(user01);
		
		User user02 = new User();
		user02.setUserId("BBB");
		user02.setUserName("BBB");
		user02.setAge(200);
		list.add(user02);
		
		User user03 = new User();
		user03.setUserId("CCC");
		user03.setUserName("CCC");
		user03.setAge(300);
		list.add(user03);
		
		System.out.println("[To Client Data]");
		System.out.println(list);

		return list;
	}
	
	//Method //==>한글변환(GET) 
	private String convertKo(String paramValue){
		String convertParamValue = null;
		try{
			//==> API를 확인요망
			byte[] b = paramValue.getBytes("8859_1");
			convertParamValue = new String(b,"EUC_KR");
		}catch(UnsupportedEncodingException uee){
			System.out.println("한글 변환중 Exception 발생");
			uee.printStackTrace();
		}
		return convertParamValue;
	}

}