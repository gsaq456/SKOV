package report_action;

import javax.servlet.http.*;

import report_vo.ReportActionForward;

//Action 클래스들의 규격을 정의하기 위한 인터페이스
public interface ReportAction {
	// execute 메소드에 대한 규격 정의
	public ReportActionForward execute(HttpServletRequest request,
								 HttpServletResponse response) throws Exception;

}
