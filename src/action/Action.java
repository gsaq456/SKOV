package action;

import javax.servlet.http.*;
import vo.ActionForward;

//Action 클래스들의 규격을 정의하기 위한 인터페이스
public interface Action {

	//execute 메소드에 대한 규경 정의
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;

	
}
