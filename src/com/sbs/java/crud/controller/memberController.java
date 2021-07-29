package com.sbs.java.crud.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.sbs.java.crud.App;
import com.sbs.java.crud.dto.member;
import com.sbs.java.crud.util.Util;

public class memberController extends Controller {
	private Scanner sc = new Scanner(System.in);
	public static ArrayList<member> members = new ArrayList<>();
	private String command;

	public void doAction(String command, String actionWord) {
		this.command = command;
		switch (actionWord) {
		case "join":
			dojoin();
			break;
		}

	}

	public void dojoin() {
		String loginId = null;
		int id = members.size() + 1;
		while (true) {
			System.out.printf("로그인 아이디:");
			loginId = sc.nextLine();
			boolean testId = true;
			for (member test : members) {
				if (loginId.equals(test.loginId)) {
					System.out.println("이미 아이디가 있습니다 다시 입력해주세요");
					testId = false;
					break;
				}
			}
			if (testId) {
				break;
			}
		}

		String loginPw = null;
		String loginPwConfirm = null;

		while (true) {
			System.out.printf("로그인 비밀번호:");
			loginPw = sc.nextLine();
			System.out.printf("비밀번호 확인:");
			loginPwConfirm = sc.nextLine();

			if (loginPw.equals(loginPwConfirm)) {
				break;
			} else {
				System.out.printf("비밀번호를 다시 입력해주세요");
			}
		}
		System.out.printf("이름:");
		String name = sc.nextLine();

		String time = Util.getNowDateStr();

		members.add(new member(id, time, loginId, loginPw, name));
		System.out.printf("%d번 회원이 생성 되었습니다\n", id);
	}

	public void login() {
		while (true) {
			int presentId = 0;
			if (presentId == 0) {
				int id = 0, pw = 0;
				String loginId;
				String loginPw;
				System.out.printf("아이디:");
				loginId = sc.nextLine();
				System.out.printf("비밀번호:");
				loginPw = sc.nextLine();
				for (member findIndex : members) {
					if (findIndex.loginId.equals(loginId)) {
						id = findIndex.id;
					}
					if (findIndex.loginPw.equals(loginPw)) {
						pw = findIndex.id;
					}
				}
				if (id == pw && id != 0 && pw != 0) {
					presentId = id;
					System.out.println("로그인 성공");
					break;
				} else if (id == 0 || pw == 0) {
					System.out.println("아이디 또는 비밀번호를 잘못 입력하셨습니다");
					continue;
				} else {
					System.out.println("잘못된 입력입니다");
					continue;
				}

			}
		}
	}
}
