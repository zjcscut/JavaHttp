package com.HttpClient;


import java.util.HashMap;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		Map<String, String> params = new HashMap<>();
		params.put("keyfrom", "zjcscut");
		params.put("key", "264057182");
		params.put("type", "data");
		params.put("doctype", "json");
		params.put("version", "1.1");
		params.put("q", "hello");
		String string = HttpXmlClient.post("http://fanyi.youdao.com/openapi.do", params);
		System.out.println(string);
		
		String string2=HttpXmlClient.get("http://www.baidu.com");
		System.out.println(string2);
	}
}