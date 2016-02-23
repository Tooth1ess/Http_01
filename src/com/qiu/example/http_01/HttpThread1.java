package com.qiu.example.http_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Properties;

public class HttpThread1 extends Thread {
	String url;
	String name;
	String age;

	public HttpThread1(String url, String name, String age) {
		this.url = url;
		this.name = name;
		this.age = age;
	}

	private void doGet() {

		try {
			url = url + "?name=" + URLEncoder.encode(name, "utf-8") + "&age=" + age;
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// get方法只能通过url传参
		try {
			URL httpUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) httpUrl
					.openConnection();
			conn.setReadTimeout(5000);
			conn.setRequestMethod("GET");
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String str;
			StringBuffer sb = new StringBuffer();
			while ((str = reader.readLine()) != null) {
				sb.append(str);
			}

			System.out.println("result:" + sb.toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void doPost() {
		try {
			Properties property = System.getProperties();
			property.list(System.out);
			
			URL httpUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) httpUrl
					.openConnection();
			conn.setRequestMethod("POST");
			conn.setReadTimeout(5000);
			OutputStream out = conn.getOutputStream();
			String content = "name=" + name + "&age=" + age;
			out.write(content.getBytes());
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String str;

			while ((str = reader.readLine()) != null) {
				sb.append(str);
			}

			System.out.println(sb.toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		 //doGet();
		doPost();
	}
}
