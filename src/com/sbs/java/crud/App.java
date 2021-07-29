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

		memberController memberController = new memberController(sc, members);
		articleController articleController = new articleController();

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
			} else if (command.equals("article write")) {

				articleController.dowrite();
			} else if (command.startsWith("article list")) {
				articleController.doList(command);
			} else if (command.startsWith("article detail")) {
				articleController.doDetail(command);

			} else if (command.startsWith("article delete")) {
				articleController.doDelete(command);

			} else if (command.startsWith("article modify")) {
				articleController.doModify(command);

			} else {
				System.out.printf("%s는 존재하지 않는 명령어입니다\n", command);

			}

		}

		sc.close();

		System.out.println("프로그램을 끝내겠습니다");

	}

}
