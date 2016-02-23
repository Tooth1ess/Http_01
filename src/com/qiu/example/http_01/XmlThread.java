package com.qiu.example.http_01;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.os.Handler;
import android.widget.TextView;

import com.qiu.module.Girl;

public class XmlThread extends Thread {
	private TextView mTextView;
	private Handler mHandler;
	private String mUrl;
	private InputStream in = null;
	
	public XmlThread (String url, Handler handler, TextView textView) {
		this.mUrl = url;
		this.mHandler = handler;
		this.mTextView = textView;
	}
	
	@Override
	public void run() {
		try {
			URL httpUrl = new URL(mUrl);
			HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
			conn.setRequestMethod("GET");
			conn.setReadTimeout(5000);
			in = conn.getInputStream();
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();//通过xml解析工厂获得实例
			XmlPullParser parser = factory.newPullParser();
			//为parser解析器对象提供流和编码格式
			parser.setInput(in, "UTF-8");
			//采用事件驱动来完成xml解析
			int eventType = parser.getEventType();//获得事件类型
			final List<Girl> list = new ArrayList<Girl>();
			Girl girl = null;
			
			while (eventType != XmlPullParser.END_DOCUMENT) {
				String data = parser.getName();
				switch (eventType) {
					case XmlPullParser.START_TAG: {
						if ("girl".equals(data)) {
							girl = new Girl();
						} 
						
						if ("name".equals(data)) {
							girl.setName(parser.nextText());
						}
						if ("age".equals(data)) {
							girl.setAge(Integer.parseInt(parser.nextText()));
						}
						if ("shcool".equals(data)) {
							girl.setSchoolName(parser.nextText());
						}
						break;
					}
					
					case XmlPullParser.END_TAG: {
						if ("girl".equals(data) && girl != null) {
							list.add(girl);
						}
						break;
					}
				}
				
				eventType = parser.next();//循环解析下一个元素
				
			}
			
			mHandler.post(new Runnable() {
				
				@Override
				public void run() {
					mTextView.setText(list.toString());
				}
			});
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
