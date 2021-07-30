package com.sbs.java.crud.dto;

public class member {
	public int numId;
	public String time, loginId, loginPw, name;

	public member(int numId, String time, String loginId, String loginPw, String name) {
		this.numId = numId;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
		this.time = time;
	}
}