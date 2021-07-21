package com.sbs.java.crud;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//현재 시간 리턴
public class Util {
	public static String getNowDateStr() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
		Date time = new Date();

		return format.format(time);

	}

	public static void visitRecord(Article a) {
		a.visit++;

	}

	public static void makeTestData() {
		System.out.println("테스트를 위한 데이터를 3개 생성합니다");
		String testTitle = "테스트용 제목";
		String testBody = "테스트용 내용";

		for (int i = 0; i < 3; i++) {
			String time = getNowDateStr();
			Article.idCount++;
			Main.guest.add(new Article(Article.idCount, Article.visitCount, testBody + (i + 1), testTitle + (i + 1), time));
		}

	}
}