package Notice_dao;

import static db.jdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

import Notice_vo.noticeBoardBean;

public class noticeBoardDAO {

	DataSource ds;
	Connection con;
	private static noticeBoardDAO noticeDAO;

	private noticeBoardDAO() {
		// TODO Auto-generated constructor stub
	}

	public static noticeBoardDAO getInstance(){
		if(noticeDAO == null){
			noticeDAO = new noticeBoardDAO();
		}
		return noticeDAO;
	}

	public void setConnection(Connection con){
		this.con = con;
	}

	//글의 개수 구하기
	public int selectListCount() {

		int listCount= 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			System.out.println("getConnection");
			pstmt=con.prepareStatement("select count(*) from noticeboard");
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

	//글의 목록 보기
	public ArrayList<noticeBoardBean> selectArticleList(int page,int limit){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_list_sql="SELECT * FROM noticeboard WHERE notice_num >= ? and ROWNUM between 1 and 10";
		ArrayList<noticeBoardBean> articleList = new ArrayList<noticeBoardBean>();
		noticeBoardBean noticeboard = null;
		int startrow=(page*10)-9; //�б� ������ row ��ȣ..	

		try{
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			rs = pstmt.executeQuery();

			while(rs.next()){
				noticeboard = new noticeBoardBean();
				noticeboard.setNOTICE_NUM(rs.getInt("NOTICE_NUM"));
				noticeboard.setNOTICE_NAME(rs.getString("NOTICE_NAME"));
				noticeboard.setNOTICE_SUBJECT(rs.getString("NOTICE_SUBJECT"));
				noticeboard.setNOTICE_CONTENT(rs.getString("NOTICE_CONTENT"));
			noticeboard.setNOTICE_READCOUNT(rs.getInt("NOTICE_READCOUNT"));
				noticeboard.setNOTICE_DATE(rs.getDate("NOTICE_DATE"));
				articleList.add(noticeboard);
			}

		}catch(Exception ex){
			System.out.println("getnoticeboardList 에러 : " + ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return articleList;

	}

	//글내용보기.
	public noticeBoardBean selectArticle(int notice_num){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		noticeBoardBean noticeboardBean = null;

		try{
			pstmt = con.prepareStatement(
					"select * from Noticeboard where NOTICE_NUM = ?");
			pstmt.setInt(1, notice_num);
			rs= pstmt.executeQuery();

			if(rs.next()){
				noticeboardBean = new noticeBoardBean();
				noticeboardBean.setNOTICE_NUM(rs.getInt("NOTICE_NUM"));
				noticeboardBean.setNOTICE_NAME(rs.getString("NOTICE_NAME"));
				noticeboardBean.setNOTICE_SUBJECT(rs.getString("NOTICE_SUBJECT"));
				noticeboardBean.setNOTICE_CONTENT(rs.getString("NOTICe_CONTENT"));
				noticeboardBean.setNOTICE_READCOUNT(rs.getInt("NOTICE_READCOUNT"));
				noticeboardBean.setNOTICE_DATE(rs.getDate("NOTICE_DATE"));
			}
		}catch(Exception ex){
			System.out.println("getDetail 에러 : " + ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return noticeboardBean;

	}

	//글 등록
	public int insertArticle(noticeBoardBean article){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num =0;
		String sql="";
		int insertCount=0;

		try{
			pstmt=con.prepareStatement("select max(notice_num) from noticeboard");
			rs = pstmt.executeQuery();

			if(rs.next())
				num =rs.getInt(1)+1;
			else
				num=1;

			sql="insert into noticeboard (NOTICE_NUM,NOTICE_NAME,NOTICE_PASS,NOTICE_SUBJECT,NOTICE_CONTENT,NOTICE_READCOUNT,NOTICE_DATE) values(?,?,?,?,?,?,sysdate)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, article.getNOTICE_NAME());
			pstmt.setString(3, article.getNOTICE_PASS());
			pstmt.setString(4, article.getNOTICE_SUBJECT());
			pstmt.setString(5, article.getNOTICE_CONTENT());
			pstmt.setInt(6, 0);
			
			
			insertCount=pstmt.executeUpdate();

		}catch(Exception ex){
			System.out.println("noticeboardInsert 에러 : "+ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return insertCount;

	}

	//글수전

	public int insertReplyArticle(noticeBoardBean article){
		int updateCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="update noticebard set NOTICE_SUBJECT=?,NOTICE_CONTENT=? where NOTICE_NUM=?";
	try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getNOTICE_SUBJECT());
			pstmt.setString(2, article.getNOTICE_CONTENT());
			pstmt.setInt(3, article.getNOTICE_NUM());
			updateCount = pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("noticeboardModify 에러 : " + ex);
		}finally{
			close(pstmt);
		}

		return updateCount;

	}

	//글 삭제.
	public int deleteArticle(int notice_num){

		PreparedStatement pstmt = null;
		String board_delete_sql="delete from noticeboard where notice_num=?";
		int deleteCount=0;

		try{
			pstmt=con.prepareStatement(board_delete_sql);
			pstmt.setInt(1, notice_num);
			deleteCount=pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("noticeboardDelete 에러 : "+ex);
		}	finally{
			close(pstmt);
		}

		return deleteCount;

	}

	//조회수 업데이트Ʈ.
	public int updateReadCount(int notice_num){

		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql="update noticeboard set NOTICE_READCOUNT = "+
				"NOTICE_READCOUNT+1 where NOTICE_NUM = "+notice_num;

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

	//글쓴이인지 확인
	public boolean isArticleBoardWriter(int notice_num,String pass){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_sql="select * from noticeboard where NOTICE_NUM=?";
		boolean isWriter = false;

		try{
			pstmt=con.prepareStatement(board_sql);
			pstmt.setInt(1, notice_num);
			rs=pstmt.executeQuery();
			rs.next();

			if(pass.equals(rs.getString("NOTICE_PASS"))){
				isWriter = true;
			}
		}catch(SQLException ex){
			System.out.println("isBoardWriter 에러 : "+ex);
		}
		finally{
			close(pstmt);
		}

		return isWriter;

	}


public int updateArticle(noticeBoardBean article){

	int updateCount = 0;
	PreparedStatement pstmt = null;
	String sql="update noticeboard set NOTICE_SUBJECT=?,NOTICE_CONTENT=? where NOTICE_NUM=?";

	try{
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, article.getNOTICE_SUBJECT());
		pstmt.setString(2, article.getNOTICE_CONTENT());
		pstmt.setInt(3, article.getNOTICE_NUM());
		updateCount = pstmt.executeUpdate();
	}catch(Exception ex){
		System.out.println("noticeboardModify 에러 : " + ex);
	}finally{
		close(pstmt);
	}

	return updateCount;

}
}
