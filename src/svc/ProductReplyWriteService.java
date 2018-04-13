package svc;

import static db.jdbcUtil.close;
import static db.jdbcUtil.commit;
import static db.jdbcUtil.getConnection;
import static db.jdbcUtil.rollback;

import java.sql.Connection;

import dao.BoardDAO;
import dao.ProductDAO;
import vo.ProductBean;
import vo.ReplyBean;

public class ProductReplyWriteService {
	public boolean registReply(ReplyBean replyBean,ProductBean productBean) throws Exception{
		// TODO Auto-generated method stub
		
		boolean isReplyWriteSuccess = false;
		Connection con = getConnection();
		ProductDAO productDAO = ProductDAO.getInstance();
		productDAO.setConnection(con);
		int insertCount = productDAO.insertReply(replyBean,productBean);
		
		if(insertCount > 0){
			commit(con);
			isReplyWriteSuccess = true;
		}
		else{
			rollback(con);
		}
		
		close(con);
		return isReplyWriteSuccess;
		
	}
}
