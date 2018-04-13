package dao;

import static db.jdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.sun.scenario.effect.impl.prism.PrDrawable;

import vo.ProductBean;

public class SearchDAO {
	
	DataSource ds;
	Connection con;
	private static SearchDAO searchDAO;

	private SearchDAO() {
		// TODO Auto-generated constructor stub
	}

	public static SearchDAO getInstance(){
		if(searchDAO == null){
			searchDAO = new SearchDAO();
		}
		return searchDAO;
	}

	public void setConnection(Connection con){
		this.con = con;
	}
	
	public int selectListCount(String type,String searchid) {

		int listCount= 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			System.out.println("getConnection");
			if(type.equals("�Ǽ�")) {
			pstmt=con.prepareStatement("select count(*) from product where product_id like ?");
			}
			else if(type.equals("����")) {
				pstmt=con.prepareStatement("select count(*) from jisung_product where product_id like ?");
			}
			else if(type.equals("���ռ�")) {
				pstmt=con.prepareStatement("select count(*) from bokhap_product where product_id like ?");
			}
			else if(type.equals("Ʈ����")) {
				pstmt=con.prepareStatement("select count(*) from trouble_product where product_id like ?");
			}
			
			pstmt.setString(1, "%"+searchid+"%" );
			
			rs = pstmt.executeQuery();

			if(rs.next()){
				listCount=rs.getInt(1);
			}
		}catch(Exception ex){
			System.out.println("getListCount ����: " + ex);			
		}finally{
			close(rs);
			close(pstmt);
		}

		return listCount;

	}
	//�˻� ��� ����.	
	public ArrayList<ProductBean> searchSearchList(int page, String type, String searchid){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String psearchList_sql = "";
		if(type.equals("�Ǽ�")) {
			psearchList_sql="select * from (SELECT rownum rnum, product_num,product_id,product_type,product_info,product_pr,picture,starpoint FROM (SELECT * FroM product where product_id like ? )) where rnum between ? and ? ";
		}
		if(type.equals("����")) {
			psearchList_sql="select * from (SELECT rownum rnum, product_num,product_id,product_type,product_info,product_pr,jisung_picture,starpoint FROM (SELECT * FroM jisung_product where product_id like ? )) where rnum between ? and ? ";
		}
		if(type.equals("���ռ�")) {
			psearchList_sql="select * from (SELECT rownum rnum, product_num,product_id,product_type,product_info,product_pr,bokhap_picture,starpoint FROM (SELECT * FroM bokhap_product where product_id like ? )) where rnum between ? and ? ";
		}
		if(type.equals("Ʈ����")) {
			psearchList_sql="select * from (SELECT rownum rnum, product_num,product_id,product_type,product_info,product_pr,trouble_picture,starpoint FROM (SELECT * FroM trouble_product where product_id like ? )) where rnum between ? and ? ";
		}
		ArrayList<ProductBean> psearchList = new ArrayList<ProductBean>();
		ProductBean product = null;
		page = 1;
		int startpage=((page-1)*4)+1; //�б� ������ row ��ȣ..	
		int endpage = page * 4;
		String picture ="";
		
		try{
			pstmt = con.prepareStatement(psearchList_sql);
			pstmt.setString(1, "%"+searchid+"%");
			pstmt.setInt(2,startpage);
			pstmt.setInt(3, endpage);
			
			rs = pstmt.executeQuery();
			
			
			
			while(rs.next()){
				
				product = new ProductBean();
				product.setProduct_num(rs.getInt("product_num"));
				product.setProduct_id(rs.getString("product_id"));
				product.setProduct_info(rs.getString("product_info"));
				product.setProduct_type(rs.getString("product_type"));
				product.setProduct_pr(rs.getString("product_pr"));
				if(type.equals("�Ǽ�")) {
					picture = rs.getString("picture");
				}
				else if (type.equals("����")) {
					picture = rs.getString("jisung_picture");
				}
				else if(type.equals("���ռ�")) {
					picture = rs.getString("bokhap_picture");
				}
				else if(type.equals("Ʈ����")) {
					picture = rs.getString("trouble_picture");
				}
				product.setPicture(picture);
				product.setStarpoint(rs.getInt("starpoint"));
				psearchList.add(product);
			}

		}catch(Exception ex){
			System.out.println("getPsearchList ���� : " + ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return psearchList;

	}

}
