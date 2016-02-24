package com.qiu.parsexml;

import com.qiu.example.http_01.R;
import com.qiu.example.http_01.R.id;
import com.qiu.example.http_01.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class XmlPullActivity extends Activity {
	private TextView mXmlText;
	private Handler mHandler;
	private String mUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xmlpull_activity);
		
		mXmlText = (TextView) findViewById(R.id.textView);
		mHandler = new Handler();
		String mUrl = "http://192.168.56.1:8080/web/girls.xml";
		new XmlThread(mUrl, mHandler, mXmlText).start();
	}
	
	
}
