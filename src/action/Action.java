package action;

import javax.servlet.http.*;
import vo.ActionForward;

//Action Ŭ�������� �԰��� �����ϱ� ���� �������̽�
public interface Action {

	//execute �޼ҵ忡 ���� �԰� ����
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;

	
}
