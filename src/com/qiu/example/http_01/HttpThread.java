package com.qiu.example.http_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.Handler;
import android.webkit.WebView;

public class HttpThread extends Thread {
	private String mUrl;
	private WebView mWebView;
	private Handler mHandler;

	public HttpThread(String url, WebView webView, Handler handler) {
		this.mUrl = url;
		this.mWebView = webView;
		this.mHandler = handler;
	}

	@Override
	public void run() {
		try {
			URL httpUrl = new URL(mUrl);
			try {
				HttpURLConnection conn = (HttpURLConnection) httpUrl
						.openConnection();
				conn.setReadTimeout(5000);
				conn.setRequestMethod("GET");
				final StringBuffer sb = new StringBuffer();
				String str;
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(conn.getInputStream()));
				
				while ((str = reader.readLine()) != null) {
					sb.append(str);
				}

				mHandler.post(new Runnable() {
					@Override
					public void run() {
						mWebView.loadData(sb.toString(),
								"text/html;charset=utf-8", null);
					}
				});

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
