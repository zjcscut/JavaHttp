package com.Http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * 使用Http的get方法读取网络数据 使用有道云翻译的api测试:http://fanyi.youdao.com/openapi
 * 
 * @author admin
 *
 */
public class HttpTestGet {
	public static void main(String[] args) {
		ReceiveUrl r = new ReceiveUrl();
		Thread thread = new Thread(r);
		thread.start();
	}
}

class ReceiveUrl implements Runnable {

	@Override
	public void run() {
		try {

			URL url = new URL(
					"http://fanyi.youdao.com/openapi.do?keyfrom=zjcscut&key=264057182&type=data&doctype=json&version=1.1&q=hello");
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			// 如果有中文乱码，可以指定is读入时候的编码方式
			// InputStreamReader isr = new InputStreamReader(is,"UTF-8");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader bf = new BufferedReader(isr);

			String line;
			StringBuffer sb = new StringBuffer();
			while ((line = bf.readLine()) != null) {
				sb.append(line);
			}

			bf.close();
			isr.close();
			is.close();

			System.out.println(sb.toString());
		} catch (Exception e) {

		}
	}
}