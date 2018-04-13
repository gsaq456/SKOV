package grade_action;

import javax.servlet.http.*;

import grade_vo.Grade_ActionForward;

//Action Ŭ�������� �԰��� �����ϱ� ���� �������̽�
public interface Grade_Action {
	// execute �޼ҵ忡 ���� �԰� ����
	public Grade_ActionForward execute(HttpServletRequest request,
								 HttpServletResponse response) throws Exception;

}
