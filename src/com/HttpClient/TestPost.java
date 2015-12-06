package com.HttpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;



public class TestPost {

	public static void main(String[] args) {
		ReceiveByPost r = new ReceiveByPost();
		Thread thread = new Thread(r);
		thread.start();
	}

}


class ReceiveByPost implements Runnable {

	HttpClient client = HttpClients.createDefault();

	@Override
	public void run() {
		HttpPost post = new HttpPost("http://fanyi.youdao.com/openapi.do");
		try {

			//keyfrom=zjcscut&key=264057182&type=data&doctype=json&version=1.1&q=hello
			//提交请求参数
			
	
			List<BasicNameValuePair> parameters=new ArrayList<>();
			parameters.add(new BasicNameValuePair("keyfrom", "zjcscut"));
			parameters.add(new BasicNameValuePair("key", "264057182"));
			parameters.add(new BasicNameValuePair("type", "data"));
			parameters.add(new BasicNameValuePair("doctype", "json"));
			parameters.add(new BasicNameValuePair("version", "1.1"));
			parameters.add(new BasicNameValuePair("q", "hello"));
			//设置请求参数
			post.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));
			HttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity, "UTF-8");

			System.out.println(result);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}