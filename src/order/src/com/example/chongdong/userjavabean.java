package com.example.chongdong;

import cn.bmob.v3.BmobObject;


@SuppressWarnings("serial")
public class userjavabean extends BmobObject{
 
	private String mobilenum;
    private String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobilenum() {
		return mobilenum;
	}
	public void setMobilenum(String mobilenum) {
		this.mobilenum = mobilenum;
	}
	
}
