package com.sbs.java.crud.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sbs.java.crud.App;
import com.sbs.java.crud.dto.Article;

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
			App.guest.add(
					new Article(Article.idCount, Article.visitCount, testBody + (i + 1), testTitle + (i + 1), time));
		}

	}



	public static boolean foundData(String command) {
		// equals 대신 startsWith도 있다 그 문장으로 시작하는 문장일때 라는 소리
		// System.out.printf("게시물 번호를 입력해주세요:");
		// int num = sc.nextInt();
		boolean found = false;
		Article foundArticle = null;
		String commandSplit[] = command.split(" ");
		int num;
		if (commandSplit.length > 2) {
			num = Integer.parseInt(commandSplit[2]); // 인트형으로 변환

			for (int i = 0; i < App.guest.size(); i++) {
				Article a = App.guest.get(i);

				if (a.id == num) {
					found = true;
					foundArticle = a;
					visitRecord(foundArticle);
					break;
				}
			}
			App.foundArticle = foundArticle;
			
			
		} else {
			System.out.printf("동일한 넘버의 ");
		}
		return found;
	}
}
