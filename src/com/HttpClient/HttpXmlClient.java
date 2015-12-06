package com.HttpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * httpclient post or get mothod util
 * 
 * @author zjc
 * @date 2015-11-01/19:17:57
 */
public class HttpXmlClient {
	// logs setting
	// private static Logger log=Logger.getLogger(this.getClass());

    /**
     * post method
     * @param url
     * @param params
     * @return
     */
	public static String post(String url, Map<String, String> params) {
		HttpClient httpclient = HttpClients.createDefault();
		String body;
		// log.info("create httppost:" + url);
		HttpPost post = postForm(url, params);
		body = invokePost(httpclient, post);
		return body;
	}

    /**
     * get method
     * @param url
     * @return
     */
	public static String get(String url) {
		HttpClient httpclient = HttpClients.createDefault();
		String body;
		// log.info("create httppost:" + url);
		HttpGet get = new HttpGet(url);
		body = invokeGet(httpclient, get);

		return body;
	}

	private static String invokeGet(HttpClient httpclient, HttpGet httpget) {
		HttpResponse response;
		String body = null;
		try {
			response = httpclient.execute(httpget);
			body = paseResponse(response);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return body;
	}

	private static String invokePost(HttpClient httpclient, HttpPost httpost) {
		HttpResponse response;
		String body = null;
		try {
			response = sendRequest(httpclient, httpost);
			body = paseResponse(response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return body;
	}

	private static String paseResponse(HttpResponse response) throws ParseException {
		// log.info("get response from http server..");
		HttpEntity entity = response.getEntity();
		String body = null;
		try {
			body = EntityUtils.toString(entity);
			// log.info(body);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return body;

	}

	private static HttpResponse sendRequest(HttpClient httpclient, HttpPost httpost) {
		// log.info("execute post...");
		HttpResponse response = null;
		try {
			response = httpclient.execute(httpost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;

	}

	private static HttpPost postForm(String url, Map<String, String> params) {
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nvps = new ArrayList<>();
		Set<String> keySet = params.keySet();
		for (String key : keySet) {
			nvps.add(new BasicNameValuePair(key, params.get(key)));
		}
		try {
			// log.info("set utf-8 form entity to httppost");
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return httpPost;
	}
	
}
