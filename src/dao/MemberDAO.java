package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.sql.DataSource;

import vo.BoardBean;
import vo.MemberBean;
import static db.jdbcUtil.*;

//DAO (Database Access Object)
public class MemberDAO {
	public static MemberDAO instance;
	Connection con;
	PreparedStatement pstmt;
	PreparedStatement pstmt_c;
	ResultSet rs;
	DataSource ds;
	
	private MemberDAO() {
		
	}
	public static MemberDAO getInstance() {
		if(instance == null) {
			instance = new MemberDAO();
		}
		return instance;
	}
	public void setConnection(Connection con) {
		this.con = con;
	}
	public String selectLoginId(MemberBean member) {
		//�Ѱܹ��� member ��ü�� id,pw �� ���� sql�� �̿��Ͽ� �ش� id �� ��ȸ
		//��ȸ�� id ���� loginId �� ����.
		//loginId �� ������.
			String loginId = null;
		
		try {
			pstmt = con.prepareStatement(
					"SELECT * FROM MEMBER WHERE Member_ID = ? AND Member_PW = ? ");
			pstmt.setString(1, member.getMember_id());
			pstmt.setString(2, member.getMember_pw());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//DB���� ��ȸ�� ID�� �����ͼ� loginId �� ����
				loginId = rs.getString("member_id");
			}
			
		}catch (Exception e) {
			//exception ����ó��
			System.out.println("���� : "+ e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return loginId;
	}
	public int insertMember(MemberBean member) {
		String sql = "INSERT INTO MEMBER (MEMBER_NUM,MEMBER_ID,MEMBER_PW,MEMBER_NAME,MEMBER_AGE,MEMBER_GENDER,MEMBER_EMAIL,MEMBER_ADDR,MEMBER_SKIN,MEMBER_GRADE) VALUES (?,?,?,?,?,?,?,?,?,'�Ϲ�') ";
		String sql_c = "select member_num from member";
		
		int insertCount = 0;
		int num_count = 1;
		
		try {
			
			pstmt = con.prepareStatement(sql);
			pstmt_c = con.prepareStatement(sql_c);
			rs = pstmt_c.executeQuery();
			
			while(rs.next()) {
				if(num_count == rs.getInt("member_num")) {
					num_count++;
				}
			}
			
			System.out.println(num_count);
			pstmt.setInt(1, num_count);
			pstmt.setString(2, member.getMember_id());
			pstmt.setString(3, member.getMember_pw());
			pstmt.setString(4, member.getMember_name());
			pstmt.setInt(5, member.getMember_age());
			pstmt.setString(6, member.getMember_gender());
			pstmt.setString(7, member.getMember_email());
			pstmt.setString(8, member.getMember_addr());
			pstmt.setString(9, member.getMember_skin());
			
			insertCount = pstmt.executeUpdate();
			
		}catch (Exception e) {
		
			e.printStackTrace();
		}finally {
			close(pstmt);
			close(pstmt_c);
			close(rs);
		}
		return insertCount;
	}
	
	public ArrayList<MemberBean> selectMemberList() {
		String sql = "SELECT * FROM MEMBER";
		ArrayList<MemberBean> memberList = new ArrayList<MemberBean>();
		MemberBean mb = null;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					mb = new MemberBean();
					
					mb.setMember_id(rs.getString("Member_ID"));
					mb.setMember_pw(rs.getString("Member_pw"));
					mb.setMember_name(rs.getString("Member_name"));
					mb.setMember_age(rs.getInt("Member_age"));
					mb.setMember_gender(rs.getString("Member_gender"));
					mb.setMember_email(rs.getString("Member_email"));
					mb.setMember_addr(rs.getString("Member_addr"));
					mb.setMember_skin(rs.getString("Member_skin"));
					mb.setMember_grade(rs.getString("Member_grade"));
				
					
					memberList.add(mb);
					
				}while(rs.next());
			}
			
		}catch (Exception e) {
			
			System.out.println("getDeatilMember ���� : " + e);
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return memberList;
	}
	public MemberBean selectMember(String id) {
		
		String sql = "SELECT * FROM MEMBER WHERE Member_ID = ?";
		MemberBean mb = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				mb = new MemberBean();
				mb.setMember_id(rs.getString("member_id"));
				mb.setMember_pw(rs.getString("member_pw"));
				mb.setMember_name(rs.getString("member_name"));
				mb.setMember_age(rs.getInt("member_age"));
				mb.setMember_gender(rs.getString("member_gender"));
				mb.setMember_email(rs.getString("member_email"));
				mb.setMember_addr(rs.getString("member_addr"));
				mb.setMember_skin(rs.getString("member_skin"));
				mb.setMember_grade(rs.getString("member_grade"));
				mb.setMember_photo(rs.getString("member_photo"));
			}
			
			
		}catch(Exception e) {
			System.out.println("getDeatilMember ���� : " + e);
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return mb;		
	}
	//���������� ���� ���
	public int changePhoto(MemberBean member){

		PreparedStatement pstmt = null;
		
		
		String sql="";
		int changeCount=0;

		try{
			

			sql="update member set member_photo =? where member_id =?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getMember_photo());
			pstmt.setString(2, member.getMember_id());
			
			
			
			changeCount=pstmt.executeUpdate();

		}catch(Exception ex){
			System.out.println("memberPhotochange ���� : "+ex);
		}finally{
			
			close(pstmt);
		}

		return changeCount;

	}
	//���� ���� ����
	public MemberBean selectPhoto(String id){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberBean memberBean = null;

		try{
			pstmt = con.prepareStatement(
					"select * from member where MEMBER_ID = ?");
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();

			if(rs.next()){
				memberBean = new MemberBean();
				memberBean.setMember_id(rs.getString("member_id"));
				memberBean.setMember_pw(rs.getString("member_pw"));
				memberBean.setMember_name(rs.getString("member_name"));
				memberBean.setMember_age(rs.getInt("member_age"));
				memberBean.setMember_gender(rs.getString("member_gender"));
				memberBean.setMember_email(rs.getString("member_email"));
				memberBean.setMember_addr(rs.getString("member_addr"));
				memberBean.setMember_skin(rs.getString("member_skin"));
				memberBean.setMember_grade(rs.getString("member_grade"));
				memberBean.setMember_photo(rs.getString("member_photo"));
			}
		}catch(Exception ex){
			System.out.println("selectPhoto ���� : " + ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return memberBean;

	}
	//���� ��� �ο�
	public int gruntMember(String id,String grade) {

		String sql = "update member set member_grade =? where member_id =?";
		
		int gruntCount = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, grade);
			pstmt.setString(2, id);
			
			
			gruntCount = pstmt.executeUpdate();
						
		}catch(Exception e) {
			System.out.println("getDeatilMember ���� : " + e);
			e.printStackTrace();
		}finally {
			
			close(pstmt);
		}
		return gruntCount;		
	}
	// ���� ���� ����
	
	public int modifyMember(String id,String pw,String addr,String skin) {
		
		
		int modifyCount = 0;
		MemberBean mb = null;
		
		String sql = "select * from member where member_id = ?";
		
		String sql1 = "update member set member_pw =? where member_id =?";
		
		String sql2 = "update member set member_addr =? where member_id =?";
		
		String sql3 = "update member set member_skin =? where member_id =?";
		
		
		try {
			pstmt_c = con.prepareStatement(sql);
			pstmt_c.setString(1, id);
			rs = pstmt_c.executeQuery();
			
			System.out.println(pw);
			System.out.println(addr);
			System.out.println(skin);
			
			
			
			while(rs.next()){
				mb = new MemberBean();
				mb.setMember_pw(rs.getString("member_pw"));
				mb.setMember_addr(rs.getString("member_addr"));
				mb.setMember_skin(rs.getString("member_skin"));
			}
			
			if(pw != "") {
				pstmt = con.prepareStatement(sql1);
				pstmt.setString(1, pw);
				pstmt.setString(2, id);
				modifyCount = pstmt.executeUpdate();
			}
			
			
			if(addr != "") {
				pstmt = con.prepareStatement(sql2);
				pstmt.setString(1, addr);
				pstmt.setString(2, id);
				modifyCount = pstmt.executeUpdate();
			}
			
			
			if(skin != null) {
				pstmt = con.prepareStatement(sql3);
				pstmt.setString(1, skin);
				pstmt.setString(2, id);
				modifyCount = pstmt.executeUpdate();
			}else {
				modifyCount = 1;
			}
			
			
						
		}catch(Exception e) {
			System.out.println("getmodifyMember ���� : " + e);
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt_c);
			close(pstmt);
		}
		return modifyCount;		
	}
	
	public int deleteMember(String id) {
		
		String sql = "DELETE FROM MEMBER WHERE Member_ID = ?";
		
		int deleteCount = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			deleteCount = pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			System.out.println("getDeleteMember ���� : " + e);
			e.printStackTrace();
		}finally {
			
			close(pstmt);
		}
		return deleteCount;		
	}
}