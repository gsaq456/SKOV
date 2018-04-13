package svc;

import java.sql.Connection;
import java.util.ArrayList;

import dao.BoardDAO;
import vo.BoardBean;
import vo.ReplyBean;

import static db.jdbcUtil.*;

public class BoardDetailService {

	public BoardBean getArticle(int FB_num) throws Exception{
		// TODO Auto-generated method stub
		
		BoardBean article = null;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		int updateCount = boardDAO.updateReadCount(FB_num);
		
		if(updateCount > 0){
			commit(con);
		}
		else{
			rollback(con);
		}
		
		article = boardDAO.selectArticle(FB_num);
		close(con);
		return article;
		
	}
	
	/*public ReplyBean getReply(int FB_num) throws Exception{
		
		ReplyBean reply = null;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		
		
		reply = boardDAO.selectReply(FB_num);
		close(con);
		
		return reply;
		
	}*/
	public ArrayList<ReplyBean> getReply(int FB_num) throws Exception{
		
		ArrayList<ReplyBean> replyList = null;
		Connection con = getConnection();
		BoardDAO boardDAO = BoardDAO.getInstance();
		boardDAO.setConnection(con);
		replyList = boardDAO.selectReplylist(FB_num);
		close(con);
		return replyList;
		
	}
}
