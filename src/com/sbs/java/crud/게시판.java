package com.sbs.java.crud;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
				SimpleDateFormat format = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
				Date time = new Date();
				id++;
				String title;
				String body;
				String timep = format.format(time);

				System.out.printf("제목입력: ");
				title = sc.nextLine().trim();
				System.out.printf("내용입력: ");
				body = sc.nextLine().trim();
				guest.add(new article(id, body, title, timep));

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
			}
			if (command.startsWith("article detail ")) {// 이걸로 시작하는 애들이 있으면

				boolean test = false;
				String commandSplit[] = command.split(" ");
				int num = Integer.parseInt(commandSplit[2]); // 인트형으로 변환

				for (int i = 0; i < guest.size(); i++) {
					article a = guest.get(i);

					if (a.id == num) {
						test = true;
					}
				}

				if (test == false) {
					System.out.println("게시물이 없습니다");
				} else if (num == 0) {
					System.out.println("1번째 부터 입력해주세요");
				} else {
					article ind = guest.get(num - 1);

					System.out.printf("번호:%d\n날24짜:%s\n", num, ind.time);
					System.out.printf("%d번째 회원\n제목:%s\n내용:%s\n", ind.id, ind.title, ind.body);
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
	String time;

	public article(int id, String body, String title, String time) {
		this.id = id;
		this.body = body;
		this.title = title;
		this.time = time;
	}

}