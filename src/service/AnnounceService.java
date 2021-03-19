package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controller.Controller;
import dao.AnnounceDao;
import util.ScanUtil;
import util.View;

public class AnnounceService {
	// 싱글톤 패턴
	private AnnounceService() {}

	private static AnnounceService instance;

	public static AnnounceService getInstance() {
		if (instance == null) {
			instance = new AnnounceService();
		}
		return instance;
	}
	
	private AnnounceDao announceDao = AnnounceDao.getInstance();	

	// 공지사항 목록 출력 메서드
	public int announce() {
		List<Map<String, Object>> annList = announceDao.selectAnnounceList();

		System.out.println("====================================================");
		System.out.println("번호\t이름\t제목\t내용\t작성일");
		System.out.println("====================================================");
		for (Map<String, Object> ann : annList) {
			System.out.println(ann.get("ANN_ID") + "\t"
					+ ann.get("ADMIN_NM") + "\t"
					+ ann.get("ANN_SUBJECT") + "\t"
					+ ann.get("ANN_CONTENT") + "\t"
					+ ann.get("ANN_DATE"));
		}
		System.out.println("====================================================");

		return View.HOME;
	};

	//공지사항 추가
	public int announceInsert() {
		//ann_id, ann_nm, ann_subject, ann_content, ann_date, ad_id  이 순서랑
		System.out.println("---------------------------------");
		System.out.println("제목 입력>");
		String inputSub = ScanUtil.nextLine();
		Map<String, Object> user = Controller.LoginUser;
		//System.out.println("user : " + user);
		System.out.println("내용입력>");
		String inputCon = ScanUtil.nextLine(); 
		List<Object> ann = new ArrayList<Object>();
//		ann.add("ann_id");
//		ann.add("ann_nm"); 
		ann.add(inputSub); //제목
		ann.add(inputCon); //내용
//		ann.add("ann_date");
		ann.add(user.get("AD_ID")); //관리자 이름
		
		int result = announceDao.insertAnnounce(ann);
		if (0 < result) {
			System.out.println("공지사항 작성이 완료되었습니다.");
		} else {
			System.out.println("공지사항 작성이 실패하였습니다.");
		}
		return View.ADMINHOME;
	}
	
	//공지사항 삭제
	public int announceDelete() {
		System.out.println("---------------------------------");
		System.out.println("공지 코드 입력>");
		String inputAnnId = ScanUtil.nextLine();
//		Map<String, Object> user = Controller.LoginUser;
//		System.out.println("공지 번호 입력>");
//		String inputAnnNm = ScanUtil.nextLine();

		List<Object> annDelete = new ArrayList<Object>();

		annDelete.add(inputAnnId);
//		annDelete.add(inputAnnNm); 
//		annDelete.add(user.get("AD_ID")); //관리자 이름
		
		
		int result = announceDao.deleteAnnounce(annDelete);
		if (0 < result) {
			System.out.println("공지사항 삭제가 완료되었습니다.");
		} else {
			System.out.println("공지사항 삭제가 실패하였습니다.");
		}
		return View.ADMINHOME;
	}
	
	//공지사항 수정
	public int announceUpdate() {
		List<Map<String, Object>> annList = announceDao.selectAnnounceList();
		List<Object> announceUpdate = new ArrayList<Object>();

		System.out.println("=================================");
		System.out.println("공지코드번호\t공지 제목\t공지 내용");
		System.out.println("=================================");
		for(Map<String, Object> add : annList) {
			System.out.println(add.get("ANN_ID") + "\t" + add.get("ANN_SUBJECT")
					+ "\t" + add.get("ANN_CONTENT") + "\t");
		}
		System.out.println("=================================");
		System.out.println("---------------------------------");

		System.out.println("변경할 공지 코드 입력>");
		String inputAnnId = ScanUtil.nextLine();
		boolean a = true;
		while (a) {
			System.out.println("---------------------------------");
			System.out.println(">>" + inputAnnId + "번 공지글 변경");
			System.out.println("1.공지 제목 변경\t2.공지 내용 변경\t3.뒤로가기");
			System.out.println("---------------------------------");
			System.out.println("번호 입력>");
			int input = ScanUtil.nextInt();
			int result = 0;
			switch (input) {
			case 1:
				System.out.println(inputAnnId + "번의 변경할 공지 제목 입력>");
				String ann_subject = ScanUtil.nextLine();
				announceUpdate.add(inputAnnId);
				announceUpdate.add(ann_subject);
				result = announceDao.updateAnnounce(announceUpdate, 1);
				if(0 < result) {
					System.out.println("공지 제목이 변경되었습니다.");
				}else {
					System.out.println("공지 제목 변경에 실패하였습니다.");
				}
				return View.ANNOUNCE_LIST;

			case 2:
				System.out.println(inputAnnId + "번의 변경할 공지 내용 입력>");
				String ann_content = ScanUtil.nextLine();
				announceUpdate.add(inputAnnId);
				announceUpdate.add(ann_content);
				result = announceDao.updateAnnounce(announceUpdate, 2);
				if(0 < result) {
					System.out.println("공지 내용이 변경되었습니다.");
				}else {
					System.out.println("공지 내용 변경에 실패하였습니다.");
				}
				return View.ANNOUNCE_LIST;
			case 3:
				a = false;
				break;
			}
		}
//		return View.ADMINHOME;
//	}
//		System.out.println("제목 입력>");
//		String inputSub = ScanUtil.nextLine();
//
//		System.out.println("내용입력>");
//		String inputCon = ScanUtil.nextLine();

//
////		annUpdate.add("ann_id");
////		annUpdate.add("ann_nm");
//		annUpdate.add(inputSub);
//		annUpdate.add(inputCon);
////		annUpdate.add("ann_date");
//		annUpdate.add(inputAnnId);
//		
//		int result = announceDao.updateAnnounce(annUpdate);
//		if (0 < result) {
//			System.out.println("공지사항 업데이트가 완료되었습니다.");
//		} else {
//			System.out.println("공지사항 업데이트가 실패하였습니다.");
//		}
		return View.ADMINHOME;
	}
}
	