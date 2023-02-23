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
		
		//=> �α��� ����Ȯ��
		HttpSession session = request.getSession(true);
		User sessionUser = null;
		if((sessionUser= (User)session.getAttribute("sessionUser") )== null){
			sessionUser = new User();
		}
		
		//==> �α��� �� ȸ��
		if(sessionUser.isActive()) {
			
			String uri = request.getRequestURI();
			if(uri.indexOf("lononAction") != -1 || uri.indexOf("logon") != -1) {
				request.getRequestDispatcher("/user002/home.jsp").forward(request, responese);
				System.out.println("[�α��� ���� ..�α��� �ĺ��ʿ��� �䱸...}");
				System.out.println("[LogonCheckInterceptor end .......]\n");
				return false;
			}
			System.out.println("[�α��� ����...]");
			System.out.println("\n[LogonCheckInterceptor end ...............");
			return true;

		}else {//==>�̷α��� �� ȸ���̶�� 
			String uri = request.getRequestURI();
			if(uri.indexOf("logonAction") != -1 || uri.indexOf("logon") != -1) {
				System.out.println("[�α׽õ� ����...]");
				System.out.println("\n[LogonCheckInterceptor end ...............");
				return true;
			}
			request.getRequestDispatcher("/user002/logon.jsp").forward(request, responese);
			System.out.println("[�α��� ����...]");
			System.out.println("[LogonCheckInterceptor end .....]\n");
			return false;
			
		}
	}
}//e class
