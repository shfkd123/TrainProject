package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class AnnounceDao {
	// 싱글톤 패턴
	private AnnounceDao() {}

	private static AnnounceDao instance;

	public static AnnounceDao getInstance() {
		if (instance == null) {
			instance = new AnnounceDao();
		}
		return instance;
	}
	
	private JDBCUtil jdbc = JDBCUtil.getInstance();
	
	//공지사항 목록 조회 메서드
	public List<Map<String, Object>> selectAnnounceList(){
		String sql= " select a.ann_id, b.ad_nm, a.ann_subject, a.ann_content, a.ann_date"
				+ " from announce a"
				+ " left outer join ad b"
				+ " on(a.ad_id = b.ad_id)"
				+ " order by a.ad_id desc";

		return jdbc.selectList(sql);
	}
	
	//insert, delete, update가 성공 1 반환, 실패 0반환 

	//공지사항 추가
	public int insertAnnounce(List<Object> param){
		String sql = "insert into announce(ANN_ID,ANN_SUBJECT,ANN_CONTENT,ANN_DATE,AD_ID) "
				+ "values((select nvl(max(ann_id),0)+1 from announce), '"+param.get(0)+"', '"+param.get(1)+"', SYSDATE, '"+param.get(2)+"')";

		return jdbc.update(sql);
	}

	//공지사항 삭제 delete
	public int deleteAnnounce(List<Object> param) {
		String sql = "delete from announce"
				+ " where ann_id = '"+param.get(0)+"'";
//		List<Object> params = new ArrayList<Object>();
//		for (int i = 0; i < param.size(); i++) {
//			params.add(param.get(i));
//		}
		return jdbc.update(sql);
		}
	
	//공지사항 수정 update   / ua => 1:  제목변경, 2:내용변경
	public int updateAnnounce(List<Object> param, int ua) {
		String sql = "update announce ";
		if(ua == 1) {	
			sql += " set ann_subject = '" + param.get(1)+"'";
		}else {
			sql += " set ann_content = '" + param.get(1)+"'";
		}
			sql += " where ann_id = '"+param.get(0)+"'";
//		List<Object> params = new ArrayList<Object>();
//		for (int i = 0; i < param.size(); i++) {
//			params.add(param.get(i));
//		}
		return jdbc.update(sql);
	}
}
