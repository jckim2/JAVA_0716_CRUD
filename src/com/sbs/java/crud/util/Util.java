package com.sbs.java.crud.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.sbs.java.crud.App;
import com.sbs.java.crud.controller.articleController;
import com.sbs.java.crud.dto.Article;
import com.sbs.java.crud.dto.member;

//현재 시간 리턴

public class Util {
	static Scanner sc = new Scanner(System.in);

	public static String getNowDateStr() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
		Date time = new Date();

		return format.format(time);

	}

}