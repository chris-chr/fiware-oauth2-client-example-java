package com.xristos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class Callback extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    private String code = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse responseq) throws ServletException, IOException {
		
		//makes the autorization header for the request
        String authorization ="Basic " +  new String(Base64.encodeBase64((Domain.clientId + ":" + Domain.clientSercet).getBytes()));
        
        //gets the code from the url
        code = req.getParameter("code");
        
        //makes the http request in order 
        //to get the access token by setting
        //the appropriate headers and payloads
        //and finally it executes it
        HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(Domain.getTokenUrl);
		post.addHeader("Authorization", authorization);
		post.addHeader("Content-Type","application/x-www-form-urlencoded");
		String payload = "grant_type=authorization_code&code="+code
				+"&redirect_uri=http%3A%2F%2F"+Domain.d+"%3A"+Domain.p+"%2Ffiware-oauth2-client-example-java%2Fcallback";
		StringEntity se;
		se = new StringEntity(payload);
		post.setEntity(se);
		HttpResponse response = client.execute(post);
		
		//reads to a string the json response,
		//parses it and gets the access token 
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		String access_token = null;
		try {
			access_token = (new JSONObject(result.toString())).getString("access_token");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		//creates a session and sets the access token
		//so the user servlet can make the request for 
		//the users' data
		req.getSession(false).setAttribute("access_token", access_token);
		
		//redirects to the user servlet
		responseq.sendRedirect("user");
	}
}
