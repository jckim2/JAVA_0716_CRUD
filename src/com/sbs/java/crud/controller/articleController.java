package com.sbs.java.crud.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.sbs.java.crud.dto.Article;
import com.sbs.java.crud.util.Util;

public class articleController extends Controller {
	public static Article foundArticle = null;
	public static ArrayList<Article> guest = new ArrayList<>(); // 메서드 밖으로 빼줘서 다른 메서드들도 접근 가능
	Scanner sc = new Scanner(System.in);
	private String command;

	@Override
	public void doAction(String command, String actionWord) {
		this.command = command;
		switch (actionWord) {
		case "write":
			dowrite();
			break;
		case "list":
			doList();
			break;
		case "detail":

			doDetail();
			break;
		case "delete":
			doDelete();
			break;
		case "modify":
			doModify();
			break;
		}
	}

	private void dowrite() {
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

	}

	private void doList() {
		String[] commandSplit = command.split(" ");
		if (commandSplit.length == 3) {

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
					return;
				}
			}

			if (guest.size() == 0) {
				System.out.println("게시물이 없습니다");
			} else {
				for (Article a : forListArticle) {

					System.out.printf("%d번째 회원\n제목:%s\n조회수:%d\n", a.id, a.title, a.visit);

				}
			}
		} else {
			return;
		}
	}

	private void doDetail() {
		boolean found = foundData(command);
		if (found) {
			System.out.printf("번호:%d\n날짜:%s\n", foundArticle.id, foundArticle.time);
			System.out.printf("%d번째 회원\n제목:%s\n내용:%s\n조회수:%d\n", foundArticle.id, foundArticle.title, foundArticle.body,
					foundArticle.visit);
		} else {

			System.out.println("게시물이 없습니다");
		}

	}

	private void doDelete() {
//		System.out.printf("게시물 번호를 입력해주세요:");
//		int num = sc.nextInt();
//		boolean found = false;
//		Article foundArticle = null;

		boolean found = foundData(command);
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

	}

	private void doModify() {
		boolean found = foundData(command);

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

	}

	private boolean foundData(String command) {
		// equals 대신 startsWith도 있다 그 문장으로 시작하는 문장일때 라는 소리
		// System.out.printf("게시물 번호를 입력해주세요:");
		// int num = sc.nextInt();
		boolean found = false;
		Article foundArticle = null;
		String commandSplit[] = command.split(" ");
		int num;
		if (commandSplit.length > 2) {
			num = Integer.parseInt(commandSplit[2]); // 인트형으로 변환

			for (int i = 0; i < articleController.guest.size(); i++) {
				Article a = articleController.guest.get(i);

				if (a.id == num) {
					found = true;
					foundArticle = a;
					visitRecord(foundArticle);
					break;
				}
			}
			articleController.foundArticle = foundArticle;

		} else {
			System.out.printf("동일한 넘버의 ");
		}
		return found;
	}

	public void visitRecord(Article a) {
		a.visit++;

	}

	public void makeTestData() {
		System.out.println("테스트를 위한 데이터를 3개 생성합니다");
		String testTitle = "테스트용 제목";
		String testBody = "테스트용 내용";

		for (int i = 0; i < 3; i++) {
			String time = Util.getNowDateStr();
			Article.idCount++;
			articleController.guest.add(
					new Article(Article.idCount, Article.visitCount, testBody + (i + 1), testTitle + (i + 1), time));
		}

	}
}
