package com.sbs.java.crud.dto;

public class member {
	public int id;
	public String time, loginId, loginPw, name;

	public member(int id, String time, String loginId, String loginPw, String name) {
		this.id = id;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
		this.time = time;
	}
}