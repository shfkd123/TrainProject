package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.Controller;
import dao.TrainDao;
import dao.UserDao;
import util.ScanUtil;
import util.View;

public class TrainService {
	// 싱글톤 패턴
	private TrainService() {
	}

	private static TrainService instance;

	public static TrainService getInstance() {
		if (instance == null) {
			instance = new TrainService();
		}
		return instance;
	}

	private TrainDao trainDao = TrainDao.getInstance();

	// 기차 추가
	public int train_Add() {
		System.out.println("==============기차 추가===============");
		System.out.println("기차 고유 번호>");
		String t_id = ScanUtil.nextLine();
		System.out.println("기차 종류>");
		String t_type = ScanUtil.nextLine();
		System.out.println("기차 상태>");
		String t_status = ScanUtil.nextLine();
		List<Object> trr = new ArrayList<Object>();
		trr.add(t_id);
		trr.add(t_type);
		trr.add(t_status);
		// Map<String, Object> param = new HashMap<>();
		// param.put("TRAIN_ID", t_id);
		// param.put("TRAIN_TYPE", t_type);
		// param.put("TRAIN_STATUS", t_status);

		int result = trainDao.addTrain(trr);
		if (0 < result) {
			System.out.println("기차 추가에 성공하였습니다.");
		} else {
			System.out.println("기차 추가에 실패하였습니다.");
		}

		return View.ADMINHOME;
	}

	// 기차 삭제
	public int train_Delete() {
		System.out.println("---------------------------------");
		System.out.println("기차 고유번호를 입력하세요>");
		String t_id = ScanUtil.nextLine();

		List<Object> train = new ArrayList<Object>();

		train.add(t_id);

		int result = trainDao.deleteTrain(train);
		if (0 < result) {
			System.out.println("기차 삭제가 완료되었습니다.");
		} else {
			System.out.println("기차 삭제가 실패하였습니다.");
		}
		return View.ADMINHOME;
	}

	// 기차 목록 및 현재 상태
	public void train_Status() {
		List<Map<String, Object>> trainList = trainDao.selectTrainList();
		System.out.println("-------------------------------------------");
		System.out.println(">>기차 목록 및 상태 ");
		System.out.println("========================================");
		System.out.println("기차 고유 번호\t기차종류\t기차상태");
		System.out.println("========================================");
		for (Map<String, Object> train_rail : trainList) {

			System.out.println(train_rail.get("TRAIN_ID") + "\t"
					+ train_rail.get("TRAIN_TYPE") + "\t"
					+ train_rail.get("TRAIN_STATUS"));
		}
		System.out.println("-------------------------------------------");
	}

