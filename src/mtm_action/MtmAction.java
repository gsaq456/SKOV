package mtm_action;

import javax.servlet.http.*;

import mtm_vo.MtmActionForward;

//Action Ŭ�������� �԰��� �����ϱ� ���� �������̽�
public interface MtmAction {
	// execute �޼ҵ忡 ���� �԰� ����
	public MtmActionForward execute(HttpServletRequest request,
								 HttpServletResponse response) throws Exception;

}
