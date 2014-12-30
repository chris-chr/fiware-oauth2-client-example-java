package com.xristos;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionFilter implements Filter {

	public void destroy() {}
	public void init(FilterConfig arg0) throws ServletException {}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		//filter to the /user path to catch the non-logged in 
		//users and redirects them to the index page so they can login
		HttpServletRequest r = (HttpServletRequest)request;
		if(r.getSession(false).getAttribute("access_token") == null){
			HttpServletResponse resp = (HttpServletResponse)response;
			resp.sendRedirect("index.jsp");
			return;
		}
		chain.doFilter(request, response);
	}
}
