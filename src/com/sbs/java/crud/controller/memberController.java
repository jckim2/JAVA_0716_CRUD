package com.sbs.java.crud.controller;

import java.util.ArrayList;
import java.util.Scanner;

import com.sbs.java.crud.App;
import com.sbs.java.crud.dto.member;
import com.sbs.java.crud.util.Util;

public class memberController {
	private Scanner sc;
	private ArrayList<member> members;

	public memberController(Scanner sc, ArrayList<member> member) {
		this.sc = sc;
		this.members = member;
	}

	public void dojoin() {
		String loginId = null;
		int id = App.members.size() + 1;
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

}
