package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class UserDao {
	// 싱글톤 패턴
	private UserDao() {}

	private static UserDao instance;

	public static UserDao getInstance() {
		if (instance == null) {
			instance = new UserDao();
		}
		return instance;
	}
	
	//jdbc 객체 전역변수로 생성
	private JDBCUtil jdbc = JDBCUtil.getInstance();
	
	
	//회원가입
	public int insertUser(Map<String, Object> param){
		String sql = "insert into CUSTOMER(CS_ID,CS_PASSWORD,CS_NM,CS_ADD,CS_BIR,CS_HP,CS_MILEAGE,CS_ACCOUNT) values(?,?,?,?,?,?,0,?)"; //쿼리 작성
		
		//물음표의 값 담기 (들어가야할 값 순서데로)
		List<Object> p  = new ArrayList<>();
		p.add(param.get("CS_ID")); //고객번호
		p.add(param.get("CS_PASSWORD")); //비밀번호
		p.add(param.get("CS_NM")); //고객명
		p.add(param.get("CS_ADD")); //주소
		p.add(param.get("CS_BIR")); //생년월일
		p.add(param.get("CS_HP")); // 전화번호
		p.add(param.get("CS_ACCOUNT")); //계좌번호
		
		return jdbc.update(sql,p);
		
	}         

	//사용자 로그인 성공
	public Map<String, Object> selectUser(String userId, String password) {
		String sql = "select * from vw_customer where cs_id = ? and cs_password = ?"; //사용자 아이디와 비밀번호가 모두 일치하는 회원을 찾는다.
		List<Object> param = new ArrayList<>();
		param.add(userId);
		param.add(password);
		
		return jdbc.selectOne(sql, param);
		
	}

	// ID 중복확인
	public Map<String, Object> correctUser(String userId) {
		String sql = "select cs_id from customer where cs_id = ?                                                        ";
		List<Object> param = new ArrayList<>();
		param.add(userId);

		return jdbc.selectOne(sql, param);
	}
	
	//관리자 로그인 성공
	public Map<String, Object> selectAdmin(String adminId, String password) {
		String sql = "select * from vw_ad where ad_id = ? and ad_ps = ?"; //관리자 아이디와 비밀번호가 모두 일치하는 회원을 찾는다.
		List<Object> param = new ArrayList<>();
		param.add(adminId);
		param.add(password);
		
		return jdbc.selectOne(sql, param);
		
	}
	
	//마이페이지 - 사용자 정보출력 
	public List<Map<String, Object>> selectmypageList(){
		String sql= " select cs_id, cs_password, cs_nm, cs_add, cs_bir cs_hp, cs_mileage, cs_account"
				+ " from customer ";
		return jdbc.selectList(sql);
	}
	
	//voc 고객센터 목록 조회
	public List<Map<String, Object>> selectVocList(){
		String sql= " select a.voc_no, b.cs_id, a.voc_type, a.vocboard_subject, a.vocboard_content, a.voc_date"
				+ " from voc a"
				+ " left outer join customer b"
				+ " on a.cs_id = b.cs_id"
				+ " order by a.voc_no desc";

		return jdbc.selectList(sql);
	}
	
	
	//VOC 문의 추가
	public int insertVoc(List<Object> param) {
		//voc_no, cs_id, voc_type, vocboard_subject, vocboard_content, voc_date 
		String sql = "insert into VOC(VOC_NO,CS_ID,VOC_TYPE,VOCBOARD_SUBJECT,VOCBOARD_CONTENT,VOC_DATE) "
				+ "values((select nvl(max(VOC_NO),0)+1 from VOC), '"+param.get(0)+"', '"+param.get(1)+"','"+param.get(2)+"','"+param.get(3)+"', SYSDATE)";
		return jdbc.update(sql);

	}

}
