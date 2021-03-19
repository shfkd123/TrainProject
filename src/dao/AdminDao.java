package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class AdminDao {
	// 싱글톤 패턴
	private AdminDao() {}

	private static AdminDao instance;

	public static AdminDao getInstance() {
		if (instance == null) {
			instance = new AdminDao();
		}
		return instance;
	}
	
	//jdbc 객체 전역변수로 생성
	private JDBCUtil jdbc = JDBCUtil.getInstance();
	
	//관리자 id 중복확인
	public Map<String, Object> correctAdmin(String ad_id) {
		String sql = "select ad_id from ad where ad_id = ?";
		List<Object> param = new ArrayList<>();
		param.add(ad_id);

		return jdbc.selectOne(sql, param);
	}
	
	//관리자 추가
	public int insertAdmin(Map<String, Object> param){
		String sql = " insert into AD(AD_ID,AD_PS,AD_NM) values(?,?,?)"; // 쿼리작성
		// 물음표의 값 담기 (들어가야할 값 순서데로)
		List<Object> p = new ArrayList<>();
		p.add(param.get("AD_ID")); // 관리자ID
		p.add(param.get("AD_PS")); // 관리자비밀번호
		p.add(param.get("AD_NM")); // 관리자명
		return jdbc.update(sql,p);
	}
	
	//관리자 삭제
	public int deleteAdmin(List<Object> param) {
		String sql = " DELETE FROM AD WHERE AD_ID='"+param.get(0)+"'";	
		return jdbc.update(sql);
	}
	

	//관리자 정보 변경
	public int updateAdmin(List<Object> param,int cs) {
		String sql = " update ad ";		
		if(cs==1){
			sql	+= " set ad_ps = '" + param.get(1) + "'";
		}else{
			sql	+= " set ad_nm = '" + param.get(1) + "'";
		}				
		sql += " where ad_id ='"+param.get(0)+"'";
		return jdbc.update(sql);
	}
	
	//회원가입된 전체 고객(사용자) 정보 확인 //sql 확인 수정 필요!!
	public List<Map<String, Object>> selectUserList(){
		//고객번호/고객이름/생년월일/전화번호/마일리지/계좌/티켓고유번호/결제번호 
		String sql = "select a.cs_id,a.cs_nm,a.cs_bir,a.cs_hp,a.cs_mileage,a.cs_account,c.ticket_id,b.pay_nm";
		       sql += " from customer a inner join pay b on(a.cs_id = b.cs_id)";
		       sql += "                 inner join ticket c on(b.ticket_id = c.ticket_id)";
		
		return jdbc.selectList(sql);
	}
	
	//관리자 조회 
	public List<Map<String, Object>> selectadminList(){
		String sql = "select * from ad";
		return jdbc.selectList(sql);
	}
	
	//<고객민원확인> 목록 조회 //sql 확인 수정 필요!! 
	public List<Map<String, Object>> selectVocList(){
		String sql = " select a.voc_no,b.cs_id,a.voc_type, a.vocboard_subject"
				+ " , a.vocboard_content, a.voc_date"
				+ " from voc a"
				+ " left outer join customer b"
				+ " on a.cs_id = b.cs_id";
		return jdbc.selectList(sql);
	}
}
