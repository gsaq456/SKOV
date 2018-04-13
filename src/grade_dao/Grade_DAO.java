package grade_dao;

import static db.jdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

import grade_vo.Grade_Bean;

public class Grade_DAO {

	DataSource ds;
	Connection con;
	private static Grade_DAO gradeDAO;

	private Grade_DAO() {
		// TODO Auto-generated constructor stub
	}

	public static Grade_DAO getInstance(){
		if(gradeDAO == null){
			gradeDAO = new Grade_DAO();
		}
		return gradeDAO;
	}

	public void setConnection(Connection con){
		this.con = con;
	}

	//�뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�룞�삕 �뜝�룞�삕�뜝�떦源띿삕.
	public int selectListCount() {

		int listCount= 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{


			System.out.println("getConnection");
			pstmt=con.prepareStatement("select count(*) from gradeboard");
			rs = pstmt.executeQuery();

			if(rs.next()){
				listCount=rs.getInt(1);
			}
		}catch(Exception ex){
			System.out.println("getListCount �뿉�윭: " + ex);			
		}finally{
			close(rs);
			close(pstmt);
		}

		return listCount;

	}

	//疫뀐옙筌뤴뫖以됭퉪�떯由�.	
	public ArrayList<Grade_Bean> selectArticleList(int page,int limit){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_list_sql="select * from (SELECT rownum rnum,up_num,up_NAME,up_PASS,up_SUBJECT,up_CONTENT,"+
				"up_DATE FROM (SELECT * FroM gradeboard order by up_num desc)) where rnum between ? and ?";
				
				
		ArrayList<Grade_Bean> articleList = new ArrayList<Grade_Bean>();
		Grade_Bean board = null;
		int startpage=(page*10)-9; //읽기 시작할 row 번호..	
		int endpage = startpage + 9; //占쎌뵭疫뀐옙 占쎈뻻占쎌삂占쎈막 row 甕곕뜇�깈..

		try{
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1, startpage);
			pstmt.setInt(2, endpage);
			
			rs = pstmt.executeQuery();

			while(rs.next()){
				board = new Grade_Bean();
				board.setUP_NUM(rs.getInt("UP_NUM"));
				board.setUP_NAME(rs.getString("UP_NAME"));
				board.setUP_SUBJECT(rs.getString("UP_SUBJECT"));
				board.setUP_CONTENT(rs.getString("UP_CONTENT"));
				board.setUP_DATE(rs.getDate("UP_DATE"));
				articleList.add(board);
			}

		}catch(Exception ex){
			System.out.println("getBoardList 占쎈퓠占쎌쑎 : " + ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return articleList;

	}

	//疫뀐옙 占쎄땀占쎌뒠 癰귣떯由�.
	public Grade_Bean selectArticle(int up_num){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Grade_Bean gradeBean = null;

		try{
			pstmt = con.prepareStatement(
					"select * from gradeboard where UP_NUM = ?");
			pstmt.setInt(1, up_num);
			rs= pstmt.executeQuery();

			if(rs.next()){
				gradeBean = new Grade_Bean();
				gradeBean.setUP_NUM(rs.getInt("UP_NUM"));
				gradeBean.setUP_NAME(rs.getString("UP_NAME"));
				gradeBean.setUP_SUBJECT(rs.getString("UP_SUBJECT"));
				gradeBean.setUP_CONTENT(rs.getString("UP_CONTENT"));
				gradeBean.setUP_DATE(rs.getDate("UP_DATE"));
			}
		}catch(Exception ex){
			System.out.println("getDetail 占쎈퓠占쎌쑎: " + ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return gradeBean;

	}

	//疫뀐옙占쎈쾻嚥∽옙.
	public int insertArticle(Grade_Bean article){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num =0;
		String sql="";
		int insertCount=0;

		try{
			pstmt=con.prepareStatement("select max(up_num) from gradeboard");
			rs = pstmt.executeQuery();

			if(rs.next())
				num =rs.getInt(1)+1;
			else
				num=1;

			sql="insert into gradeboard (UP_NUM,UP_NAME,UP_PASS,UP_SUBJECT,UP_CONTENT,UP_DATE) values(?,?,?,?,?,sysdate)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, article.getUP_NAME());
			pstmt.setString(3, article.getUP_PASS());
			pstmt.setString(4, article.getUP_SUBJECT());
			pstmt.setString(5, article.getUP_CONTENT());
			
			
			
			
			insertCount=pstmt.executeUpdate();

		}catch(Exception ex){
			System.out.println("gradeInsert �뿉�윭: "+ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return insertCount;

	}


	
		
	//疫뀐옙占쎈땾占쎌젟.
	public int updateArticle(Grade_Bean article){

		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql="update gradeboard set UP_SUBJECT=?,UP_CONTENT=? where UP_NUM=?";

		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getUP_SUBJECT());
			pstmt.setString(2, article.getUP_CONTENT());
			pstmt.setInt(3, article.getUP_NUM());
			updateCount = pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("gradeModify 占쎈퓠占쎌쑎 : " + ex);
		}finally{
			close(pstmt);
		}

		return updateCount;

	}

	//疫뀐옙 占쎄텣占쎌젫.
	public int deleteArticle(int up_num){

		PreparedStatement pstmt = null;
		String board_delete_sql="delete from gradeboard where UP_num=?";
		int deleteCount=0;

		try{
			pstmt=con.prepareStatement(board_delete_sql);
			pstmt.setInt(1, up_num);
			deleteCount=pstmt.executeUpdate();
		}catch(Exception ex){ 
			System.out.println("boardDelete 占쎈퓠占쎌쑎 : "+ex);
		}	finally{
			close(pstmt);
		}

		return deleteCount;

	}


	public boolean isArticleBoardWriter(int up_num,String pass){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_sql="select * from gradeboard where UP_NUM=?";
		boolean isWriter = false;
		
		System.out.println(up_num);
		System.out.println(pass);
		
		try{
			pstmt=con.prepareStatement(board_sql);
			pstmt.setInt(1, up_num);
			rs=pstmt.executeQuery();
			rs.next();

			if(pass.equals(rs.getString("UP_PASS"))){
				isWriter = true;
			}
		}catch(SQLException ex){
			System.out.println("isBoardWriter 占쎈퓠占쎌쑎 : "+ex);
		}
		finally{
			close(pstmt);
		}

		return isWriter;

	}

}
