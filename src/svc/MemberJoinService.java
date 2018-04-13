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
		//DAO ȣ�� ����� ���� ����
		//insert ������ �����ϸ� 1�̻��� ���� ���� �ް� insert�� ����� ���� ������
		//0�� ���� �ް� ��. ���� �޴� ���� ���� ����, ���� ���θ� �Ǵ���.
		
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
