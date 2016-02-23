package com.qiu.example.http_01;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;

public class JsonActivity extends Activity {
	private ListView mListView;
	private MyAdapter mMyAdapter;
	private Handler mHandler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.json_activity);
		
		mListView = (ListView) findViewById(R.id.listView);
		
		mMyAdapter = new MyAdapter(this);
		
		String url = "http://192.168.56.1:8080/web/JsonServlet";
		new HttpJson(url , mListView, mMyAdapter, mHandler).start();
	}
}
