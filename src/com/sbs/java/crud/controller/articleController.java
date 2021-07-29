package com.sbs.java.crud.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.sbs.java.crud.App;
import com.sbs.java.crud.dto.Article;
import com.sbs.java.crud.util.Util;

public class articleController {

	Scanner sc = new Scanner(System.in);

	public void dowrite() {
		Article.idCount++;
		String title;
		String body;
		String timep = Util.getNowDateStr();// Util도 똑같은 Class이다 util안에 메서드는
		// static선언 되었기에 객체없이 바로 사용가능

		System.out.printf("제목입력: ");
		title = sc.nextLine().trim();
		System.out.printf("내용입력: ");
		body = sc.nextLine().trim();
		App.guest.add(new Article(Article.idCount, Article.visitCount, body, title, timep));

		System.out.printf("제목:%s\n", title);
		System.out.printf("내용:%s\n", body);
		System.out.printf("현재 id수:%d\n", Article.idCount);

	}

	public void doList(String command) {
		String searchKeyword = command.substring("article list".length()).trim();
		ArrayList<Article> forListArticle = null;

		if (searchKeyword.length() > 0) {
			forListArticle = new ArrayList<>();

			for (Article articles : App.guest) {
				if (articles.title.contains(searchKeyword)) {
					forListArticle.add(articles);
				}
			}

			if (forListArticle.size() <= 0) {
				System.out.println("검색결과가 존재하지 않습니다");
				return;
			}
		}

		if (App.guest.size() == 0) {
			System.out.println("게시물이 없습니다");
		} else {
			for (Article a : forListArticle) {

				System.out.printf("%d번째 회원\n제목:%s\n조회수:%d\n", a.id, a.title, a.visit);

			}
		}
		
	}

	public void doDetail(String command) {
		boolean found = Util.foundData(command);
		if (found) {
			System.out.printf("번호:%d\n날짜:%s\n", App.foundArticle.id, App.foundArticle.time);
			System.out.printf("%d번째 회원\n제목:%s\n내용:%s\n조회수:%d\n", App.foundArticle.id, App.foundArticle.title,
					App.foundArticle.body, App.foundArticle.visit);
		} else {

			System.out.println("게시물이 없습니다");
		}
		
	}

	public void doDelete(String command) {
//		System.out.printf("게시물 번호를 입력해주세요:");
//		int num = sc.nextInt();
//		boolean found = false;
//		Article foundArticle = null;

		boolean found = Util.foundData(command);
		if (found) {
			for (int i = App.foundArticle.id; i < App.guest.size(); i++) {
				Article a = App.guest.get(i);
				a.id--;
			}

			// guest.remove(num-1);
			System.out.printf("%d번 게시물을 삭제하였습니다\n", App.foundArticle.id);
			Article.idCount--;
			App.guest.remove(App.foundArticle);
		} else if (found) {
			System.out.printf("%d번 게시물은 존재하지 않습니다\n", App.foundArticle.id);
		}
		
	}

	public void doModify(String command) {
		boolean found = Util.foundData(command);

		if (found) {
			System.out.printf("%d번째 게시글을 수정합니다\n", App.foundArticle.id);
			System.out.printf("수정할 제목:");
			App.foundArticle.title = sc.nextLine().trim();
			System.out.printf("수정할 내용:");
			App.foundArticle.body = sc.nextLine().trim();

			System.out.printf("수정된 제목:%s\n수정된 내용:%s\n", App.foundArticle.title, App.foundArticle.body);
		} else {
			System.out.println("게시물이 없습니다");
		}
		
	}

}
