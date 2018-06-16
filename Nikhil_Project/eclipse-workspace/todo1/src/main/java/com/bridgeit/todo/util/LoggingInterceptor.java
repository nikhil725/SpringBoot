package com.bridgeit.todo.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bridgeit.todo.user.model.User;
import com.bridgeit.todo.user.service.IUserService;

public class LoggingInterceptor implements HandlerInterceptor{

	@Autowired
	private IUserService userService;
	//private static final String OPTIONS = "OPTIONS";

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		if(!request.getMethod().equals(null)) {
			
			int id = Token.getId(request.getHeader("Authorization"));
			request.setAttribute("id", id);
			
			User user = userService.getUserById(id);
			
			if(user==null) {
				return false;
			}
		}
	
		return true;
		 	
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		System.out.println("After handling the request");
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		
	}

}
