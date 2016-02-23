package com.qiu.module;

public class Girl {
	private String mName;
	private int mAge;
	private String mSchool;

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		mName = name;
	}

	public int getAge() {
		return mAge;
	}

	public void setAge(int age) {
		mAge = age;
	}

	public String getSchoolName() {
		return mSchool;
	}

	public void setSchoolName(String school) {
		mSchool= school;
	}

	@Override
	public String toString() {
		return "Girl [mName=" + mName + ", mAge=" + mAge + ", mSchoolName="
				+ mSchool + "]";
	}

}
