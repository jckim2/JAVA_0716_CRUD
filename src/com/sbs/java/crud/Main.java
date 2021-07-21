package com.sbs.java.crud;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static ArrayList<Article> guest=new ArrayList<>(); //메인 메서드 밖으로 빼줘서 다른 메서드들도 접근 가능 static 이기에 다른 클래스도 가능

	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);

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
			} else if (command.equals("article write")) {

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

			} else if (command.equals("article list")) {

				if (guest.size() == 0) {
					System.out.println("게시물이 없습니다");
				} else {
					for (Article a : guest) {

						System.out.printf("%d번째 회원\n제목:%s\n조회수:%d\n", a.id, a.title, a.visit);

					}
				}
			} else if (command.startsWith("article detail")) {
				// equals 대신 startsWith도 있다 그 문장으로 시작하는 문장일때 라는 소리
				// System.out.printf("게시물 번호를 입력해주세요:");
				// int num = sc.nextInt();
				boolean found = false;
				Article foundArticle = null;
				String commandSplit[] = command.split(" ");
				int num;
				if (commandSplit.length > 2) {
					num = Integer.parseInt(commandSplit[2]); // 인트형으로 변환
				} else {
					System.out.println("잘못된 명령어 입니다");
					continue;
				}

				for (int i = 0; i < guest.size(); i++) {
					Article a = guest.get(i);

					if (a.id == num) {
						found = true;
						foundArticle = a;
						Util.visitRecord(foundArticle);
						break;
					}
				}

				if (found) {
					System.out.printf("번호:%d\n날짜:%s\n", num, foundArticle.time);
					System.out.printf("%d번째 회원\n제목:%s\n내용:%s\n조회수:%d\n", foundArticle.id, foundArticle.title,
							foundArticle.body, foundArticle.visit);
				} else if (num == 0) {
					System.out.println("1번째 부터 입력해주세요");
				} else {

					System.out.println("게시물이 없습니다");
				}

			} else if (command.equals("article delete")) {
				System.out.printf("게시물 번호를 입력해주세요:");
				int num = sc.nextInt();
				boolean found = false;
				Article foundArticle = null;
				// String[] commandsplit = command.split(" ");

				// int num = Integer.parseInt(commandsplit[2]);

				for (int i = 0; i < guest.size(); i++) {
					Article a = guest.get(i);
					if (a.id == num) {
						found = true;
						foundArticle = a;
						break;
					}
				}
				if (found) {
					for (int i = foundArticle.id; i < guest.size(); i++) {
						Article a = guest.get(i);
						a.id--;
					}
					guest.remove(foundArticle);
					// guest.remove(num-1);
					System.out.printf("%d번 게시물을 삭제하였습니다\n", num);
					Article.idCount--;

				} else if (found) {
					System.out.printf("%d번 게시물은 존재하지 않습니다\n", num);
				}

			} else if (command.startsWith("article modify")) {
				boolean found = false;
				Article foundArticle = null;
				String commandSplit[] = command.split(" ");
				int num = Integer.parseInt(commandSplit[2]); // 인트형으로 변환

				for (int i = 0; i < guest.size(); i++) {
					Article a = guest.get(i);

					if (a.id == num) {
						found = true;
						foundArticle = a;
						break;
					}
				}

				if (found) {
					System.out.printf("%d번째 게시글을 수정합니다\n", num);
					System.out.printf("수정할 제목:");
					foundArticle.title = sc.nextLine().trim();
					System.out.printf("수정할 내용:");
					foundArticle.body = sc.nextLine().trim();

					System.out.printf("수정된 제목:%s\n수정된 내용:%s\n", foundArticle.title, foundArticle.body);
				} else if (num == 0) {
					System.out.println("1번째 부터 입력해주세요");
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

class Article {
	static int idCount = 0, visitCount = 0;
	int id, visit;
	String body, title, time;

	public Article(int id, int visit, String body, String title, String time) {
		this.id = id;
		this.visit = visit;
		this.body = body;
		this.title = title;
		this.time = time;
	}

}