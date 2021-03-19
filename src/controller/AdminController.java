package controller;

import service.AdminService;
import service.AnnounceService;
import service.StaionService;
import service.TrainService;
import util.ScanUtil;
import util.View;

public class AdminController {
	private AdminController() {}

	private static AdminController instance;

	public static AdminController getInstance() {
		if (instance == null) {
			instance = new AdminController();
		}
		return instance;
	}
	private AnnounceService announceService= AnnounceService.getInstance();
	private AdminService adminService = AdminService.getInstance();
	private TrainService trainService = TrainService.getInstance();
	private StaionService stationService = StaionService.getInstance();

	// 1.관리자 관리
	public int adiminmanage() {
		boolean b = true;
		while (b) {
			System.out.println("-----------------------------------------------------");
			System.out.println(">>관리자 관리");
			System.out.println("1.관리자 추가\t2.관리자 삭제\t3.관리자 정보 변경\t4.뒤로가기");
			System.out.println("-----------------------------------------------------");
			System.out.println("번호 입력>");

			int input = ScanUtil.nextInt();
			switch (input) {
			case 1:adminService.adminjoin();break;
			case 2:adminService.adminDelete();break;
			case 3:adminService.adminUpdate();break; 
			case 4:
				b = false; // 현재 while문 빠져나가기\
				Controller.adminhome();
			}
		}
		return View.ADMINHOME;// 그 외의 번호 입력시 ADMINHOME리턴
	}


	// 2.역 관리
	public int stationmanage() {
		boolean b = true;
		while (b) {
			System.out.println("-----------------------------------------------------");
			System.out.println(">>역 관리");
			System.out.println("1.역 정보 추가\t2.역 정보 삭제\t3.역 정보 확인\t4.역 정보 변경\t5.뒤로가기");
			System.out.println("-----------------------------------------------------");
			System.out.println("번호 입력>");

			int input = ScanUtil.nextInt();
			switch (input) {
			case 1: stationService.station_Add();break;
			case 2: stationService.station_Delete();break;
			case 3: stationService.station_Print();break;
			case 4: stationService.station_Update();break;
			case 5:
				b = false; // 현재 while문 빠져나가기
				Controller.adminhome();
			}
		}
		return View.ADMINHOME;// 그 외의 번호 입력시 ADMINHOME리턴
	}

	// 3.기차 관리
	public int trainmanage() {
		boolean b = true;
		while (b) {
			System.out.println("-------------------------------------------------------------------------");
			System.out.println(">>기차 관리");
			System.out.println("1.기차 추가\t2.기차 삭제\t3.기차 목록 및 현재 상태 확인\t4.기차 정보 변경\t5.기차 시간관리\t6.뒤로가기");
			System.out.println("--------------------------------------------------------------------------");
			System.out.println("번호 입력>");

			int input = ScanUtil.nextInt();
			switch (input) {
			case 1:trainService.train_Add();break;
			case 2:trainService.train_Delete(); break;
			case 3:trainService.train_Status(); break;
			case 4:trainService.train_Update(); break;
			case 5:trainService.train_Time(); break;
			case 6:
				b = false; 
				Controller.adminhome();
			}
		}
		return View.ADMINHOME;// 그 외의 번호 입력시 ADMINHOME리턴
	}

	// 4.사용자 관리
	public int usermanage() {
		boolean b = true;
		while (b) {
			System.out.println("-----------------------------------------------------");
			System.out.println(">>사용자 관리");
			System.out.println("1.공지사항\t\t2.고객 정보 확인\t3.고객 민원 확인(VOC)및 관리\t4.뒤로가기");
			System.out.println("-----------------------------------------------------");
			System.out.println("번호 입력>");

			int input = ScanUtil.nextInt();
			switch (input) {
			case 1:
				while (b) {
					System.out.println("1.공지사항 추가 \t2.공지사항 삭제\t3.공지사항 수정\t4.뒤로가기");
					input = ScanUtil.nextInt();
					switch (input) {
					case 1:announceService.announceInsert();break;
					case 2:announceService.announceDelete();break;
					case 3:announceService.announceUpdate();break;
					case 4: b = false;
					usermanage();
					}
				}
				break;
			case 2:adminService.adminUserSelct();break;
			case 3:adminService.cf_voc();break;
			case 4:
				b = false; // 현재 while문 빠져나가기
				Controller.adminhome();
			}
		}
		return View.ADMINHOME;// 그 외의 번호 입력시 ADMINHOME리턴
	}
}
