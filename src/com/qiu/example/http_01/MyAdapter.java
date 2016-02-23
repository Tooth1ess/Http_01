package com.qiu.example.http_01;

import com.qiu.module.*;
import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qiu.adapter.utils.CommonAdapter;
import com.qiu.adapter.utils.ViewHolder;

public class MyAdapter extends CommonAdapter<Person> {
	private Handler mHandler = new Handler();

	public MyAdapter(Context context, List<Person> datas) {
		super(context, datas, R.layout.item);
	}

	public MyAdapter(Context context) {
		super(context, R.layout.item);
	}

	public void setDatas(List<Person> datas) {
		this.mDatas = datas;
	}

	@Override
	public void convert(ViewHolder holder, Person person) {
		List<SchoolInfo> schools = person.getSchoolInfo();
		SchoolInfo schoolInfo1 = schools.get(0);
		SchoolInfo schoolInfo2 = schools.get(1);
		holder.setText(R.id.name_textView, person.getName())
				.setText(R.id.age_textView, person.getAge() + "")
				.setText(R.id.school1_textView, schoolInfo1.getSchoolName())
				.setText(R.id.school2_textView, schoolInfo2.getSchoolName());
		new HttpImage(person.getUrl(), mHandler,
				holder.getView(R.id.imageView)).start();
	}

}
