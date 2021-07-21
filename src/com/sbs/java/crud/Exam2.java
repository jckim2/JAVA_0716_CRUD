package com.sbs.java.crud;

//공부용이라 Exclude 됐음
public class Exam2 {

	public static void main(String[] args) {
		new app().start();//start가 static이 아니기 때문에 객체를 만들어 주었다
		//static은 static끼리
		
	}


}

class app {
	private int a;// 인스턴트 변수(클래스안에 변수) 다른 메서드에서도 공유가능하고 static을 붙여주면 다른 클래스에서도 사용 가능

	app() {// 구조상 int a = 10이라고 하는 것보다 초기값은 이렇게 정하는 게 좋다
		a = 10;
	}

	public void start() {//외부에서 사용하는 것은 public
		makeTestData();
		System.out.println(a);
		
	}

	private void makeTestData() {//같은 인스턴트(클래스)안에서만 사용해서 외부에서는 사용 안되는 private
		a=5;
		
	}
	
}