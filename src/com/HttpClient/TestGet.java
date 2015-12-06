package com.HttpClient;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 使用HttpClient进行Get方式通信
 * 读入的是整个目标页面html转化成字符串(文本)
 * @author admin
 *
 */
public class TestGet {
	public static void main(String[] args) {
		ReceiveByGet r = new ReceiveByGet();
		Thread thread = new Thread(r);
		thread.start();
	}
}

class ReceiveByGet implements Runnable {

	HttpClient client = HttpClients.createDefault();

	@Override
	public void run() {
		HttpGet get = new HttpGet("http://www.baidu.com");
		try {

			HttpResponse response = client.execute(get);
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