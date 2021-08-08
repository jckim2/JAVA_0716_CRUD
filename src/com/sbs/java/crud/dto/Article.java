package com.sbs.java.crud.dto;

public class Article {
	public static int idCount = 0;
	public static int visitCount = 0;
	public int id;
	public int visit;
	public String body, title, time;
	public member writeMember =null;

	public Article(int id, int visit, String body, String title, String time,member writeMember) {
		this.id = id;
		this.visit = visit;
		this.body = body;
		this.title = title;
		this.time = time;
		this.writeMember = writeMember;
	}

}