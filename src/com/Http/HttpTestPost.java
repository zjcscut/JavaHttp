package com.Http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 使用 Http 的 Post 方式与网络交互通信
 * @author admin
 *
 */
public class HttpTestPost {

	 public static void main(String[] args) {
		 ReceiveByPost r=new ReceiveByPost();
		 Thread thread=new Thread(r);
		 thread.start();
	}
}


class ReceiveByPost implements Runnable{
	
	@Override
	public void run(){
		try {
			URL url=new URL("http://fanyi.youdao.com/openapi.do");
			HttpURLConnection connection=(HttpURLConnection) url.openConnection();
			//connection内容设置
			connection.addRequestProperty("encoding", "UTF-8");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			
			//向对方服务器发送请求参数
			OutputStream os=connection.getOutputStream();
			OutputStreamWriter osw= new OutputStreamWriter(os);
			BufferedWriter bw=new BufferedWriter(osw);
			
			//发送内容
			bw.write("keyfrom=zjcscut&key=264057182&type=data&doctype=json&version=1.1&q=hello");
			bw.flush();
			
			//读取内容
			InputStream is=connection.getInputStream();
			InputStreamReader isr= new InputStreamReader(is);
			BufferedReader br=new BufferedReader(isr);
			
			String line;
			StringBuilder sb=new StringBuilder();
			while((line=br.readLine())!=null)
				{sb.append(line);}
			
			bw.close();
			osw.close();
			os.close();
			
			br.close();
			isr.close();
			is.close();
			
			System.out.println(sb.toString());
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
}