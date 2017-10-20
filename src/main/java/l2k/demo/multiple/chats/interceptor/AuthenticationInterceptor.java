package l2k.demo.multiple.chats.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import l2k.demo.multiple.chats.domain.User;
import l2k.demo.multiple.chats.services.UserService;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
	
	@Autowired
	private UserService userService;
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		boolean isRegisteredUser = isRegisteredUser(request);
		
		if(!isRegisteredUser)
			response.sendRedirect("/");
		
		return isRegisteredUser;
    }
	
	private boolean isRegisteredUser(HttpServletRequest request) {
		User user = null;
		
		try {
			Cookie sessionId = WebUtils.getCookie(request, "TRIVIA_SESSION_COOKIE");
			user = userService.getUser(sessionId.getValue());
			
		} catch (Exception exception) {
			System.out.println("Encountered excetpion while trying to retrieve User");
		}
		
		return user != null;
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
		
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
}
