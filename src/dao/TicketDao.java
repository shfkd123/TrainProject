package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import service.TicketService;
import util.JDBCUtil;

public class TicketDao {
	private TicketDao() {}

	private static TicketDao instance;

	public static TicketDao getInstance() {
		if (instance == null) {
			instance = new TicketDao();
		}
		return instance;
	}
	// jdbc 객체 전역변수로 생성
	private JDBCUtil jdbc = JDBCUtil.getInstance();
	
	//기차 출발 시간 출력
	public List<Map<String, Object>> selectStationTime(){
		String sql = "SELECT TIME_ID";
			   sql += ", TO_CHAR(TO_DATE(TIME_TRAIN,'HH24:MI'),'HH24:MI') AS TIME_TRAIN";
			   sql += " FROM   TIME";
			   sql += " WHERE  TO_DATE(TIME_TRAIN,'HH24:MI') BETWEEN TO_DATE('06:00','HH24:MI') AND TO_DATE('12:00','HH24:MI')";
		return jdbc.selectList(sql);
	}
	
	//사용자 티켓 예매 저장(추가) 
		public int addTicket(Map<String, Object> param) {
			String sql =  "INSERT INTO TICKET(TICKET_ID, TRAIN_ID, TICKET_TYPE, DEPART_STATION, DEPART_TIME,";
				   sql += "ARRIVE_STATION, TICKET_CHILD, TICKET_AGE, TICKET_OLD, SEAT_ID,"; 
				   sql += "TICKET_PRICE";
			   	   sql += ") VALUES(";
			   	   sql += "FN_GET_TRAIN_ID(),(SELECT MIN(TRAIN_ID) FROM TRAIN WHERE TRAIN_TYPE=? GROUP BY TRAIN_TYPE) , ?, ?, ?,";
			   	   sql += "?, ?, ?, ?, ?,";
			   	   sql += "?)";
			List<Object> p = new ArrayList<>();
			p.add(param.get("TICKET_TYPE"));
			p.add(param.get("TICKET_TYPE"));
			p.add(param.get("DEPART_STATION"));
			p.add(param.get("DEPART_TIME"));
			p.add(param.get("ARRIVE_STATION"));
			p.add(param.get("TICKET_CHILD"));
			p.add(param.get("TICKET_AGE"));
			p.add(param.get("TICKET_OLD"));
			p.add(param.get("SEAT_ID"));
			p.add(param.get("TICKET_PRICE"));

			return jdbc.update(sql,p);
		}
}
