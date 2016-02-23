package com.qiu.example.http_01;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends Activity {
	private EditText mNameText;
	private EditText mAgeText;
	private Button mRegisterBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_activity);
		setViews();
		mRegisterBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String url = "http://192.168.56.1:8080/web/MyServlet";
				url = url + "?name=" + mNameText.getText().toString() + "&age="
						+ mAgeText.getText().toString();
				new HttpThread2(url).start();
			}
		});
	}

	private void setViews() {
		mNameText = (EditText) findViewById(R.id.editText1);
		mAgeText = (EditText) findViewById(R.id.editText2);
		mRegisterBtn = (Button) findViewById(R.id.button1);
	}
}
