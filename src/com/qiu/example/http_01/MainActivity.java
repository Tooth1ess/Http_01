package com.qiu.example.http_01;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;

public class MainActivity extends Activity {
	private ListView mListView;
	private Handler mHandler = new Handler();
	private MyAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mListView = (ListView) findViewById(R.id.listView);

		mAdapter = new MyAdapter(this);
		String url = "http://192.168.56.1:8080/web/JsonServlet";
		new HttpJson(url, mListView, mAdapter, mHandler).start();
		
	}
}
