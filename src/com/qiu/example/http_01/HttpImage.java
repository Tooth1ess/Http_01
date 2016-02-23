package com.qiu.example.http_01;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class HttpImage extends Thread {
	private View mImageView;
	private String mUrl;
	private Handler mHandler;
	
	public HttpImage(String url, Handler handler, View imageView) {
		this.mImageView = imageView;
		this.mUrl = url;
		this.mHandler = handler;
	}
	
	@Override
	public void run() {
		try {
			URL httpUrl = new URL(mUrl);
			HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
			conn.setRequestMethod("GET");
			conn.setReadTimeout(5000);
			InputStream in = conn.getInputStream();
			final Bitmap bitmap = BitmapFactory.decodeStream(in);
			
			mHandler.post(new Runnable() {
				
				@Override
				public void run() {
					((ImageView) mImageView).setImageBitmap(bitmap);
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
}
