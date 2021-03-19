package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.Controller;
import dao.MypageDao;
import dao.UserDao;
import util.ScanUtil;
import util.View;

public class MypageService {
	private MypageService() {
	}

	private static MypageService instance;

	public static MypageService getInstance() {
		if (instance == null) {
			instance = new MypageService();
		}
		return instance;
	}

	private MypageDao mypageDao = MypageDao.getInstance();

	// 마이페이지 내정보 출력
	public int information() {

		List<Map<String, Object>> myList = mypageDao.SelectPayUserId();
		
		System.out.println("---------- 내 정보 확인 ----------");
		System.out.println("사용자 아이디 : "+ myList.get(0).get("CS_ID"));
		System.out.println("사용자 주소 : "+ myList.get(0).get("CS_ADD"));
		System.out.println("사용자 전화번호 : "+ myList.get(0).get("CS_HP"));
		System.out.println("사용자 마일리지 : "+ myList.get(0).get("CS_MILEAGE"));
		System.out.println("사용자 주소 : "+ myList.get(0).get("CS_ADD"));
		
		return View.MAINHOME;	
	}

	public int userticket() {
		
	List<Map<String, Object>> myList = mypageDao.SelectPayUserId();
		
		System.out.println("---------- 티켓 정보 확인 ----------");
		System.out.println("티켓 번호 : "+ myList.get(0).get("TICKET_TYPE"));
		System.out.println("회원 ID  : "+ myList.get(0).get("CS_ID"));
		System.out.println("회원 이름 : "+ myList.get(0).get("CS_NM"));
		System.out.println("출발역 : "+ myList.get(0).get("DEPART_STATION")+"출발 시간: "+ myList.get(0).get("DEPART_TIME"));
		System.out.println("도착역 : "+ myList.get(0).get("ARRIVE_STATION")+"출발 시간: "+ myList.get(0).get("ARRIVE_TIME"));
		System.out.println("좌석"+ myList.get(0).get("Seat_ID"));
		

		return View.MAINHOME;
	}

	public int userpay() {
		List<Map<String, Object>> myList = mypageDao.SelectPayUserId();
		List<Map<String, Object>> destatime = mypageDao.DepartstationTime();
			
		System.out.println("---------- 예매 이력 ----------");
		System.out.println("티켓 번호 : "+ myList.get(0).get("TICKET_TYPE"));
		System.out.println("회원 ID  : "+ myList.get(0).get("CS_ID"));
		System.out.println("회원 이름 : "+ myList.get(0).get("CS_NM"));
		System.out.println("출발역 : "+ myList.get(0).get("DEPART_STATION")+"출발 시간: "+ destatime.get(0).get("TIME_train"));
		System.out.println("도착역 : "+ myList.get(0).get("ARRIVE_STATION"));
		System.out.println("좌석"+ myList.get(0).get("Seat_ID"));
		
		
		return View.MAINHOME;
	}

}
