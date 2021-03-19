package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.Controller;
import util.ScanUtil;
import util.View;
import dao.StationDao;
import dao.TicketDao;

public class TicketService {
	private TicketService() {}

	private static TicketService instance;

	public static TicketService getInstance() {
		if (instance == null) {
			instance = new TicketService();
		}
		return instance;
	}
	private TicketDao ticketDao = TicketDao.getInstance();

	public static Map<String, Object> param = new HashMap<>();

	public void train_type(){
		String train_type = null;
		System.out.println("====================[승차권 예매]====================");
		System.out.println(">>기차 종류 ");
		System.out.println("1.KTX\t2.무궁화\t3.새마을");
		System.out.println("==================================================");
		System.out.println("번호 입력>");
		int input = ScanUtil.nextInt();
		switch (input) {
		case 1:train_type = "KTX"; param.put("TICKET_TYPE", train_type); break;
		case 2:train_type = "무궁화"; param.put("TICKET_TYPE", train_type); break;
		case 3:train_type = "새마을"; param.put("TICKET_TYPE", train_type); break;
		}
		 Departstation();
	}
	
	public void Departstation() {
		String depart_station = null;
		System.out.println("====================[승차권 예매]====================");
		System.out.println(">>출발역 선택");
		System.out.println("1.서울\t2.광명\t3.천안\t4.오송\t5.대전");
		System.out.println("\t6.공주\t7.논산\t8.익산\t9.정읍");
		System.out.println("==================================================");
		System.out.println("번호 입력>");
		int input = ScanUtil.nextInt();
		switch (input) {
		case 1:depart_station = "서울"; param.put("DEPART_STATION", depart_station); break;
		case 2:depart_station = "광명"; param.put("DEPART_STATION", depart_station); break;
		case 3:depart_station = "천안"; param.put("DEPART_STATION", depart_station); break;
		case 4:depart_station = "오송"; param.put("DEPART_STATION", depart_station); break;
		case 5:depart_station = "대전"; param.put("DEPART_STATION", depart_station); break;
		case 6:depart_station = "공주"; param.put("DEPART_STATION", depart_station); break;
		case 7:depart_station = "논산"; param.put("DEPART_STATION", depart_station); break;
		case 8:depart_station = "익산"; param.put("DEPART_STATION", depart_station); break;
		case 9:depart_station = "정읍"; param.put("DEPART_STATION", depart_station); break;
		}
		DepartTime();
	}

	public void DepartTime() {
		List<Map<String, Object>> timeList = ticketDao.selectStationTime();
		System.out.println("================[기차 출발 시각]================");
		System.out.println("time_no\t출발 시간");
		for (Map<String, Object> time : timeList) {
			System.out.print(time.get("TIME_ID")+ " ");
			System.out.println(time.get("TIME_TRAIN"));
		}
		System.out.println("==========================================");
		System.out.println(">>출발 시간 입력");
		String d_t = ScanUtil.nextLine();
		param.put("DEPART_TIME", d_t);
		ArriveStation();
	}
	
	public void ArriveStation() {
		String arrive_station = null;
		System.out.println("====================[승차권 예매]====================");
		System.out.println(">>도착역 선택");
		System.out.println("1.서울\t2.광명\t3.천안\t4.오송\t5.대전");
		System.out.println("\t6.공주\t7.논산\t8.익산\t9.정읍");
		System.out.println("==================================================");
		System.out.println("번호 입력>");
		int input = ScanUtil.nextInt();
		switch (input) {
		case 1:arrive_station = "서울"; param.put("ARRIVE_STATION", arrive_station);break;
		case 2:arrive_station = "광명"; param.put("ARRIVE_STATION", arrive_station);break;
		case 3:arrive_station = "천안"; param.put("ARRIVE_STATION", arrive_station);break;
		case 4:arrive_station = "오송"; param.put("ARRIVE_STATION", arrive_station);break;
		case 5:arrive_station = "대전"; param.put("ARRIVE_STATION", arrive_station);break;
		case 6:arrive_station = "공주"; param.put("ARRIVE_STATION", arrive_station);break;
		case 7:arrive_station = "논산"; param.put("ARRIVE_STATION", arrive_station);break;
		case 8:arrive_station = "익산"; param.put("ARRIVE_STATION", arrive_station);break;
		case 9:arrive_station = "정읍"; param.put("ARRIVE_STATION", arrive_station);break;
		}
		TicketQty();
	}
	
	public void TicketQty() {
		int kid = 0; // 어린이
		int adult = 0; // 성인
		int old = 0; // 노인
		int amount = 0; // 총 가격

		System.out.println(">>승차권 종류 선택");
		System.out.println("=======[승차권 종류 및 가격]=======");
		System.out.println(">>아동 승차권 7900원");
		System.out.println(">>일반 승차권 8900원");
		System.out.println(">>노인 승차권 6900원");
		System.out.println("==============================");

		System.out.println("구매하지 않는 승차권에는 0을 입력해주시고, 구매하는 승차권에는 구매수량을 입력해주세요.");
		System.out.println("아동 승차권 갯수>");
		kid = ScanUtil.nextInt();
		param.put("TICKET_CHILD", kid);
		System.out.println("일반 승차권 갯수>");
		adult = ScanUtil.nextInt();
		param.put("TICKET_AGE", adult);
		System.out.println("노인 승차권 갯수>");
		old = ScanUtil.nextInt();
		param.put("TICKET_OLD", old);

		amount = (7900 * kid) + (8900 * adult) + (6900 * old);

		System.out.println("현재 총 결제 금액 : " + amount + "원");
		param.put("TICKET_PRICE", amount);
		//좌석 호출
		TicketSeat();
	}
	
	public void TicketSeat() {

		System.out.println("\ta\tb\tc\td");
		for (int i = 1; i < 29; i++) {
			System.out.print(i);
			for (int j = 2; j <= 5; j++) {
				if (j == 1) {
					 }if(j==2 && i==25 ) {
					
						 System.out.print("\t■");
				} else {
					System.out.print("\t□");
				}
			}
			System.out.println();
		}
		System.out.println("좌석 입력>");
		String seat = ScanUtil.nextLine();

		param.put("SEAT_ID", seat);

		int result = ticketDao.addTicket(param);

		if (0 < result) {
			System.out.println("기차 예매가 되었습니다.");
			Controller.mainmenu();
		} else {
			System.out.println("기차 예매에 오류가 발생했습니다. ");
			train_type();
		}
	}

}	
	

