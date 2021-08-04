package com.sbs.java.crud.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.sbs.java.crud.App;
import com.sbs.java.crud.dto.member;
import com.sbs.java.crud.util.Util;

public class memberController extends Controller {
	int numId = 0, numPw = 0;
	int presentId = 0;
	private Scanner sc = new Scanner(System.in);
	public static ArrayList<member> members = new ArrayList<>();
	private String command;

	public void doAction(String command, String actionWord) {
		this.command = command;
		switch (actionWord) {
		case "join":
			dojoin();
			break;
		case "login":
			doLogin();
			break;
		case "whoami":
			showWhoami();
			break;
		case "logout":
			doLogout();
			break;

		}

	}

	private void dojoin() {
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
				System.out.println("비밀번호를 다시 입력해주세요");
			}
		}
		System.out.printf("이름:");
		String name = sc.nextLine();

		String time = Util.getNowDateStr();

		members.add(new member(id, time, loginId, loginPw, name));
		System.out.printf("%d번 회원이 생성 되었습니다\n", id);
	}

	private void doLogin() {
		if (presentId == 0) {
			while (true) {

				numId = 0;
				numPw = 0;
				String loginId, loginPw;

				System.out.printf("아이디:");
				loginId = sc.nextLine();
				System.out.printf("비밀번호:");
				loginPw = sc.nextLine();
				for (member findIndex : members) {
					if (findIndex.loginId.equals(loginId)) {
						numId = findIndex.numId;
					}

				}
				for (member findIndex : members) {

					if (findIndex.loginPw.equals(loginPw)) {
						numPw = findIndex.numId;
						if (numPw == numId) {
							break;
						} else {
							continue;
						}

					}
				}

				if (numId == numPw && numId != 0 && numPw != 0) {
					presentId = numId;
					member findMember = findMembers(presentId);
					System.out.printf("%s님 환영합니다\n", findMember.name);
					break;
				} else if (numId == 0 || numPw == 0) {
					System.out.println("아이디 또는 비밀번호를 잘못 입력하셨습니다");
					continue;
				} else {
					System.out.println("잘못된 입력입니다");
					continue;
				}
			}
		}else if(presentId!=0) {
			System.out.println("로그아웃을 해주세요");
		}
	}

	private void doLogout() {
		if (presentId != 0) {
			presentId = 0;
			System.out.println("로그아웃 되었습니다");
		} else if (presentId == 0) {
			System.out.println("현재 가입된 계정이 없거나 로그아웃한 상태입니다");
		}
	}

	private void showWhoami() {
		member findMember = findMembers(presentId);

		if (findMember == null) {
			System.out.println("현재 가입된 계정이 없거나 로그아웃한 상태입니다");
		} else {
			System.out.printf("로그인 ID:%s\n이름:%s\n", findMember.loginId, findMember.name);
		}
	}

	private member findMembers(int presentId) {// 지금 로그인된 사람의 정보를 가져올 수 있는 함수이다
		member findMember = null;
		for (member findId : members) {
			if (findId.numId == presentId) {
				findMember = findId;
			}
		}
		return findMember;
	}

	public void makeTestMember() {

		for (int i = 1; i <= 3; i++) {
			String time = Util.getNowDateStr();
			members.add(new member(i, time, "test" + i, "1234", "테스트용" + i));
		}
		System.out.println("테스트용 회원이 3개 생성되었습니다.");

	}
}
