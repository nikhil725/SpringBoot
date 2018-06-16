package com.bridgeit.todo.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class CorsFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
		System.out.println("In Cors Filter... Start");
		
		response.addHeader("Access-Control-Allow-Origin","*");
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		response.addHeader("Access-Control-Allow-Headers", "Authorization, Content-Type,Accept, X-Requested-With");// get token
		response.addHeader("Access-Control-Expose-Headers", "Authorization, Content-Type");
		response.addHeader("Access-Control-Max-Age", "600");
		
		response.setStatus(HttpServletResponse.SC_OK);
		
        System.out.println("from corse filter");
        
        chain.doFilter(request, response);
		
		
	}

}
