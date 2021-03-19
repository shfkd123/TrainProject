package service;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controller.Controller;
import dao.UserDao;
import util.ScanUtil;
import util.View;

public class UserService {
	
	//싱글톤 패턴
	private UserService(){}
	private static UserService instance;
	public static UserService getInstance(){
		if(instance == null){
			instance = new UserService();
		}
		return instance;
	}
	
	private UserDao userDao = UserDao.getInstance();
	
	public int loginTab() {
		int action =  0;
		boolean b = true;
		while(b){
			System.out.println("-------------------------------------------");
			System.out.println(">>로그인");
			System.out.println("1.로그인\t2.회원가입\t3.뒤로가기");
			System.out.println("-------------------------------------------");
			System.out.println("번호 입력>");
			
			int input = ScanUtil.nextInt();
			switch(input){
			case 1: login(); break;
			case 2: join(); break;
			case 3: b = false; //현재 while문 빠져나가기 
			}
		}
		return View.HOME;//그 외의 번호 입력시 HOME리턴
	}
	
	//회원가입 메서드
	public int join() {

		System.out.println("=============회원가입=============");

		// 영문 한자 이상 영문, 순자 혼합하여 3~10자
		System.out.println("[영문(한글자 이상)과 숫자를 혼합하여 3자 이상 10자 이내로 입력해주세요]");
		System.out.println("아이디>");
		String csId = ScanUtil.nextLine();

		String regexCsid = "^[a-zA-Z]{1}[a-zA-Z0-9_]{3,10}$";
		Pattern p1 = Pattern.compile(regexCsid);
		Matcher m1 = p1.matcher(csId);
		Map<String, Object> userId = userDao.correctUser(csId);
		if (m1.matches() == true || userId == null) {
			System.out.println("사용 가능한 ID입니다.");
		} else {
			System.out.println("형식에 맞지 않거나 중복된 ID입니다.");
			System.out.println("다시 입력해주세요");

			return join();
		}

		// 영문, 숫자 혼합하여 3~10자
		System.out.println("[영문 또는 숫자를 사용하여 3자 이상 10자 이내로 입력해주세요]");
		System.out.println("비밀번호>");
		String password = ScanUtil.nextLine();
		String regexPassword = "^[A-Za-z[0-9]]{3,10}$";
		Pattern p2 = Pattern.compile(regexPassword);
		Matcher m2 = p2.matcher(password);
		if (m2.matches() == true) {
			System.out.println("사용 가능한 비밀번호 입니다.");
		} else {
			System.out.println("형식에 맞지 않는 비밀번호입니다.");
			System.out.println("다시 입력해주세요.");

			return join();
		}

		// 한글로만 이루어진 이름
		System.out.println("[한글로만 이루어지도록 입력해주세요]");
		System.out.println("이름>");
		String csname = ScanUtil.nextLine();
		String regexCsname = "^[가-힣]*$";
		Pattern p3 = Pattern.compile(regexCsname);
		Matcher m3 = p3.matcher(csname);
		if (m3.matches() == true) {
			System.out.println();
		} else {
			System.out.println("형식에 맞지 않는 이름입니다.");
			System.out.println("다시 입력해주세요.");

			return join();
		}

		System.out.println("주소> ex.대전 서구");
		String csadd = ScanUtil.nextLine();

		System.out.println("[98-07-07 와 같은 형식으로 입력해주세요]");
		System.out.println("생년월일>");
		String csbir = ScanUtil.nextLine();
		String regexCsbir = "^(?:[0-9]{2}-(?:0[1-9]|1[0-2])-(?:0[1-9]|[1,2][0-9]|3[0,1]))";
		Pattern p4 = Pattern.compile(regexCsbir);
		Matcher m4 = p4.matcher(csbir);
		if (m4.matches() == true) {
			System.out.println();
		} else {
			System.out.println("형식에 맞지 않는 생년월일입니다.");
			System.out.println("다시 입력해주세요.");

			return join();
		}

		System.out.println("[010-1234-5678 와 같은 형식으로 입력해주세요]");
		System.out.println("HP>");
		String cshp = ScanUtil.nextLine();
		String regexCshp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$";
		Pattern p5 = Pattern.compile(regexCshp);
		Matcher m5 = p5.matcher(cshp);
		if (m5.matches() == true) {
			System.out.println();
		} else {
			System.out.println("형식에 맞지 않는 전화번호입니다.");
			System.out.println("다시 입력해주세요.");

			return join();
		}

		System.out.println("[1234(4자리 숫자) 와 같은 형식으로 입력해주세요]");
		System.out.println("PIN입력[결제시 사용]>");
		String userncard = ScanUtil.nextLine();
		String regexUserncard = "[0-9]{4}";
		Pattern p6 = Pattern.compile(regexUserncard);
		Matcher m6 = p6.matcher(userncard);
		if (m6.matches() == true) {
			System.out.println();
		} else {
			System.out.println("형식에 맞지 않는 PIN번호입니다.");
			System.out.println("다시 입력해주세요.");

			return join();
		}

		String csmileage = null;

		Map<String, Object> param = new HashMap<>();
		param.put("CS_ID", csId);
		param.put("CS_PASSWORD", password);
		param.put("CS_NM", csname);
		param.put("CS_ADD", csadd);
		param.put("CS_BIR", csbir);
		param.put("CS_HP", cshp);
		param.put("CS_MILEAGE", csmileage);
		param.put("CS_ACCOUNT", userncard);
		// 아이디, 비밀번호, 이름이 담긴 hashmap을 넣어준다.
		int result = userDao.insertUser(param);

		// 몇개의 행이 들어갔는지, 확인
		if (0 < result) {
			System.out.println("회원가입 성공");
		} else {
			System.out.println("회원가입 실패");
		}
		return View.MAINHOME;
	}


