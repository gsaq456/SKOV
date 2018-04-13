package svc;

import vo.MemberBean;
import static db.jdbcUtil.*;


import java.sql.Connection;


import dao.MemberDAO;

//�α��� ó���� ���� ����Ͻ� ������ �ִ� Ŭ����
// DB ������ ���� DAO ��ü�� ȣ���ϰ�
// �޼ҵ� ���� ����� MemberLoginAction Ŭ������ ������.
public class MemberLoginService {

	public boolean login(MemberBean member) {
		//DB ������ �ϰ� DAO Ŭ������ �α��ΰ� ���õ� SQL ���� ������ �ִ� �޼ҵ带 ȣ��
		
		
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
		//DB ������ �ϰ� DAO Ŭ������ �α��ΰ� ���õ� SQL ���� ������ �ִ� �޼ҵ带 ȣ��
		
		
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		MemberBean memberInfo = memberDAO.selectMember(id);
		
		close(con);
		return memberInfo;
	}
}
