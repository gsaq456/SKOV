package ans_dao;

import static ans_db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

import ans_vo.ansBoardBean;

public class ansBoardDAO {

	DataSource ds;
	Connection con;
	private static ansBoardDAO ansDAO;

	private ansBoardDAO() {
		// TODO Auto-generated constructor stub
	}

	public static ansBoardDAO getInstance(){
		if(ansDAO == null){
			ansDAO = new ansBoardDAO();
		}
		return ansDAO;
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
			pstmt=con.prepareStatement("select count(*) from ansboard");
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
	public ArrayList<ansBoardBean> selectArticleList(int page,int limit){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_list_sql="SELECT * FROM ansboard WHERE ans_num >= ? and ROWNUM between 1 and 10";
		ArrayList<ansBoardBean> articleList = new ArrayList<ansBoardBean>();
		ansBoardBean ansboard = null;
		int startrow=(page*10)-9; //�б� ������ row ��ȣ..	

		try{
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			rs = pstmt.executeQuery();

			while(rs.next()){
				ansboard = new ansBoardBean();
				ansboard.setANS_NUM(rs.getInt("ANS_NUM"));
				ansboard.setANS_NAME(rs.getString("ANS_NAME"));
				ansboard.setANS_SUBJECT(rs.getString("ANS_SUBJECT"));
				ansboard.setANS_CONTENT(rs.getString("ANS_CONTENT"));
			ansboard.setANS_READCOUNT(rs.getInt("ANS_READCOUNT"));
				ansboard.setANS_DATE(rs.getDate("ANS_DATE"));
				articleList.add(ansboard);
			}

		}catch(Exception ex){
			System.out.println("getansboardList 에러 : " + ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return articleList;

	}

	//글내용보기.
	public ansBoardBean selectArticle(int ans_num){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ansBoardBean ansboardBean = null;

		try{
			pstmt = con.prepareStatement(
					"select * from ansboard where ANS_NUM = ?");
			pstmt.setInt(1, ans_num);
			rs= pstmt.executeQuery();

			if(rs.next()){
				ansboardBean = new ansBoardBean();
				ansboardBean.setANS_NUM(rs.getInt("ANS_NUM"));
				ansboardBean.setANS_NAME(rs.getString("ANS_NAME"));
				ansboardBean.setANS_SUBJECT(rs.getString("ANS_SUBJECT"));
				ansboardBean.setANS_CONTENT(rs.getString("ANS_CONTENT"));
				ansboardBean.setANS_READCOUNT(rs.getInt("ANS_READCOUNT"));
				ansboardBean.setANS_DATE(rs.getDate("ANS_DATE"));
			}
		}catch(Exception ex){
			System.out.println("getDetail 에러 : " + ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return ansboardBean;

	}

	//글 등록
	public int insertArticle(ansBoardBean article){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num =0;
		String sql="";
		int insertCount=0;

		try{
			pstmt=con.prepareStatement("select max(ans_num) from ansboard");
			rs = pstmt.executeQuery();

			if(rs.next())
				num =rs.getInt(1)+1;
			else
				num=1;

			sql="insert into ansboard (ANS_NUM,ANS_NAME,ANS_PASS,ANS_SUBJECT,ANS_CONTENT,ANS_READCOUNT,ANS_DATE) values(?,?,?,?,?,?,sysdate)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, article.getANS_NAME());
			pstmt.setString(3, article.getANS_PASS());
			pstmt.setString(4, article.getANS_SUBJECT());
			pstmt.setString(5, article.getANS_CONTENT());
			pstmt.setInt(6, 0);
			
			
			insertCount=pstmt.executeUpdate();

		}catch(Exception ex){
			System.out.println("ansboardInsert 에러 : "+ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return insertCount;

	}

	//글수전

	public int insertReplyArticle(ansBoardBean article){
		int updateCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="update ansboard set ANS_SUBJECT=?,ANS_CONTENT=? where ANS_NUM=?";
	try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getANS_SUBJECT());
			pstmt.setString(2, article.getANS_CONTENT());
			pstmt.setInt(3, article.getANS_NUM());
			updateCount = pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("ansboardModify 에러 : " + ex);
		}finally{
			close(pstmt);
		}

		return updateCount;

	}

	//글 삭제.
	public int deleteArticle(int ans_num){

		PreparedStatement pstmt = null;
		String board_delete_sql="delete from ansboard where ans_num=?";
		int deleteCount=0;

		try{
			pstmt=con.prepareStatement(board_delete_sql);
			pstmt.setInt(1, ans_num);
			deleteCount=pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("ansboardDelete 에러 : "+ex);
		}	finally{
			close(pstmt);
		}

		return deleteCount;

	}

	//조회수 업데이트Ʈ.
	public int updateReadCount(int ans_num){

		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql="update ansboard set ANS_READCOUNT = "+
				"ANS_READCOUNT+1 where ANS_NUM = "+ans_num;

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
	public boolean isArticleBoardWriter(int ans_num,String pass){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_sql="select * from ansboard where ANS_NUM=?";
		boolean isWriter = false;

		try{
			pstmt=con.prepareStatement(board_sql);
			pstmt.setInt(1, ans_num);
			rs=pstmt.executeQuery();
			rs.next();

			if(pass.equals(rs.getString("ANS_PASS"))){
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


public int updateArticle(ansBoardBean article){

	int updateCount = 0;
	PreparedStatement pstmt = null;
	String sql="update ansboard set ANS_SUBJECT=?,ANS_CONTENT=? where ANS_NUM=?";

	try{
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, article.getANS_SUBJECT());
		pstmt.setString(2, article.getANS_CONTENT());
		pstmt.setInt(3, article.getANS_NUM());
		updateCount = pstmt.executeUpdate();
	}catch(Exception ex){
		System.out.println("ansboardModify 에러 : " + ex);
	}finally{
		close(pstmt);
	}

	return updateCount;

}
}
