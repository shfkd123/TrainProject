package controller;

import service.MypageService;
import service.TicketService;
import service.TrainService;
import service.UserService;
import util.ScanUtil;
import util.View;

public class UserController {
	private UserController() {}

	private static UserController instance;

	public static UserController getInstance() {
		if (instance == null) {
			instance = new UserController();
		}
		return instance;
	}
	private UserService userService = UserService.getInstance(); 
	private TicketService ticketService = TicketService.getInstance();
	private MypageService mypageService = MypageService.getInstance();
	private TrainService trainService = TrainService.getInstance();
	
	// 1.승차권 예매
	public int ticket() {
		boolean b = true;
			System.out.println(">>승차권 예매");	
			ticketService.train_type();
		return View.MAINHOME;// 그 외의 번호 입력시 MAINHOME리턴
	}

	// 2.기차 일정표
	public int station() {
		boolean b = true;
		while (b) {
			System.out.println("-----------------------------------------------------");
			System.out.println(">>기차 일정표");
			System.out.println("1.기차 일정 확인\t2.뒤로가기");
			System.out.println("-----------------------------------------------------");
			System.out.println("번호 입력>");

			int input = ScanUtil.nextInt();
			switch (input) {
			case 1: try {
					trainService.Schedulehome();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}break;
			case 2:
				b = false; // 현재 while문 빠져나가기
				Controller.mainmenu();
			}
		}
		return View.MAINHOME;// 그 외의 번호 입력시 MAINHOME리턴
	}

	// 3.마이 페이지
	public int mypage() {
		boolean b = true;
		while (b) {
			System.out.println("----------------------------------------------------------------");
			System.out.println(">>마이페이지");
			System.out.println("1.내 정보 확인\t2.승차권 확인\t3.뒤로가기");
			System.out.println("-------------------------------------------------------------");
			System.out.println("번호 입력>");

			int input = ScanUtil.nextInt();
			switch (input) {
			case 1:mypageService.information();break;
			case 2:mypageService.userticket();break;
			case 3:b = false; 
			Controller.mainmenu();
				
			}
		}
		return View.MAINHOME;// 그 외의 번호 입력시 MAINHOME리턴
	}

	// 4.고객 센터 
	public int voc() {
		boolean b = true;
		while (b) {
			System.out.println("-----------------------------------------------------");
			System.out.println(">>고객 센터");
			System.out.println("1.고객 문의(VOC)\t2..뒤로가기");
			System.out.println("-----------------------------------------------------");
			System.out.println("번호 입력>");

			int input = ScanUtil.nextInt();
			switch (input) {
			case 1:userService.voc();break;
			case 2:b = false;
			Controller.mainmenu();
			}
		}
		return View.MAINHOME;// 그 외의 번호 입력시 MAINHOME리턴
	}
	
	
	
}
