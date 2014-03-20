package com.example.ce;

import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

public class Http_Post {
	String response = null;

	public String Response(String url, List<NameValuePair> nameValuePairs) {
		System.out.println("URL:" + url);
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		try {
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
			ResponseHandler<String> handler = new BasicResponseHandler();
			response = new String(client.execute(post, handler).getBytes(),
					HTTP.UTF_8);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
}
