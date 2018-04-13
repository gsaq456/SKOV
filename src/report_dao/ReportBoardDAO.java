package report_dao;

import static db.jdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

import report_vo.ReportBoardBean;

public class ReportBoardDAO {

	DataSource ds;
	Connection con;
	private static ReportBoardDAO boardDAO;

	private ReportBoardDAO() {
		// TODO Auto-generated constructor stub
	}

	public static ReportBoardDAO getInstance(){
		if(boardDAO == null){
			boardDAO = new ReportBoardDAO();
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
			pstmt=con.prepareStatement("select count(*) from reportboard");
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
	public ArrayList<ReportBoardBean> selectArticleList(int page,int limit){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_list_sql="SELECT * FROM reportboard WHERE report_num >= ? and ROWNUM between 1 and 10";
		ArrayList<ReportBoardBean> articleList = new ArrayList<ReportBoardBean>();
		ReportBoardBean board = null;
		int startrow=(page*10)-9; //읽기 시작할 row 번호..	

		try{
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			rs = pstmt.executeQuery();

			while(rs.next()){
				board = new ReportBoardBean();
				board.setREPORT_NUM(rs.getInt("REPORT_NUM"));
				board.setREPORT_NAME(rs.getString("REPORT_NAME"));
				board.setREPORT_SUBJECT(rs.getString("REPORT_SUBJECT"));
				board.setREPORT_CONTENT(rs.getString("REPORT_CONTENT"));
				board.setREPORT_FILE(rs.getString("REPORT_FILE"));
				board.setREPORT_READCOUNT(rs.getInt("REPORT_READCOUNT"));
				board.setREPORT_DATE(rs.getDate("REPORT_DATE"));
				board.setREPORT_HEAD(rs.getString("REPORT_HEAD"));
				articleList.add(board);
			}

		}catch(Exception ex){
			System.out.println("getreportboardList 에러 : " + ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return articleList;

	}

	//글 내용 보기.
	public ReportBoardBean selectArticle(int REPORT_num){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ReportBoardBean boardBean = null;

		try{
			pstmt = con.prepareStatement(
					"select * from reportboard where REPORT_NUM = ?");
			pstmt.setInt(1, REPORT_num);
			rs= pstmt.executeQuery();

			if(rs.next()){
				boardBean = new ReportBoardBean();
				boardBean.setREPORT_NUM(rs.getInt("REPORT_NUM"));
				boardBean.setREPORT_NAME(rs.getString("REPORT_NAME"));
				boardBean.setREPORT_SUBJECT(rs.getString("REPORT_SUBJECT"));
				boardBean.setREPORT_CONTENT(rs.getString("REPORT_CONTENT"));
				boardBean.setREPORT_FILE(rs.getString("REPORT_FILE"));
				boardBean.setREPORT_READCOUNT(rs.getInt("REPORT_READCOUNT"));
				boardBean.setREPORT_DATE(rs.getDate("REPORT_DATE"));
			}
		}catch(Exception ex){
			System.out.println("getDetail 에러 : " + ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return boardBean;

	}

	//글 등록.
	public int insertArticle(ReportBoardBean article){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num =0;
		String sql="";
		int insertCount=0;

		try{
			pstmt=con.prepareStatement("select max(REPORT_num) from reportboard");
			rs = pstmt.executeQuery();

			if(rs.next())
				num =rs.getInt(1)+1;
			else
				num=1;

			sql="insert into reportboard (REPORT_NUM,REPORT_NAME,REPORT_PASS,REPORT_HEAD,REPORT_SUBJECT,REPORT_CONTENT, REPORT_FILE,REPORT_READCOUNT,REPORT_DATE) values(?,?,?,?,?,?,?,?,sysdate)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, article.getREPORT_NAME());
			pstmt.setString(3, article.getREPORT_PASS());
			pstmt.setString(4, article.getREPORT_HEAD());
			pstmt.setString(5, article.getREPORT_SUBJECT());
			pstmt.setString(6, article.getREPORT_CONTENT());
			pstmt.setString(7, article.getREPORT_FILE());
			pstmt.setInt(8, 0);
			
			
			insertCount=pstmt.executeUpdate();

		}catch(Exception ex){
			System.out.println("reportboardInsert 에러 : "+ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return insertCount;

	}

	//글 수정.
	public int updateArticle(ReportBoardBean article){

		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql="update reportboard set REPORT_SUBJECT=?,REPORT_CONTENT=? where REPORT_NUM=?";

		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getREPORT_SUBJECT());
			pstmt.setString(2, article.getREPORT_CONTENT());
			pstmt.setInt(3, article.getREPORT_NUM());
			updateCount = pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("reportboardModify 에러 : " + ex);
		}finally{
			close(pstmt);
		}

		return updateCount;

	}

	//글 삭제.
	public int deleteArticle(int REPORT_num){

		PreparedStatement pstmt = null;
		String board_delete_sql="delete from reportboard where REPORT_num=?";
		int deleteCount=0;

		try{
			pstmt=con.prepareStatement(board_delete_sql);
			pstmt.setInt(1, REPORT_num);
			deleteCount=pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("reportboardDelete 에러 : "+ex);
		}	finally{
			close(pstmt);
		}

		return deleteCount;

	}

	//조회수 업데이트.
	public int updateReadCount(int REPORT_num){

		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql="update reportboard set REPORT_READCOUNT = "+
				"REPORT_READCOUNT+1 where REPORT_NUM = "+REPORT_num;

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
	public boolean isArticleBoardWriter(int REPORT_num,String pass){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_sql="select * from reportboard where REPORT_NUM=?";
		boolean isWriter = false;

		try{
			pstmt=con.prepareStatement(board_sql);
			pstmt.setInt(1, REPORT_num);
			rs=pstmt.executeQuery();
			rs.next();

			if(pass.equals(rs.getString("REPORT_PASS"))){
				isWriter = true;
			}
		}catch(SQLException ex){
			System.out.println("isreportboardWriter 에러 : "+ex);
		}
		finally{
			close(pstmt);
		}

		return isWriter;

	}

}
