package controller;

import java.util.Map;

import service.AdminService;
import service.AnnounceService;
import service.UserService;
import util.ScanUtil;
import util.View;

//Controller 화면이동 / 메뉴
public class Controller {

	public static void main(String[] args) {

		new Controller().start();
	}
	//사용자가 로그인을 했으면, 로그인 접속 정보를 가지고 있어야 하기 때문에 생성.
	public static Map<String, Object> LoginUser;
	
	private UserService userService = UserService.getInstance(); 
	private AnnounceService announceService= AnnounceService.getInstance();
	private static AdminController adminController = AdminController.getInstance();
	private static UserController userController = UserController.getInstance();
	
	private void start() {
		int view = View.HOME;

		while (true) {
			switch (view) {
			case View.HOME: view = home(); break;
			case View.ANNOUNCE_LIST: view = announceService.announce(); break;
			case View.LOGIN: view = userService.loginTab(); break;
			case View.JOIN: view = userService.join(); break;
			case View.MAINHOME: view = mainmenu(); break;
			case View.ADMINHOME: view = adminhome(); break;
			}
		}

	}

	// 맨 처음 시작할 때 나오는 화면
	private int home() {
		System.out.println("---------------------------------");
		System.out.println("1.로그인\t2.공지사항\t0.프로그램 종료");
		System.out.println("---------------------------------");
		System.out.println("번호 입력>");
		int input = ScanUtil.nextInt();
		switch (input) {
		case 1:return View.LOGIN; // 로그인
		case 2:return View.ANNOUNCE_LIST; // 공지사항
		case 0:
			System.out.println("프로그램이 종료되었습니다.");
			System.exit(0);
			}
		return View.HOME;// 그 외의 번호 입력시 HOME리턴해서 다시 돌아오도록 함.
	}
	
	//사용자 메인 홈
	public static int mainmenu() {
		System.out.println("----------------------------------------------------------");
		System.out.println("1.승차권 예매\t2.기차 일정표\t3.마이페이지\t4.고객센터\t5.뒤로가기");
		System.out.println("----------------------------------------------------------");
		System.out.println("번호 입력>");
		
		int input = ScanUtil.nextInt();
		switch(input){
		case 1: userController.ticket(); break;
		case 2: userController.station(); break;
		case 3: userController.mypage(); break;
		case 4: userController.voc(); break;
		}
		return View.HOME;//그 외의 번호 입력시 MAINHOME리턴해서 다시 돌아오도록 함. 
	}

	//관리자 메인 홈
	public static int adminhome() {
		System.out.println("------------------------------------------------------------");
		System.out.println("1.관리자 관리\t2.역 관리\t3.기차 관리\t4.사용자관리\t5.뒤로가기");
		System.out.println("------------------------------------------------------------");
		System.out.println("번호 입력>");
		
		int input = ScanUtil.nextInt();
		switch(input){
		case 1: adminController.adiminmanage(); break;
		case 2: adminController.stationmanage(); break;
		case 3: adminController.trainmanage(); break;
		case 4: adminController.usermanage(); break;
		}
		return View.HOME; //그 외의 번호 ADMINHOME
	}
}//Controller 클래스
