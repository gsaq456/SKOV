package svc;



public class MemberLogoutService {

	public boolean logout(String loginId) {
		//DB 연결을 하고 DAO 클래스로 로그인과 관련된 SQL 문을 가지고 있는 메소드를 호출
		
		
		
		boolean logoutResult = false;
		
		if(loginId == null) {
			
			logoutResult = true;
		}
		
		return logoutResult;
	}
}
