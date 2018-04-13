package svc;

import static db.jdbcUtil.*;
import java.sql.Connection;

import dao.MemberDAO;

public class MemberInfoModifyService {
	
	public boolean modifyInfo(String id, String pw, String addr, String skin) throws Exception {
		// TODO Auto-generated method stub
		
		boolean memberModifySuccess = false;
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		int updateCount = memberDAO.modifyMember(id,pw,addr,skin);
		
		if(updateCount > 0){
			commit(con);
			memberModifySuccess=true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return memberModifySuccess;
		
	}
}
