package ans_action;

import javax.servlet.http.*;

import ans_vo.ansActionForward;

//Action Ŭ�������� �԰��� �����ϱ� ���� �������̽�
public interface ansAction {
	// execute �޼ҵ忡 ���� �԰� ����
	public ansActionForward execute(HttpServletRequest request,
								 HttpServletResponse response) throws Exception;

}
