package com.sbs.java.crud;

import java.util.ArrayList;
import java.util.Scanner;

import com.sbs.java.crud.controller.Controller;
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
			String commandSplit[] = command.split(" ");

			String controllerName = commandSplit[0];
			String actionWord = commandSplit[1];

			Controller controller = null;
			if (commandSplit.length == 1) {
				System.out.println("잘못된 입력입니다");
			}

			if (controllerName.equals("article")) {
				controller = articleController;
			} else if (controllerName.equals("member")) {
				controller = memberController;
			} else {
				System.out.println("존재하지 않는 명령어 입니다");
				continue;
			}

			controller.doAction(command, actionWord);
		}
		sc.close();

		System.out.println("프로그램을 끝내겠습니다");

	}

}
