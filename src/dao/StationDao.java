package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class StationDao {
	private StationDao() {}

	private static StationDao instance;

	public static StationDao getInstance() {
		if (instance == null) {
			instance = new StationDao();
		}
		return instance;

	}
	// jdbc 객체 전역변수로 생성
	private JDBCUtil jdbc = JDBCUtil.getInstance();

	// 역 추가
	public int addStation(List<Object> param) {
		String sql = " insert into STATION"
				+ " values('"+param.get(0)+"','"+param.get(1)+"',null,null,null)";
//		List<Object> p = new ArrayList<>();
//		p.add(param.get("ST_ID"));
//		p.add(param.get("ST_NM"));
//		p.add(param.get("ST_KTX"));
//		p.add(param.get("ST_MU"));
//		p.add(param.get("ST_SA"));
		return jdbc.update(sql);
	}

	// 역 삭제
	public int deleteStation(List<Object> param) {
		String sql = " DELETE FROM STATION WHERE ST_ID='" + param.get(0) + "'";
		return jdbc.update(sql);
	}

	// 역 정보 확인
	public List<Map<String, Object>> selectStaionList() {
		String sql = " select * from station ";
		return jdbc.selectList(sql);
	}

	// 역 정보 변경
	public int updateStation(List<Object> stUpdate, int us) {
		String sql = "update station ";
		if(us == 1) {
			sql += " set st_nm = '"+stUpdate.get(1)+"'";
		}else if(us == 2) {
			sql += " set st_ktx = '"+stUpdate.get(1)+"'";
		}else if(us == 3) {
			sql += " set st_mu = '"+stUpdate.get(1)+"'";
		}else {
			sql += " set st_sa = '"+stUpdate.get(1)+"'";
		}
			sql += " where st_id = '"+stUpdate.get(0)+"'";
//				+ "set st_id, st_nm, st_ktx, st_mu, st_sa"
//				+ ") where (?, ?, ?, ?, ?)";
//		List<Object> params = new ArrayList<Object>();
//		for (int i = 0; i < stUpdate.size(); i++) {
//			params.add(stUpdate.get(i));
//		}
//		return jdbc.update(sql, params);
		return jdbc.update(sql);
	}
	
}