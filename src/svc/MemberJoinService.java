package svc;


import java.sql.Connection;


import static db.jdbcUtil.*;
import dao.MemberDAO;
import vo.MemberBean;

public class MemberJoinService {

	public boolean joinMember(MemberBean member) {
		boolean joinSucess = false;
		MemberDAO memberDAO = MemberDAO.getInstance();
		Connection con = getConnection();
		memberDAO.setConnection(con);
		//DAO 호출 결과를 담을 변수
		//insert 쿼리가 성공하면 1이상의 값을 리턴 받고 insert가 제대로 되지 않으면
		//0을 리턴 받게 됨. 리턴 받는 값을 통해 성공, 실패 여부를 판단함.
		
		int insertCount = memberDAO.insertMember(member);
		if(insertCount > 0 ) {
			joinSucess = true;
			commit(con);
		}
		else {
			rollback(con);
		}
		
		close(con);
		return joinSucess;
		
	}
	
}
