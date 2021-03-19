package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class MypageDao {
	private MypageDao() {}

	private static MypageDao instance;

	public static MypageDao getInstance() {
		if (instance == null) {
			instance = new MypageDao();
		}
		return instance;
	}
	
	// jdbc 객체 전역변수로 생성
	private static JDBCUtil jdbc = JDBCUtil.getInstance();

	// 사용자 정보 빼 오기
	public List<Map<String, Object>> SelectPayUserId() {
		String sql = "SELECT *\r\n" + "FROM   CUSTOMER A INNER JOIN PAY B ON(A.CS_ID = B.CS_ID)\r\n"
				+ "                  INNER JOIN TICKET C ON(B.TICKET_ID = C.TICKET_ID)\r\n" + "WHERE  A.CS_ID = 'CS01'";
		return jdbc.selectList(sql);
	}

	public List<Map<String, Object>> DepartstationTime() {
		String sql = "SELECT a.TIME_train FROM TIME A INNER JOIN TICKET B ON(A.TIME_ID = B.DEPART_TIME)\\r\\n"
				+ "                                  INNER JOIN PAY C ON(B.TICKET_ID = C.TICKET_ID)\\r\\n"
				+ "                                  INNER JOIN CUSTOMER D ON(C.CS_ID = D.CS_ID)"
				+ "WHERE D.CS_ID = 'CS01';";
		return jdbc.selectList(sql);
	}

}
