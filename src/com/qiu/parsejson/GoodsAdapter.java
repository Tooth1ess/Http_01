package com.qiu.parsejson;

import java.util.List;

import android.content.Context;

import com.qiu.adapter.utils.CommonAdapter;
import com.qiu.adapter.utils.ViewHolder;
import com.qiu.example.http_01.R;
import com.qiu.module.GoodsInfo;

public class GoodsAdapter extends CommonAdapter<GoodsInfo> {

	public GoodsAdapter(Context context, List<GoodsInfo> datas, int layoutId) {
		super(context, datas, layoutId);
	}

	public GoodsAdapter(Context context, int layoutId) {
		super(context, layoutId);
	}

	public void setDatas(List<GoodsInfo> datas) {
		this.mDatas = datas;
	}

	@Override
	public void convert(ViewHolder holder, GoodsInfo goodsInfo) {
		holder.setText(R.id.goodsName_textView, goodsInfo.getGoodsName())
				.setText(R.id.goodsInc_textView, goodsInfo.getGoodsIntroduce())
				.setText(R.id.addDate_textView,goodsInfo.getAddDate().toString())
				.setText(R.id.goodsPrice_textView, goodsInfo.getPrice() + "");
	}

}
