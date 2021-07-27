package com.sbs.java.crud;

import java.util.ArrayList;
import java.util.Scanner;

import com.sbs.java.crud.controller.articleController;
import com.sbs.java.crud.controller.memberController;
import com.sbs.java.crud.dto.Article;
import com.sbs.java.crud.dto.member;
import com.sbs.java.crud.util.Util;

public class App {// main일때는 static이였기 때문에 static과 관련된 것들은 static선언 이였지만 app으로 온뒤로는 static을 삭제
	public static Article foundArticle = null;
	public static ArrayList<Article> guest = new ArrayList<>(); // 메서드 밖으로 빼줘서 다른 메서드들도 접근 가능
	public static ArrayList<member> members = new ArrayList<>();

	public void start() {
		Scanner sc = new Scanner(System.in);
	
		memberController memberController =new memberController(sc,members);
		articleController articleController =new articleController();
	
		System.out.println("==프로그램 시작==");

		

		Util.makeTestData();

		while (true) {

			System.out.printf("명령어 입력:");
			String command = sc.nextLine().trim();
			
			// trim()은 쓸데없는 공백을 없애줌
			// sc.next()는 공백전까지
			// sc.nextInt()는 정수
			if (command.equals("system exit")) {// command=="system exit"
				break;
			}

			else if (command.length() == 0) {
				continue;
			} else if (command.equals("member join")) {
				memberController.dojoin();
			}
			else if (command.equals("article write")) {

				Article.idCount++;
				String title;
				String body;
				String timep = Util.getNowDateStr();// Util도 똑같은 Class이다 util안에 메서드는
				// static선언 되었기에 객체없이 바로 사용가능

				System.out.printf("제목입력: ");
				title = sc.nextLine().trim();
				System.out.printf("내용입력: ");
				body = sc.nextLine().trim();
				guest.add(new Article(Article.idCount, Article.visitCount, body, title, timep));

				System.out.printf("제목:%s\n", title);
				System.out.printf("내용:%s\n", body);
				System.out.printf("현재 id수:%d\n", Article.idCount);
				continue;

			} else if (command.startsWith("article list")) {
				String searchKeyword = command.substring("article list".length()).trim();
				ArrayList<Article> forListArticle = null;

				if (searchKeyword.length() > 0) {
					forListArticle = new ArrayList<>();

					for (Article articles : guest) {
						if (articles.title.contains(searchKeyword)) {
							forListArticle.add(articles);
						}
					}

					if (forListArticle.size() <= 0) {
						System.out.println("검색결과가 존재하지 않습니다");
						continue;
					}
				}

				if (guest.size() == 0) {
					System.out.println("게시물이 없습니다");
				} else {
					for (Article a : forListArticle) {

						System.out.printf("%d번째 회원\n제목:%s\n조회수:%d\n", a.id, a.title, a.visit);

					}
				}
			} else if (command.startsWith("article detail")) {
				boolean found = Util.foundData(command);
				if (found) {
					System.out.printf("번호:%d\n날짜:%s\n", foundArticle.id, foundArticle.time);
					System.out.printf("%d번째 회원\n제목:%s\n내용:%s\n조회수:%d\n", foundArticle.id, foundArticle.title,
							foundArticle.body, foundArticle.visit);
				} else {

					System.out.println("게시물이 없습니다");
				}

			} else if (command.startsWith("article delete")) {
//				System.out.printf("게시물 번호를 입력해주세요:");
//				int num = sc.nextInt();
//				boolean found = false;
//				Article foundArticle = null;

				boolean found = Util.foundData(command);
				if (found) {
					for (int i = foundArticle.id; i < guest.size(); i++) {
						Article a = guest.get(i);
						a.id--;
					}

					// guest.remove(num-1);
					System.out.printf("%d번 게시물을 삭제하였습니다\n", foundArticle.id);
					Article.idCount--;
					guest.remove(foundArticle);
				} else if (found) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", foundArticle.id);
				}

			} else if (command.startsWith("article modify")) {
				boolean found = Util.foundData(command);

				if (found) {
					System.out.printf("%d번째 게시글을 수정합니다\n", foundArticle.id);
					System.out.printf("수정할 제목:");
					foundArticle.title = sc.nextLine().trim();
					System.out.printf("수정할 내용:");
					foundArticle.body = sc.nextLine().trim();

					System.out.printf("수정된 제목:%s\n수정된 내용:%s\n", foundArticle.title, foundArticle.body);
				} else {
					System.out.println("게시물이 없습니다");
				}
			} else {
				System.out.printf("%s는 존재하지 않는 명령어입니다\n", command);

			}

		}

		sc.close();

		System.out.println("프로그램을 끝내겠습니다");

	}

}
