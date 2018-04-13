package svc;

import static db.jdbcUtil.close;
import static db.jdbcUtil.getConnection;

import java.sql.Connection;
import dao.MemberDAO;
import vo.MemberBean;

public class MemberViewService {
	
	public MemberBean getMember(String view_member_id) {
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		MemberBean member = memberDAO.selectMember(view_member_id);
		close(con);
		return member;
	}

}
