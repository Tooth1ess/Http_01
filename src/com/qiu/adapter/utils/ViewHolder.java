package com.qiu.adapter.utils;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {
	private SparseArray<View> mViews;
	private int mPositoin;
	private View mConvertView;

	public ViewHolder(Context context, ViewGroup parent, int layoutId,
			int position) {
		this.mPositoin = position;
		this.mViews = new SparseArray<View>();
		mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
				false);
		mConvertView.setTag(this);
	}

	/**
	 * 
	 * @Title: get
	 * @Description: 入口方法，来判断是否要创建ViewHolder.
	 * @param: context, convertView, parent, position, layoutId.
	 * @return: ViewHolder
	 * @throws
	 */
	public static ViewHolder get(Context context, View convertView,
			ViewGroup parent, int position, int layoutId) {
		if (convertView == null) {
			return new ViewHolder(context, parent, layoutId, position);
		} else {
			ViewHolder holder = (ViewHolder) convertView.getTag();
			holder.mPositoin = position;
			return holder;
		}

	}
	
	/**
	 * 
	* @Title: getView 
	* @Description: 通过viewId来获取控件
	* @param: viewId
	* @return: T    
	* @throws
	 */
	public <T extends View> T getView(int viewId) {
		View view = mViews.get(viewId);
		
		if (view == null) {
			view = mConvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		
		return (T) view;
	}

	public View getConvertView() {
		return mConvertView;
	}

	/**
	 * 
	* @Title: setText 
	* @Description: 设置TextView的值
	* @param: viewId， text
	* @return: ViewHolder    
	* @throws
	 */
	public ViewHolder setText(int viewId, String text) {
		TextView tv = getView(viewId);
		tv.setText(text);
		return this;		
	}
}
