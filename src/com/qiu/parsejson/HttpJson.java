package com.qiu.parsejson;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.qiu.module.GoodsInfo;

public class HttpJson extends Thread{
	private String mUrl;
	@Override
	public void run() {
		try {
				URL httpUrl = new URL(mUrl);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<GoodsInfo> parseJson(String json) {
		try {
				JSONObject object = new JSONObject(json);
				List<GoodsInfo> goodsInfos = new ArrayList<GoodsInfo>();
				int resultCode = object.getInt("mResultCode");
				
				if (resultCode == 200) {
					JSONArray goodsData = object.getJSONArray("mGoodsData");
					
					for (int i = 0; i < goodsData.length(); i++) {
						GoodsInfo goodsObject = new GoodsInfo();
						goodsInfos.add(goodsObject);
						JSONObject goods = goodsData.getJSONObject(i);
						
						String name = goods.getString("mGoodsName");
						String intro = goods.getString("mGoodsIntroduce");
						String date = goods.getString("mAddDate");
						double price = goods.getDouble("mPrice");
						
						SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						
						goodsObject.setClassName(name);
						goodsObject.setGoodsIntroduce(intro);
						goodsObject.setAddDate(sim.parse(date));
						goodsObject.setPrice((float) price);
					}
					return goodsInfos;
				}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
