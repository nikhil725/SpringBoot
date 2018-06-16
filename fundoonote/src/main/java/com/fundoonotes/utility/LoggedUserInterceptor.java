package com.fundoonotes.utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fundoonotes.userservice.User;
import com.fundoonotes.userservice.UserService;

public class LoggedUserInterceptor implements HandlerInterceptor  {
	@Autowired
	private UserService userService;

	
	private static final String OPTIONS = "OPTIONS";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)

			throws Exception {

		if (!request.getMethod().equals(OPTIONS)) {
			try {
				int userId = TokenUtils.verifyToken(request.getHeader("Authorization"));
				request.setAttribute("userId", userId);
				System.out.println("in inteceptor i am getting userId " + userId);

				User user = userService.getUserById(userId);
				if (user == null) {
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		System.out.println("After handling the request");

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		System.out.println("After rendering the view");

	}
}
