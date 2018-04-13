package svc;

import vo.MemberBean;
import static db.jdbcUtil.*;


import java.sql.Connection;


import dao.MemberDAO;

//로그인 처리를 위한 비즈니스 로직이 있는 클래스
// DB 접속을 위해 DAO 객체를 호출하고
// 메소드 수행 결과를 MemberLoginAction 클래스로 리턴함.
public class MemberLoginService {

	public boolean login(MemberBean member) {
		//DB 연결을 하고 DAO 클래스로 로그인과 관련된 SQL 문을 가지고 있는 메소드를 호출
		
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		boolean loginResult = false;
		String loginId = memberDAO.selectLoginId(member);
		if(loginId != null) {
			
			loginResult = true;
		}
		close(con);
		return loginResult;
	}
	public MemberBean selectInfo(String id) {
		//DB 연결을 하고 DAO 클래스로 로그인과 관련된 SQL 문을 가지고 있는 메소드를 호출
		
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		MemberBean memberInfo = memberDAO.selectMember(id);
		
		close(con);
		return memberInfo;
	}
}
