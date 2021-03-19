package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.xpath.XPathConstants;

import util.ScanUtil;
import util.View;
import dao.StationDao;
import dao.TrainDao;

public class StaionService {
	private StaionService() {}

	private static StaionService instance;

	public static StaionService getInstance() {
		if (instance == null) {
			instance = new StaionService();
		}
		return instance;
	}
	
	private StationDao stationDao = StationDao.getInstance();
	
	// 역 추가
	public int station_Add() {
		System.out.println("==============역 추가===============");
		System.out.println("기차역 번호>");
		String st_id = ScanUtil.nextLine();
		System.out.println("기차역 명>");
		String st_nm = ScanUtil.nextLine();
//		System.out.println("KTX열차 - 다음역에 대한 소요시간>");
//		String st_ktx = ScanUtil.nextLine();
//		System.out.println("무궁화열차 - 다음역에 대한 소요시간>");
//		String st_mu = ScanUtil.nextLine();
//		System.out.println("새마을열차 - 다음역에 대한 소요시간>");
//		String st_sa = ScanUtil.nextLine();

		List<Object> sta = new ArrayList<Object>();
//		Map<String, Object> param = new HashMap<>();
		sta.add(st_id);
		sta.add(st_nm);
/*		sta.add(null);
		sta.add(null);
		sta.add(null);*/
//		param.put("ST_NM", st_nm);
//		param.put("ST_KTX", st_ktx);
//		param.put("ST_MU", st_mu);
//		param.put("ST_SA", st_sa);
		int result = stationDao.addStation(sta);

		if (0 < result) {
			System.out.println("역 추가에 성공하였습니다.");
		} else {
			System.out.println("역 추가에 실패하였습니다.");
		}

		return View.ADMINHOME;
	}

	// 역 삭제
	public int station_Delete() {
		System.out.println("---------------------------------");
		System.out.println("기차역 번호를 입력하세요>");
		String st_id = ScanUtil.nextLine();

		List<Object> station = new ArrayList<Object>();

		station.add(st_id);

		int result = stationDao.deleteStation(station);
		if (0 < result) {
			System.out.println("역 삭제가 완료되었습니다.");
		} else {
			System.out.println("역 삭제가 실패하였습니다.");
		}
		return View.ADMINHOME;
	}


	// 역 정보 확인
	public void station_Print() {
		List<Map<String, Object>> stationList = stationDao.selectStaionList();
		System.out.println("---------------------------------------------------------------------");
		System.out.println(">>역 정보 확인 ");
		System.out.println("=====================================================================");
		System.out.println("기차역 번호\t기차역 명\tktx-다음역과 소요시간\t무궁화-다음역과 소요시간\t새마을-다음역과 소요시간");
		System.out.println("=====================================================================");
		for (Map<String, Object> station : stationList) {

			System.out.println(station.get("ST_ID") + "\t"
					+ station.get("ST_NM") + "\t\t"
					+ station.get("ST_KTX") + "\t\t"
					+ station.get("ST_MU") + "\t\t\t"
					+ station.get("ST_SA"));
		}
		System.out.println("---------------------------------------------------------------------");
	}
	
	//역 정보 변경
	public int station_Update() {
		List<Map<String, Object>> staList = stationDao.selectStaionList();
		List<Object> stationUpdate = new ArrayList<Object>();

		System.out.println("기차역번호\t기차역명\tKTX열차 소요시간\t무궁화 열차 소요시간\t새마을 열차 소요시간");
		System.out.println("====================================");
		for(Map<String, Object>add : staList) {
			System.out.println(add.get("ST_ID") + "\t" + add.get("ST_NM") 
			+ "\t" + add.get("ST_KTX") + "\t" + add.get("ST_MU") + "\t" + add.get("ST_SA") + "\t");
		}
		System.out.println("====================================");
		System.out.println("------------------------------------");
		
		System.out.println("변경할 기차역 번호 입력>");
		String st_id = ScanUtil.nextLine();
		
		boolean t = true;
		while (t) {
			System.out.println("------------------------------------");
			System.out.println(">>" + st_id + "번 기차역 변경");
			System.out.println("1.기차역명 변경\t2.KTX열차 소요시간 변경\t3.무궁화열차 소요시간 변경\t4.새마을열차 소요시간 변경\t5.뒤로가기");
			System.out.println("------------------------------------");
			System.out.println("번호 입력>");
			int input = ScanUtil.nextInt();
			int result = 0;
			switch (input) {
			case 1:
				System.out.println(st_id + "번 기차역의 변경할 기차역명 입력>");
				String st_nm = ScanUtil.nextLine();
				stationUpdate.add(st_id);
				stationUpdate.add(st_nm);
				result = stationDao.updateStation(stationUpdate, 1);
				if(0 < result) {
					System.out.println("기차역명이 변경되었습니다.");
				}else {
					System.out.println("기차역명 변경에 실패하였습니다.");
				}
				return View.ADMINHOME;

			case 2:
				System.out.println(st_id + "번 기차역의 변경할 KTX열차 소요시간 입력>");
				String st_ktx = ScanUtil.nextLine();
				stationUpdate.add(st_id);
				stationUpdate.add(st_ktx);
				result = stationDao.updateStation(stationUpdate, 2);
				if(0 < result) {
					System.out.println("KTX열차 소요시간이 변경되었습니다.");
				}else {
					System.out.println("KTX열차 소요시간 변경에 실패하였습니다.");
				}
				return View.ADMINHOME;
				
			case 3: 
				System.out.println(st_id + "번 기차역의 변경할 무궁화열차 소요시간 입력>");
				String st_mu = ScanUtil.nextLine();
				stationUpdate.add(st_id);
				stationUpdate.add(st_mu);
				result = stationDao.updateStation(stationUpdate, 3);
				if(0 < result) {
					System.out.println("무궁화열차 소요시간이 변경되었습니다.");
				}else {
					System.out.println("무궁화열차 소요시간 변경에 실패하였습니다.");
				}
				return View.ADMINHOME;
			case 4:
				System.out.println(st_id + "번 기차역의 변경할 새마을열차 소요시간 입력>");
				String st_sa = ScanUtil.nextLine();
				stationUpdate.add(st_id);
				stationUpdate.add(st_sa);
				result = stationDao.updateStation(stationUpdate, 4);
				if(0 < result) {
					System.out.println("새마을열차 소요시간이 변경되었습니다.");
				}else {
					System.out.println("새마을열차 소요시간 변경에 실패하였습니다.");
				}
				return View.ADMINHOME;
			case 5:
				t = false;
				break;
			}
		}
		return View.ADMINHOME;
//		System.out.println(st_id + "기차역 명 변경>");
//		String st_nm = ScanUtil.nextLine();
//		System.out.println(st_id + "KTX열차 소요시간>");
//		String st_ktx = ScanUtil.nextLine();
//		System.out.println(st_id + "무궁화 열차 소요시간 >");
//		String st_mu = ScanUtil.nextLine();
//		System.out.println(st_id + "새마을 열차 소요시간>");
//		String st_sa = ScanUtil.nextLine();
//
//		List<Object> stUpdate = new ArrayList<Object>();
//		stUpdate.add("st_id");
//		stUpdate.add("st_nm");
//		stUpdate.add("st_ktx");
//		stUpdate.add("st_mu");
//		stUpdate.add("st_sa");
//		
//		int result = stationDao.updateStation(stUpdate);
//		if (0 < result) {
//			System.out.println("역 정보가 변경되었습니다.");
//		} else {
//			System.out.println("역 정보를 변경하지 못했습니다.");
		}
	}

