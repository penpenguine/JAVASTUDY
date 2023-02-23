package spring.common.web;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import spring.domain.User;
public class LogonCheckInterceptor extends HandlerInterceptorAdapter{
	
	public LogonCheckInterceptor() {
		System.out.println("==>LogonCheckInterceptor default Constructor call............");
	}
	
	public boolean preHandle(HttpServletRequest request,HttpServletResponse responese,Object handler)throws Exception{
		System.out.println("\n[LogonCheckInterceptor start ...............");
		
		//=> 로그인 유무확인
		HttpSession session = request.getSession(true);
		User sessionUser = null;
		if((sessionUser= (User)session.getAttribute("sessionUser") )== null){
			sessionUser = new User();
		}
		
		//==> 로그인 한 회원
		if(sessionUser.isActive()) {
			
			String uri = request.getRequestURI();
			if(uri.indexOf("lononAction") != -1 || uri.indexOf("logon") != -1) {
				request.getRequestDispatcher("/user002/home.jsp").forward(request, responese);
				System.out.println("[로그인 상태 ..로그인 후불필요한 요구...}");
				System.out.println("[LogonCheckInterceptor end .......]\n");
				return false;
			}
			System.out.println("[로그인 상태...]");
			System.out.println("\n[LogonCheckInterceptor end ...............");
			return true;

		}else {//==>미로그인 한 회원이라면 
			String uri = request.getRequestURI();
			if(uri.indexOf("logonAction") != -1 || uri.indexOf("logon") != -1) {
				System.out.println("[로그시도 상태...]");
				System.out.println("\n[LogonCheckInterceptor end ...............");
				return true;
			}
			request.getRequestDispatcher("/user002/logon.jsp").forward(request, responese);
			System.out.println("[로그인 이전...]");
			System.out.println("[LogonCheckInterceptor end .....]\n");
			return false;
			
		}
	}
}//e class
