package com.qiu.module;

import java.util.Date;

public class GoodsInfo {
	private int mGoodsId;
	private int mClassId;
	private String mGoodsIntroduce;
	private String mGoodsName;
	private String mClassName;
	private float mPrice;
	private Date mAddDate;

	public int getGoodsId() {
		return mGoodsId;
	}

	public void setGoodsId(int goodsId) {
		mGoodsId = goodsId;
	}

	public int getClassId() {
		return mClassId;
	}

	public void setClassId(int classId) {
		mClassId = classId;
	}

	public String getGoodsIntroduce() {
		return mGoodsIntroduce;
	}

	public void setGoodsIntroduce(String goodsIntroduce) {
		mGoodsIntroduce = goodsIntroduce;
	}

	public String getGoodsName() {
		return mGoodsName;
	}

	public void setGoodsName(String goodsName) {
		mGoodsName = goodsName;
	}

	public String getClassName() {
		return mClassName;
	}

	public void setClassName(String className) {
		mClassName = className;
	}

	public float getPrice() {
		return mPrice;
	}

	public void setPrice(float price) {
		mPrice = price;
	}

	public Date getAddDate() {
		return mAddDate;
	}

	public void setAddDate(Date addDate) {
		mAddDate = addDate;
	}

}
