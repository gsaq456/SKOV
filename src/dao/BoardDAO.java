package dao;

import static db.jdbcUtil.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import vo.BoardBean;
import vo.ReplyBean;

public class BoardDAO {

	DataSource ds;
	Connection con;
	private static BoardDAO boardDAO;

	private BoardDAO() {
		// TODO Auto-generated constructor stub
	}

	public static BoardDAO getInstance(){
		if(boardDAO == null){
			boardDAO = new BoardDAO();
		}
		return boardDAO;
	}

	public void setConnection(Connection con){
		this.con = con;
	}

	//글의 개수 구하기.
	public int selectListCount() {

		int listCount= 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{


			System.out.println("getConnection");
			pstmt=con.prepareStatement("select count(*) from freeboard");
			rs = pstmt.executeQuery();

			if(rs.next()){
				listCount=rs.getInt(1);
				
			}
		}catch(Exception ex){
			System.out.println("getListCount 에러: " + ex);			
		}finally{
			close(rs);
			close(pstmt);
		}

		return listCount;

	}

	//글 목록 보기.	
	public ArrayList<BoardBean> selectArticleList(int page,int limit){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_list_sql="select * from (SELECT rownum rnum,FB_NUM,FB_NAME,FB_PASS,FB_HEAD,FB_SUBJECT,FB_CONTENT,FB_FILE,"+
				"FB_READCOUNT,FB_DATE FROM (SELECT * FroM FREEBOARD order by FB_NUM desc)) where rnum between ? and ?";
		ArrayList<BoardBean> articleList = new ArrayList<BoardBean>();
		BoardBean board = null;
		int startpage=(page*10)-9; //읽기 시작할 row 번호..	
		int endpage = startpage + 9;
		try{
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1, startpage);
			pstmt.setInt(2, endpage);
			rs = pstmt.executeQuery();

			while(rs.next()){
				board = new BoardBean();
				board.setFB_NUM(rs.getInt("FB_NUM"));
				board.setFB_NAME(rs.getString("FB_NAME"));
				board.setFB_SUBJECT(rs.getString("FB_SUBJECT"));
				board.setFB_CONTENT(rs.getString("FB_CONTENT"));
				board.setFB_FILE(rs.getString("FB_FILE"));
				board.setFB_READCOUNT(rs.getInt("FB_READCOUNT"));
				board.setFB_DATE(rs.getDate("FB_DATE"));
				board.setFB_HEAD(rs.getString("FB_HEAD"));
				articleList.add(board);
			}

		}catch(Exception ex){
			System.out.println("getFreeBoardList 에러 : " + ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return articleList;

	}

	//글 내용 보기.
	public BoardBean selectArticle(int FB_num){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardBean boardBean = null;

		try{
			pstmt = con.prepareStatement(
					"select * from freeboard where FB_NUM = ?");
			pstmt.setInt(1, FB_num);
			rs= pstmt.executeQuery();

			if(rs.next()){
				boardBean = new BoardBean();
				boardBean.setFB_NUM(rs.getInt("FB_NUM"));
				boardBean.setFB_NAME(rs.getString("FB_NAME"));
				boardBean.setFB_SUBJECT(rs.getString("FB_SUBJECT"));
				boardBean.setFB_CONTENT(rs.getString("FB_CONTENT"));
				boardBean.setFB_FILE(rs.getString("FB_FILE"));
				boardBean.setFB_READCOUNT(rs.getInt("FB_READCOUNT"));
				boardBean.setFB_DATE(rs.getDate("FB_DATE"));
			}
		}catch(Exception ex){
			System.out.println("getDetail 에러 : " + ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return boardBean;

	}
	// 댓글 찾기
	public ArrayList<ReplyBean> selectReplylist(int FB_NUM){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_list_sql="SELECT REPLY_NUM, m_id, fb_num, reply,reply_date FroM (select *from reply order by reply_NUM asc )where fb_num =? ";
		ArrayList<ReplyBean> articleList = new ArrayList<ReplyBean>();
		ReplyBean replyBean = null;
		
		try{
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1, FB_NUM);
			
			rs = pstmt.executeQuery();

			while(rs.next()){
				replyBean = new ReplyBean();
				replyBean.setFB_REPLY(rs.getString("REPLY"));
				replyBean.setM_ID(rs.getString("M_ID"));
				replyBean.setREPLY_DATE(rs.getDate("REPLY_DATE"));
				
				articleList.add(replyBean);
			}

		}catch(Exception ex){
			System.out.println("getReplyFreeBoardList 에러 : " + ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return articleList;

	}
	
	//글 등록.
	public int insertArticle(BoardBean article){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num =0;
		String sql="";
		int insertCount=0;

		try{
			pstmt=con.prepareStatement("select max(FB_num) from freeboard");
			rs = pstmt.executeQuery();

			if(rs.next())
				num =rs.getInt(1)+1;
			else
				num=1;

			sql="insert into freeboard (FB_NUM,FB_NAME,FB_PASS,FB_HEAD,FB_SUBJECT,FB_CONTENT, FB_FILE,FB_READCOUNT,FB_DATE) values(?,?,?,?,?,?,?,?,sysdate)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, article.getFB_NAME());
			pstmt.setString(3, article.getFB_PASS());
			pstmt.setString(4, article.getFB_HEAD());
			pstmt.setString(5, article.getFB_SUBJECT());
			pstmt.setString(6, article.getFB_CONTENT());
			pstmt.setString(7, article.getFB_FILE());
			pstmt.setInt(8, 0);
			
			
			insertCount=pstmt.executeUpdate();

		}catch(Exception ex){
			System.out.println("freeboardInsert 에러 : "+ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return insertCount;

	}
	
	//글 수정.
	public int updateArticle(BoardBean article){

		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql="update freeboard set FB_SUBJECT=?,FB_CONTENT=? where FB_NUM=?";

		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getFB_SUBJECT());
			pstmt.setString(2, article.getFB_CONTENT());
			pstmt.setInt(3, article.getFB_NUM());
			updateCount = pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("freeboardModify 에러 : " + ex);
		}finally{
			close(pstmt);
		}

		return updateCount;

	}

	//글 삭제.
	public int deleteArticle(int FB_num){

		PreparedStatement pstmt = null;
		String board_delete_sql="delete from freeboard where FB_num=?";
		int deleteCount=0;

		try{
			pstmt=con.prepareStatement(board_delete_sql);
			pstmt.setInt(1, FB_num);
			deleteCount=pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("freeboardDelete 에러 : "+ex);
		}	finally{
			close(pstmt);
		}

		return deleteCount;

	}
	//댓글 등록.
	public int insertReply(ReplyBean replyBean){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num =0;
		String sql="";
		int insertCount=0;

		try{
			pstmt=con.prepareStatement("select max(reply_num) from reply");
			rs = pstmt.executeQuery();

			if(rs.next())
				num =rs.getInt(1)+1;
			else
				num=1;

			sql="insert into reply (reply_num,M_id,FB_Num,reply,REPLY_DATE) values(?,?,?,?,sysdate)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, replyBean.getM_ID());
			pstmt.setInt(3, replyBean.getFB_NUM());
			pstmt.setString(4, replyBean.getFB_REPLY());
			
			insertCount=pstmt.executeUpdate();

		}catch(Exception ex){
			System.out.println("freeboardReplyInsert 에러 : " + ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return insertCount;

	}
	//조회수 업데이트.
	public int updateReadCount(int FB_num){

		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql="update freeboard set FB_READCOUNT = "+
				"FB_READCOUNT+1 where FB_NUM = "+FB_num;

		try{
			pstmt=con.prepareStatement(sql);
			updateCount = pstmt.executeUpdate();
		}catch(SQLException ex){
			System.out.println("setReadCountUpdate 에러 : "+ex);
		}
		finally{
			close(pstmt);

		}

		return updateCount;

	}

	//글쓴이인지 확인.
	public boolean isArticleBoardWriter(int FB_num,String pass){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_sql="select * from freeboard where FB_NUM=?";
		boolean isWriter = false;

		try{
			pstmt=con.prepareStatement(board_sql);
			pstmt.setInt(1, FB_num);
			rs=pstmt.executeQuery();
			rs.next();

			if(pass.equals(rs.getString("FB_PASS"))){
				isWriter = true;
			}
		}catch(SQLException ex){
			System.out.println("isfreeBoardWriter 에러 : "+ex);
		}
		finally{
			close(pstmt);
		}

		return isWriter;

	}

}
