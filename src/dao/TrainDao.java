package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class TrainDao {
	// 싱글톤 패턴
	private TrainDao() {}

	private static TrainDao instance;

	public static TrainDao getInstance() {
		if (instance == null) {
			instance = new TrainDao();
		}
		return instance;

	}

	// jdbc 객체 전역변수로 생성
	private JDBCUtil jdbc = JDBCUtil.getInstance();

	//기차 추가
	public int addTrain(List<Object> param) {
		String sql = "insert into TRAIN(TRAIN_ID, TRAIN_TYPE, TRAIN_STATUS) "
				+ "values('"+param.get(0)+"','"+param.get(1)+"','"+param.get(2)+"')";
//		List<Object> p = new ArrayList<>();
//		p.add(param.get("TRAIN_ID"));
//		p.add(param.get("TRAIN_TYPE"));
//		p.add(param.get("TRAIN_STATUS"));
		return jdbc.update(sql);
	}
	
	//기차 삭제
	public int deleteTrain(List<Object> param) {
		String sql = " DELETE FROM TRAIN WHERE TRAIN_ID='"+param.get(0)+"'";
		return jdbc.update(sql);
	}

	// 기차 목록과 상태 표시
	public List<Map<String, Object>> selectTrainList() {
		String sql = " select * from train";
		return jdbc.selectList(sql);
	}

	// 기차 정보 변경
	public int trainUpdate(List<Object> param, int t) {
		String sql = " update train ";
		if (t == 1) {
			sql += " set train_type = '" + param.get(1) + "'";
		} else {
			sql += " set train_status = '" + param.get(1) + "'";
		}
		sql += " where train_id ='" + param.get(0) + "'";
		return jdbc.update(sql);
	}
//시간
	//기차 시간 목록 출력
	public List<Map<String, Object>> selectTimeList() {
		String sql = " select * from time";
		return jdbc.selectList(sql);
	}
	//기차 시간 추가
	public int addTime(Map<String, Object> param) {
		String sql = "insert into TIME(TIME_ID,TIME_TRAIN) vlaues(?,?)";
		List<Object> p = new ArrayList<>();
		p.add(param.get("TIME_ID"));
		p.add(param.get("TIME_TRAIN"));
		return jdbc.update(sql,p);
	}
	//기차 시간 삭제
	public int deleteTime(List<Object> param) {
		String sql = " DELETE FROM TIME WHERE TIME_ID='"+param.get(0)+"'";
		return jdbc.update(sql);
	}

	// 기차 시간 변경
	public int updateTime(List<Object> tUpdate) {
		String sql = "update time (" + "set time_id, time_train"
				+ ") where (?, ?)";

		return jdbc.update(sql);
	}


}
