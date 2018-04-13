package mtm_dao;

import static db.jdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

import mtm_vo.MtmBoardBean;

public class MtmBoardDAO {

	DataSource ds;
	Connection con;
	private static MtmBoardDAO boardDAO;

	private MtmBoardDAO() {
		// TODO Auto-generated constructor stub
	}

	public static MtmBoardDAO getInstance(){
		if(boardDAO == null){
			boardDAO = new MtmBoardDAO();
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
			pstmt=con.prepareStatement("select count(*) from mtmboard");
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
	public ArrayList<MtmBoardBean> selectArticleList(int page,int limit){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql="";
		
		sql="select * from ";
		sql+="(SELECT rownum rnum,MTM_NUM,MTM_NAME,MTM_PASS,";
		sql+="MTM_SUBJECT,MTM_CONTENT,MTM_FILE,";
		sql+="MTM_RE_REF, MTM_RE_LEV, MTM_RE_SEQ, MTM_READCOUNT,MTM_DATE FROM ";
		sql+="(SELECT * FroM mtmboard order by MTM_RE_REF ";
		sql+="desc,MTM_RE_SEQ asc)) where rnum between ? and ?";
		
		
		
		
		ArrayList<MtmBoardBean> articleList = new ArrayList<MtmBoardBean>();
		MtmBoardBean board = null;
		int startrow=(page*10)-9; //읽기 시작할 row 번호..	
		int endrow = startrow + 9;
		
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();

			while(rs.next()){
				board = new MtmBoardBean();
				board.setMTM_NUM(rs.getInt("MTM_NUM"));
				board.setMTM_NAME(rs.getString("MTM_NAME"));
				board.setMTM_SUBJECT(rs.getString("MTM_SUBJECT"));
				board.setMTM_CONTENT(rs.getString("MTM_CONTENT"));
				board.setMTM_FILE(rs.getString("MTM_FILE"));
				board.setMTM_RE_REF(rs.getInt("MTM_RE_REF"));
				board.setMTM_RE_LEV(rs.getInt("MTM_RE_LEV"));
				board.setMTM_RE_SEQ(rs.getInt("MTM_RE_SEQ"));
				board.setMTM_READCOUNT(rs.getInt("MTM_READCOUNT"));
				board.setMTM_DATE(rs.getDate("MTM_DATE"));
				articleList.add(board);
			}

		}catch(Exception ex){
			System.out.println("getMTMList 에러 : " + ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return articleList;

	}

	//글 내용 보기.
	public MtmBoardBean selectArticle(int MTM_num){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MtmBoardBean boardBean = null;

		try{
			pstmt = con.prepareStatement(
					"select * from mtmboard where MTM_NUM = ?");
			pstmt.setInt(1, MTM_num);
			rs= pstmt.executeQuery();

			if(rs.next()){
				boardBean = new MtmBoardBean();
				boardBean.setMTM_NUM(rs.getInt("MTM_NUM"));
				boardBean.setMTM_NAME(rs.getString("MTM_NAME"));
				boardBean.setMTM_SUBJECT(rs.getString("MTM_SUBJECT"));
				boardBean.setMTM_CONTENT(rs.getString("MTM_CONTENT"));
				boardBean.setMTM_FILE(rs.getString("MTM_FILE"));
				boardBean.setMTM_RE_REF(rs.getInt("MTM_RE_REF"));
				boardBean.setMTM_RE_LEV(rs.getInt("MTM_RE_LEV"));
				boardBean.setMTM_RE_SEQ(rs.getInt("MTM_RE_SEQ"));
				boardBean.setMTM_READCOUNT(rs.getInt("MTM_READCOUNT"));
				boardBean.setMTM_DATE(rs.getDate("MTM_DATE"));
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
	public int insertArticle(MtmBoardBean article){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num =0;
		String sql="";
		int insertCount=0;

		try{
			pstmt=con.prepareStatement("select max(MTM_num) from mtmboard");
			rs = pstmt.executeQuery();

			if(rs.next())
				num =rs.getInt(1)+1;
			else
				num=1;

			sql="insert into mtmboard (MTM_NUM,MTM_NAME,MTM_PASS,MTM_SUBJECT,MTM_CONTENT, MTM_FILE, MTM_RE_REF,MTM_RE_LEV,MTM_RE_SEQ,MTM_READCOUNT,MTM_DATE) values(?,?,?,?,?,?,?,?,?,?,sysdate)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, article.getMTM_NAME());
			pstmt.setString(3, article.getMTM_PASS());
			pstmt.setString(4, article.getMTM_SUBJECT());
			pstmt.setString(5, article.getMTM_CONTENT());
			pstmt.setString(6, article.getMTM_FILE());
			pstmt.setInt(7, num);
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 0);
			pstmt.setInt(10, 0);
			
			insertCount=pstmt.executeUpdate();

		}catch(Exception ex){
			System.out.println("mtmboardInsert 에러 : "+ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return insertCount;

	}

	//글 답변.
	@SuppressWarnings("resource")
	public int insertReplyArticle(MtmBoardBean article){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_max_sql="select max(mtm_num) from mtmboard";
		String sql="";
		int num=0;
		int insertCount=0;
		int re_ref=article.getMTM_RE_REF();
		int re_lev=article.getMTM_RE_LEV();
		int re_seq=article.getMTM_RE_SEQ();

		try{
			pstmt=con.prepareStatement(board_max_sql);
			rs = pstmt.executeQuery();
			if(rs.next())num =rs.getInt(1)+1;
			else num=1;
			sql="update mtmboard set MTM_RE_SEQ=MTM_RE_SEQ+1 where MTM_RE_REF=? and MTM_RE_SEQ>?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,re_ref);
			pstmt.setInt(2,re_seq);
			int updateCount=pstmt.executeUpdate();

			if(updateCount > 0){
				commit(con);
			}

			re_seq = re_seq + 1;
			re_lev = re_lev+1;
			sql="insert into mtmboard (MTM_NUM,MTM_NAME,MTM_PASS,MTM_SUBJECT,";
			sql+="MTM_CONTENT, MTM_FILE,MTM_RE_REF,MTM_RE_LEV,MTM_RE_SEQ,";
			sql+="MTM_READCOUNT,MTM_DATE) values(?,?,?,?,?,?,?,?,?,?,sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, article.getMTM_NAME());
			pstmt.setString(3, article.getMTM_PASS());
			pstmt.setString(4, article.getMTM_SUBJECT());
			pstmt.setString(5, article.getMTM_CONTENT());
			pstmt.setString(6, ""); //답장에는 파일을 업로드하지 않음.
			pstmt.setInt(7, re_ref);
			pstmt.setInt(8, re_lev);
			pstmt.setInt(9, re_seq);
			pstmt.setInt(10, 0);
			insertCount = pstmt.executeUpdate();
		}catch(SQLException ex){
			System.out.println("mtmboardReply 에러 : "+ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return insertCount;

	}

	//글 수정.
	public int updateArticle(MtmBoardBean article){

		int updateCount = 0;
		PreparedStatement pstmt = null;
		String sql="update mtmboard set MTM_SUBJECT=?,MTM_CONTENT=? where MTM_NUM=?";

		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getMTM_SUBJECT());
			pstmt.setString(2, article.getMTM_CONTENT());
			pstmt.setInt(3, article.getMTM_NUM());
			updateCount = pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("mtmboardModify 에러 : " + ex);
		}finally{
			close(pstmt);
		}

		return updateCount;

	}

	//글 삭제.
	public int deleteArticle(int mtm_num){

		PreparedStatement pstmt = null;
		String board_delete_sql="delete from mtmboard where MTM_num=?";
		int deleteCount=0;

		try{
			pstmt=con.prepareStatement(board_delete_sql);
			pstmt.setInt(1, mtm_num);
			deleteCount=pstmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("mtmboardDelete 에러 : "+ex);
		}	finally{
			close(pstmt);
		}

		return deleteCount;

	}

	//조회수 업데이트.
	public int updateReadCount(int mtm_num){

		PreparedStatement pstmt = null;
		int updateCount = 0;
		String sql="update mtmboard set MTM_READCOUNT = "+
				"MTM_READCOUNT+1 where MTM_NUM = "+mtm_num;

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
	public boolean isArticleBoardWriter(int mtm_num,String pass){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String board_sql="select * from mtmboard where MTM_NUM=?";
		boolean isWriter = false;

		try{
			pstmt=con.prepareStatement(board_sql);
			pstmt.setInt(1, mtm_num);
			rs=pstmt.executeQuery();
			rs.next();

			if(pass.equals(rs.getString("MTM_PASS"))){
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

}
