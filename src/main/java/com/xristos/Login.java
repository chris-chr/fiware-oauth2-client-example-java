package com.xristos;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest requestq, HttpServletResponse responseq) throws ServletException, IOException {
		
		//redirect url for the user to log in to fi-ware
		String url = "http://account.lab.fi-ware.org/oauth2/authorize"
					+ "?response_type=code"
					+ "&client_id="+ Domain.clientId
					+ "&state=xyz"
					+ "&redirect_uri=http%3A%2F%2F"+Domain.d+"%3A"+Domain.p+"%2Ffiware-oauth2-client-example-java%2Fcallback";
		
		//actual redirect
		responseq.sendRedirect(url);
	}
}
