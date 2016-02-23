package com.qiu.example.http_01;

import com.qiu.module.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Handler;
import android.widget.ListView;
import android.widget.Toast;

public class HttpJson extends Thread {
	private String mUrl;
	private Context mContext;
	private ListView mListView;
	private MyAdapter mAdapter;
	private Handler mHandler;
	
	public HttpJson(String url, ListView listView, MyAdapter adapter, Handler handler) {
		this.mUrl = url;
		this.mListView = listView;
		this.mAdapter = adapter;
		this.mHandler = handler;
	}
	
	@Override
	public void run() {
		URL httpUrl;
		try {
				httpUrl = new URL(mUrl);
				HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
				conn.setRequestMethod("GET");
				conn.setReadTimeout(5000);
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuffer sb = new StringBuffer();
				String str;
				
				while ((str = reader.readLine()) != null) {
					sb.append(str);
				}
				final List<Person> parsedDatas = parseJson(sb.toString());
				
				mHandler.post(new Runnable() {
					
					@Override
					public void run() {
						mAdapter.setDatas(parsedDatas);
						mListView.setAdapter(mAdapter);
					}
				});
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private List<Person> parseJson(String json) {
		try {
				JSONObject object = new JSONObject(json);
				List<Person> persons = new ArrayList<Person>();
				int result = object.getInt("result");
				
				if (result == 1) {
					JSONArray personData = object.getJSONArray("personData");
					
					for (int i = 0; i < personData.length(); i++) {
						
						Person personObject = new Person();
						persons.add(personObject);
						JSONObject person = personData.getJSONObject(i);
						
						String name = person.getString("name");
						int age = person.getInt("age");
						String url = person.getString("url");
						personObject.setName(name);
						personObject.setAge(age);
						personObject.setUrl(url);
						
						JSONArray schoolInfos = person.getJSONArray("schoolInfo");
						List<SchoolInfo> schInfos = new ArrayList<SchoolInfo>();
						personObject.setSchoolInfo(schInfos);
						for (int j = 0; j < schoolInfos.length(); j++) {
							JSONObject school = schoolInfos.getJSONObject(j);
							String schoolName = school.getString("schoolName");
							SchoolInfo info = new SchoolInfo();
							info.setSchoolName(schoolName);
							schInfos.add(info);
						}	
					}
					return persons;
				} else {
					Toast.makeText(mContext, "error", Toast.LENGTH_SHORT).show();
				}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
