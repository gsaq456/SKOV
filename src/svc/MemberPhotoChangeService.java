package svc;

import static db.jdbcUtil.*;
import java.sql.Connection;


import dao.MemberDAO;
import vo.MemberBean;

public class MemberPhotoChangeService {
	
	public MemberBean getMemberPhoto(String login_id) throws Exception{
		// TODO Auto-generated method stub
		
		MemberBean memberPhoto = null;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		
		memberPhoto = memberDAO.selectPhoto(login_id);
		close(con);
		
		return memberPhoto;
		
	}
}
