package com.sbs.java.crud;

import java.util.ArrayList;
import java.util.Scanner;

public class 게시판 {
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");
		Scanner sc = new Scanner(System.in);
		ArrayList<article> guest = new ArrayList<>();

		int id = 0;

		while (true) {
			System.out.printf("명령어: ");
			String command = sc.nextLine().trim();
			// trim()은 쓸데없는 공백을 없애줌
			// sc.next()는 공백전까지
			// sc.nextInt()는 정수
			if (command.equals("system exit")) {// command=="system exit"
				break;
			}

			if (command.length() == 0) {
				continue;
			}
			if (command.equals("article write")) {
				id++;
				String title;
				String body;

				System.out.printf("제목입력: ");
				title = sc.nextLine().trim();
				System.out.printf("내용입력: ");
				body = sc.nextLine().trim();
				guest.add(new article(id, body, title));

				System.out.printf("제목:%s\n", title);
				System.out.printf("내용:%s\n", body);
				System.out.printf("현재 id수:%d\n", id);
				continue;

			} else if (command.equals("article list")) {
				if (guest.size() == 0) {
					System.out.println("게시물이 없습니다");
				} else {
					for (article a : guest) {
						System.out.printf("%d번째 회원\n제목:%s\n내용:%s\n", a.id, a.title, a.body);
					}
				}
			} else {
				System.out.printf("%s는 존재하지 않는 명령어입니다\n", command);
				continue;
			}

		}

		sc.close();
		
		System.out.println("프로그램을 끝내겠습니다");

	}
}

class article {
	int id;
	String body;
	String title;

	public article(int id, String body, String title) {
		this.id = id;
		this.body = body;
		this.title = title;
	}

}