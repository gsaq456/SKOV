package report_action;

import javax.servlet.http.*;

import report_vo.ReportActionForward;

//Action Ŭ�������� �԰��� �����ϱ� ���� �������̽�
public interface ReportAction {
	// execute �޼ҵ忡 ���� �԰� ����
	public ReportActionForward execute(HttpServletRequest request,
								 HttpServletResponse response) throws Exception;

}
