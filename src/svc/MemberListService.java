package svc;

import java.sql.Connection;
import java.util.ArrayList;
import dao.MemberDAO;
import static db.jdbcUtil.*;
import vo.MemberBean;

public class MemberListService {

	public ArrayList<MemberBean> getMemberList() {
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		ArrayList<MemberBean> memberList = memberDAO.selectMemberList();
	
		close(con);
		
		return memberList;
	}
}
