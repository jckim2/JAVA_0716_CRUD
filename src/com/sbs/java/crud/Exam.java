package com.sbs.java.crud;

import java.text.SimpleDateFormat;
import java.util.Date;
//테스트용 exclude 했음
public class Exam {
	public static void main(String[] args) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
		Date time = new Date();
		
		String time1 = format.format(time);
		
		System.out.println(time);//format전
		System.out.println(time1);//SimpleDateFormat 후
	}
}