	public int login() {
		System.out.println("================로그인===============");
		System.out.println("아이디>");
		String userId = ScanUtil.nextLine();
		System.out.println("비밀번호>");
		String password = ScanUtil.nextLine();

		// db에 접속해서 아이디와 비밀번호가 일치하는 사람을 찾는다.
		Map<String, Object> user = userDao.selectUser(userId, password);
		Map<String, Object> admin = userDao.selectAdmin(userId, password);
		String gubun = "";
		//System.out.println("user : " + user + ", admin : " + admin); 
		
		if (user == null) {
			System.out.println("아이디 혹은 비밀번호를 잘못 입력하셨습니다.");
		} else{
			gubun = user.get("GUBUN").toString();
			//사용자인지 관리자인지 분기
			if(gubun.equals("CUSTOMER")){
				System.out.println(userId+"님 사용자 로그인 성공");
				Controller.LoginUser = user;
				System.out.println("사용자");
				
//				return View.MAINHOME; // 로그인 성공하면 사용자 메인화면 리턴
				return Controller.mainmenu();
			}
			else{
				Controller.LoginUser = admin;
				System.out.println("관리자");
				System.out.println(userId+"님 관리자 로그인 성공");
				
//				return View.ADMINHOME; //로그인 성공하면 관리자 메인화면 리턴 
				return Controller.adminhome();
			}
		} 
		return View.HOME; // 그외 아이디나 비밀번호를 잘못 입력한 경우 HOME 리턴
	}
	
	
	// 승차권 예매
	public int booking() {
		return View.MAINHOME;
	}

	// 마이페이지 내정보 출력
	public int mypage() {
		return View.MAINHOME;
	}

	// 고객문의
	public int voc() {
		//VOC 목록 출력
		List<Map<String, Object>> vocList = userDao.selectVocList();

		System.out.println("====================================================");
		System.out.println("번호\t고객ID\t문의분류\t제목\t내용\t작성일");
		System.out.println("====================================================");
		for (Map<String, Object> voc : vocList) {
			System.out.println(voc.get("VOC_NO") + "\t"
					+ voc.get("CS_ID") + "\t"
					+ voc.get("VOC_TYPE") + "\t"
					+ voc.get("VOCBOARD_SUBJECT") + "\t"
					+ voc.get("VOCBOARD_CONTENT") + "\t"
					+ voc.get("VOC_DATE"));
		}
		System.out.println("====================================================");
		//VOC 고객 문의 작성
		Map<String, Object> user = Controller.LoginUser;
		int voc_no = 1;
		System.out.println("문의 분류 작성>/신고/유실물/문의");
		String voc_type = ScanUtil.nextLine();
		System.out.println("제목>");
		String voc_subject = ScanUtil.nextLine();
		System.out.println("내용>");
		String voc_content = ScanUtil.nextLine();
	
		List<Object> voc = new ArrayList<Object>();
		voc.add(user.get("CS_ID"));//사용자 이름1
		voc.add(voc_type);
		voc.add(voc_subject);
		voc.add(voc_content);
	
		int result = userDao.insertVoc(voc);
		if (0 < result) {
			System.out.println("문의글 작성이 완료되었습니다.");
		} else {
			System.out.println("문의글 작성이 실패하였습니다.");
		}
		return View.MAINHOME;
	} 

}