	// 기차 정보 변경
	public int train_Update() {
		List<Object> trainUpdate = new ArrayList<Object>();

		System.out.println("변경할 기차 고유 번호 입력>");
		String t_id = ScanUtil.nextLine();
		boolean b = true;
		while (b) {
			System.out
					.println("-----------------------------------------------------");
			System.out.println(">>" + t_id + " 기차 정보 변경");
			System.out.println("1.기차 종류\t2.기차 상태\t3.뒤로가기");
			System.out
					.println("-----------------------------------------------------");
			System.out.println("번호 입력>");
			int input = ScanUtil.nextInt();
			int result = 0;
			switch (input) {
			case 1:
				System.out.println(t_id + "기차 종류 변경> ex)KTX 무궁화 새마을");
				String t_type = ScanUtil.nextLine();
				trainUpdate.add(t_id); // 관리자id
				trainUpdate.add(t_type); // 입력된 비밀번호
				result = trainDao.trainUpdate(trainUpdate, 1);
				if (0 < result) {
					System.out.println("관리자 정보가 변경되었습니다.");
				} else {
					System.out.println("관리자 정보가 변경에 실패하였습니다.");
				}
				return View.ADMINHOME;
			case 2:
				System.out.println(t_id + "기차 상태 변경> ex)대기중 운행중 수리중");
				String t_status = ScanUtil.nextLine();
				trainUpdate.add(t_id); // 관리자id
				trainUpdate.add(t_status); // 관리자 이름
				result = trainDao.trainUpdate(trainUpdate, 2);
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

	public int train_Time() {
		// 기차 시간 출력
		List<Map<String, Object>> timeList = trainDao.selectTimeList();
		System.out.println("-------------------------------------------");
		System.out.println(">>기차 시간 목록");
		System.out.println("========================================");
		System.out.println("기차 시간 번호\t기차시간");
		System.out.println("========================================");
		for (Map<String, Object> train_time : timeList) {

			System.out.println(train_time.get("TIME_ID") + "\t"
					+ train_time.get("TIME_TRAIN"));
		}
		System.out.println("-------------------------------------------");
		boolean b = true;
		while (b) {
			System.out
					.println("-----------------------------------------------------");
			System.out.println("1.기차 시간 추가\t2.기차 시간 변경\t3.기차 시간 삭제\t4.뒤로가기");
			System.out
					.println("-----------------------------------------------------");
			System.out.println("번호 입력>");
			int input = ScanUtil.nextInt();
			int result = 0;
			switch (input) {
			case 1:
				// 기차 시간 추가
				System.out.println("==============기차 시간 추가===============");
				System.out.println("기차 시간 ID>");
				String time_id = ScanUtil.nextLine();
				System.out.println("기차 시간 입력>");
				String time_t = ScanUtil.nextLine();

				Map<String, Object> param = new HashMap<>();
				param.put("TIME_ID", time_id);
				param.put("TIME_STATION", time_t);

				result = trainDao.addTime(param);

				if (0 < result) {
					System.out.println("시간 추가에 성공하였습니다.");
				} else {
					System.out.println("시간 추가에 실패하였습니다.");
				}
				break;
			case 2:
				// 기차 시간 변경
				List<Object> timeUpdate = new ArrayList<Object>();

				System.out.println("변경할 기차시간 ID 입력>");
				String tt_id = ScanUtil.nextLine();
				System.out.println(tt_id + "변경할 기차시간 입력>");
				String tt_t = ScanUtil.nextLine();

				List<Object> tUpdate = new ArrayList<Object>();
				tUpdate.add("tt_id");
				tUpdate.add("tt_t");

				result = trainDao.updateTime(tUpdate);
				if (0 < result) {
					System.out.println("기차 시간 정보가 변경되었습니다.");
				} else {
					System.out.println("기차 시간 정보를 변경하지 못했습니다.");
				}
				break;
			case 3:
				// 기차 시간 삭제
				System.out.println("---------------------------------");
				System.out.println("기차 시간 ID를 입력하세요>");
				String t_id = ScanUtil.nextLine();

				List<Object> time = new ArrayList<Object>();

				time.add(t_id);

				result = trainDao.deleteTime(time);
				if (0 < result) {
					System.out.println("기차 시간 삭제가 완료되었습니다.");
				} else {
					System.out.println("기차 시간 삭제가 실패하였습니다.");
				}
				break;
			case 4:
				b = false;
				break;
			}
		}

		return View.ADMINHOME;

	}

	// 기차 일정표 출력
	public int Schedulehome() throws InterruptedException {
		System.out.println("----------------기차 일정 표------------------");
		System.out.println("1.KTX 시간표\t2.무궁화 시간표\t3.새마을 시간표\t4.뒤로가기");
		System.out.println("-------------------------------------------");
		System.out.println("원하는 시간표 목록>");

		int input = ScanUtil.nextInt();
		switch (input) {
		case 1:
			Schedulektx();
			break;
		case 2:
			Schedulemu();
			break;
		case 3:
			ScheduleSa();
			break;
		}
		return View.MAINHOME; // 그 외의 번호 ADMINHOME
	}

	public int Schedulektx() throws InterruptedException {

		String ImportantInfo[] = {
				"-------------------------열차 출발 안내-------------------------------",
				"  출발 시간      출발역         열차 종류       열차 번호                    지연",
				"   06:00      서울역            KTX      KT01          0 ",
				"   07:00      서울역            KTX      KT02          0 ",
				"   08:15      서울역            KTX      KT03          0 ",
				"   08:45      서울역            KTX      KT04          0 ",
				"   09:00      서울역            KTX      KT05          0 ",
				"   10:00      서울역            KTX      KT06          0 ",
				"   11:00      서울역            KTX      kt07          0 ",
				"   12:15      서울역            KTX      kt08          0 ",
				"   12:45      서울역            KTX      kt09          0 ",
				"   13:00      서울역            KTX      kt10          0 ",
				"   14:00      서울역            KTX      kt11          0 ",
				"   15:00      서울역            KTX      kt12          0 ",
				"   16:15      서울역            KTX      kt13          0 ",
				"   17:45      서울역            KTX      kt14          0 ",
				"   18:00      서울역            KTX      KT15          0 "

		};

		for (int i = 0; i < ImportantInfo.length; i++) {
			// 1.5 초 동안 일시 중지
			Thread.sleep(1000);
			// 메시지 인쇄
			System.out.println(ImportantInfo[i]);
		}

		return View.HOME; // 그 외의 번호 ADMINHOME
	}

	public int Schedulemu() throws InterruptedException {

		String ImportantInfo[] = {
				"-------------------------열차 출발 안내-------------------------------",
				"  출발 시간      출발역         열차 종류       열차 번호                    지연",
				"   06:00      서울역            무궁화           Mu01          0 ",
				"   07:00      서울역            무궁화           Mu02          0 ",
				"   08:15      서울역            무궁화           Mu03          0 ",
				"   08:45      서울역            무궁화           Mu04          0 ",
				"   09:00      서울역            무궁화           Mu06          0 ",
				"   10:00      서울역            무궁화           Mu07          0 ",
				"   11:00      서울역            무궁화           Mu08          0 ",
				"   12:15      서울역            무궁화           Mu09          0 ",
				"   12:45      서울역            무궁화           Mu10          0 ",
				"   13:00      서울역            무궁화           Mu11          0 ",
				"   14:00      서울역            무궁화           Mu12          0 ",
				"   15:00      서울역            무궁화           Mu13          0 ",
				"   16:15      서울역            무궁화           Mu14          0 ",
				"   17:45      서울역            무궁화           Mu15          0 ",
				"   18:00      서울역            무궁화           Mu16          0 "
		};

		for (int i = 0; i < ImportantInfo.length; i++) {
			// 1.5 초 동안 일시 중지
			Thread.sleep(1000);
			// 메시지 인쇄
			System.out.println(ImportantInfo[i]);
		}

		return View.HOME; // 그 외의 번호 ADMINHOME
	}

	public int ScheduleSa() throws InterruptedException {

		String ImportantInfo[] = {
				"-------------------------열차 출발 안내-------------------------------",
				"  출발 시간      출발역         열차 종류       열차 번호                    지연",
				"   06:00      서울역            새마을           SA01          0 ",
				"   07:00      서울역            새마을           SA02          0 ",
				"   08:15      서울역            새마을           SA03          0 ",
				"   08:45      서울역            새마을           SA04          0 ",
				"   09:00      서울역            새마을           SA05          0 ",
				"   10:00      서울역            새마을           SA06          0 ",
				"   11:00      서울역            새마을           SA07          0 ",
				"   12:15      서울역            새마을           SA08          0 ",
				"   12:45      서울역            새마을           SA09          0 ",
				"   13:00      서울역            새마을           SA10          0 ",
				"   14:00      서울역            새마을           SA11          0 ",
				"   15:00      서울역            새마을           SA12          0 ",
				"   16:15      서울역            새마을           SA13          0 ",
				"   17:45      서울역            새마을           SA14          0 ",
				"   18:00      서울역            새마을           SA15          0 "

		};

		for (int i = 0; i < ImportantInfo.length; i++) {
			// 1.5 초 동안 일시 중지
			Thread.sleep(1000);
			// 메시지 인쇄
			System.out.println(ImportantInfo[i]);
		}
		return View.HOME; // 그 외의 번호 ADMINHOME
	}

}
