package svc;

import java.sql.Connection;

import dao.MemberDAO;

import vo.MemberBean;

import static db.jdbcUtil.*;

public class MemberPhotoService {
	public boolean photoChange(MemberBean member) throws Exception{
		// TODO Auto-generated method stub
		
		boolean changeSuccess = false;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		int changeCount = memberDAO.changePhoto(member);
		
		if(changeCount > 0){
			commit(con);
			changeSuccess = true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return changeSuccess;
		
	}
}
