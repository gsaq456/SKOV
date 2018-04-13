package Notice_action;

import javax.servlet.http.*;

import Notice_vo.noticeActionForward;

//Action Ŭ�������� �԰��� �����ϱ� ���� �������̽�
public interface noticeAction {
	// execute �޼ҵ忡 ���� �԰� ����
	public noticeActionForward execute(HttpServletRequest request,
								 HttpServletResponse response) throws Exception;

}
