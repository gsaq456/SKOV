package svc;



public class MemberLogoutService {

	public boolean logout(String loginId) {
		//DB ������ �ϰ� DAO Ŭ������ �α��ΰ� ���õ� SQL ���� ������ �ִ� �޼ҵ带 ȣ��
		
		
		
		boolean logoutResult = false;
		
		if(loginId == null) {
			
			logoutResult = true;
		}
		
		return logoutResult;
	}
}
