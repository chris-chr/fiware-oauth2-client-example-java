package com.xristos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest requestq, HttpServletResponse responseq) throws ServletException, IOException {
		
		//gets the access token from session
		String access_token = (String)requestq.getSession(false).getAttribute("access_token");
		
		//makes the request using the access 
		//token in order to get the user data
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet("http://account.lab.fi-ware.org/user?access_token=" + access_token);
		HttpResponse response = client.execute(request);
		
		//reads to a string the user's 
		//data from the response body
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		
		//sets the data to the request for the user jsp to print them 
		requestq.setAttribute("data", result.toString());

		//redirect to the user jsp
		getServletContext().getRequestDispatcher("/user.jsp").forward(requestq, responseq);
	}
}
