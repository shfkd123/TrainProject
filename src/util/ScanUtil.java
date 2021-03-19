package util;

import java.util.Scanner;

public class ScanUtil {

	// Scanner라는 메서드
	private static Scanner s = new Scanner(System.in);

	// staic이 붙은 nextLine 메서드
	public static String nextLine() {
		// return s.nextLine(); 오류
		// Scanner메서드는 아직 static을 붙이지 않아서 오류
		return s.nextLine(); // 오류 사라짐.
	}
	
	public static int nextInt(){
		return Integer.parseInt(s.nextLine());
	}
}
