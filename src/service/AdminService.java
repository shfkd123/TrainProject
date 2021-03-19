package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.AdminDao;
import util.ScanUtil;
import util.View;

public class AdminService {
	private AdminService() {}

	private static AdminService instance;

	public static AdminService getInstance() {
		if (instance == null) {
			instance = new AdminService();
		}
		return instance;
	}
	
	private AdminDao adminDao = AdminDao.getInstance();
	
	// 관리자 추가 메서드
	public int adminjoin() {
		System.out.println("=============관리자 추가=============");
		String ad_id = null;
		String ad_ps = null;
		String ad_nm = null;
		System.out.println("아이디>");
		ad_id = ScanUtil.nextLine();
		Map<String, Object> admin_id = adminDao.correctAdmin(ad_id);
		if(admin_id == null){
			System.out.println("사용가능한 관리자 id입니다.");
			System.out.println("비밀번호>");
			ad_ps = ScanUtil.nextLine();
			System.out.println("이름>");
			ad_nm = ScanUtil.nextLine();
		}else {
			System.out.println("이미 존재하는 관리자 id입니다.");
		System.out.println("아이디>");
		ad_id = ScanUtil.nextLine();
		System.out.println("비밀번호>");
		ad_ps = ScanUtil.nextLine();
		System.out.println("이름>");
		ad_nm = ScanUtil.nextLine();
		}
		Map<String, Object> param = new HashMap<>();
		param.put("AD_ID", ad_id);
		param.put("AD_PS", ad_ps);
		param.put("AD_NM", ad_nm);

		// 아이디, 비밀번호, 이름이 담긴 hashmap을 넣어준다.
		int result = adminDao.insertAdmin(param);

		// 몇개의 행이 들어갔는지, 확인
		if (0 < result) {
			System.out.println("관리자 추가 성공");
		} else {
			System.out.println("관리자 추가 실패");
		}
		return View.ADMINHOME;
	}
	
	// 관리자 삭제
	public int adminDelete() {
		System.out.println("---------------------------------");
		System.out.println("관리자 ID를 입력하세요>");
		String ad_id = ScanUtil.nextLine();

	
		List<Object> da = new ArrayList<Object>();

		da.add(ad_id);

		int result = adminDao.deleteAdmin(da);
		if (0 < result) {
			System.out.println("관리자 삭제가 완료되었습니다.");
		} else {
			System.out.println("관리자 삭제가 실패하였습니다.");
		}
		return View.ADMINHOME;
	}
	
	// 관리자 정보 변경
	public int adminUpdate() {
		List<Map<String, Object>> adList = adminDao.selectadminList();
		List<Object> adminUpdate = new ArrayList<Object>();
		
		System.out.println("==========================");
		System.out.println("관리자 id\t관리자 pw\t관리자명");
		System.out.println("==========================");
		for (Map<String, Object> add : adList) {
			System.out.println(add.get("AD_ID") + "\t" + add.get("AD_PS")
					+ "\t" + add.get("AD_NM") + "\t");
		}
		System.out.println("==========================");
		System.out.println("---------------------------------");
			
		System.out.println("변경할 아이디 입력>");
		String ad_id = ScanUtil.nextLine();
		boolean b = true;
		while (b) {
			System.out.println("-----------------------------------------------------");
			System.out.println(">>" + ad_id + "님의 정보 변경");
			System.out.println("1.비밀번호 변경\t2.이름 변경\t3.뒤로가기");
			System.out.println("-----------------------------------------------------");
			System.out.println("번호 입력>");
			int input = ScanUtil.nextInt();
			int result = 0;
			switch (input) {
			case 1:
				System.out.println(ad_id + "님의 변경 할 비밀번호 입력>");
				String ad_ps = ScanUtil.nextLine();
				adminUpdate.add(ad_id);	//관리자id
				adminUpdate.add(ad_ps); //입력된 비밀번호
				result = adminDao.updateAdmin(adminUpdate,1);
				if (0 < result) {
					System.out.println("관리자 정보가 변경되었습니다.");
				} else {
					System.out.println("관리자 정보가 변경에 실패하였습니다.");
				}
				return View.ADMINHOME;
			case 2:
				System.out.println(ad_id + "님의 변경 할 이름 입력>");
				String ad_nm = ScanUtil.nextLine();
				adminUpdate.add(ad_id);	//관리자id
				adminUpdate.add(ad_nm);	//관리자 이름
				result = adminDao.updateAdmin(adminUpdate,2);
				if (0 < result) {
					System.out.println("관리자 정보가 변경되었습니다.");
				} else {
					System.out.println("관리자 정보가 변경에 실패하였습니다.");
				}
				return View.ADMINHOME;
			case 3:
				b = false;
				break;
			}
		}
		return View.ADMINHOME;
	}

	//관리자 -> 사용자 정보 출력
	public int adminUserSelct(){
		List<Map<String, Object>> adList = adminDao.selectUserList();
		System.out.println("=================================================================================================");
		System.out.println("번호\t이름\t주소\t생년월일\t\t\t전화번호\t마일리지\t계좌번호\t\t티켓번호\t결제번호");
		System.out.println("=================================================================================================");
		for(Map<String, Object> ad_us : adList){
			System.out.println(ad_us.get("CS_ID")+ "\t"
					+ ad_us.get("CS_NM") + "\t"
					+ ad_us.get("CS_ADD") + "\t"
					+ ad_us.get("CS_BIR") + "\t"
					+ ad_us.get("CS_HP") + "\t"
					+ ad_us.get("CS_MILEAGE") + "\t"
					+ ad_us.get("CS_ACCOUNT")+ "\t"
					+ ad_us.get("TICKET_ID")+ "\t"
					+ ad_us.get("PAY_NM"));
		}
		System.out.println("=================================================================================================");
		return View.ADMINHOME;
	}	

	//고객 민원 확인 출력
	public int cf_voc() {
		//VOC 목록 출력
		List<Map<String, Object>>  admList = adminDao.selectVocList();

		System.out.println("====================================================");
		System.out.println("번호\t고객ID\t문의분류\t제목\t\t\t내용\t작성일");
		System.out.println("====================================================");
		for (Map<String, Object> ann : admList) {
			System.out.println(ann.get("VOC_NO") + "\t" 
					+ ann.get("CS_ID") + "\t" 
					+ ann.get("VOC_TYPE") + "\t"
					+ ann.get("VOCBOARD_SUBJECT") + "\t\t\t" 
					+ ann.get("VOCBOARD_CONTENT") + "\t\t\t" 
					+ ann.get("VOC_DATE"));
		}
		System.out.println("====================================================");

		return View.ADMINHOME;
	}
	
}
