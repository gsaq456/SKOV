package svc;

import static db.jdbcUtil.close;
import static db.jdbcUtil.commit;
import static db.jdbcUtil.getConnection;
import static db.jdbcUtil.rollback;

import java.sql.Connection;
import dao.MemberDAO;


public class MemberGradeService {
	
	public boolean getMember(String member_id, String member_grade) {
		boolean gruntSucess = false;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		int gruntResult = memberDAO.gruntMember(member_id, member_grade);
		
		
		
		if(gruntResult > 0 ) {
			gruntSucess = true;
			commit(con);
		}
		else {
			rollback(con);
		}
		
		close(con);
		return gruntSucess;
	}

}
