package com.qiu.adapter.utils;

import java.util.List;

import com.qiu.adapter.utils.ViewHolder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class CommonAdapter<T> extends BaseAdapter {
	protected Context mContext;
	protected List<T> mDatas;	
	protected int mLayoutId;
	
	public CommonAdapter(Context context, List<T> datas, int layoutId) {
		this.mContext = context;
		this.mDatas = datas;
		this.mLayoutId = layoutId;
	}
	
	public CommonAdapter(Context context, int layoutId) {
		this.mContext =context;
		this.mLayoutId = layoutId;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDatas.size();
	}

	@Override
	public T getItem(int position) {
		// TODO Auto-generated method stub
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = new ViewHolder(mContext, parent,
				mLayoutId, position);
		
		convert(holder, getItem(position));

		return holder.getConvertView();
	}
 
	public abstract void convert(ViewHolder holder, T t);
}
