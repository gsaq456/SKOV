package dao;

import static db.jdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import vo.BokhapBean;
import vo.JisungBean;
import vo.ProductBean;
import vo.ReplyBean;
import vo.TroubleBean;

public class ProductDAO {
	
	DataSource ds;
	Connection con;
	private static ProductDAO productDAO;

	private ProductDAO() {
		// TODO Auto-generated constructor stub
	}

	public static ProductDAO getInstance(){
		if(productDAO == null){
			productDAO = new ProductDAO();
		}
		return productDAO;
	}

	public void setConnection(Connection con){
		this.con = con;
	}
	
	//글의 개수 구하기.
		public int selectListCount(String type) {

			int listCount= 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try{
				System.out.println("getConnection");
				if(type.equals("건성")) {
				pstmt=con.prepareStatement("select count(*) from product");
				}
				else if(type.equals("지성")) {
					pstmt=con.prepareStatement("select count(*) from jisung_product");
					}
				else if(type.equals("복합성")) {
					pstmt=con.prepareStatement("select count(*) from bokhap_product");
					}
				else if(type.equals("트러블")) {
					pstmt=con.prepareStatement("select count(*) from trouble_product");
					}
				
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
	public ArrayList<ProductBean> selectArticleList(int page,int limit){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String product_list_sql="select * from product where product_num between ? and ?";
		ArrayList<ProductBean> productList = new ArrayList<ProductBean>();
		ProductBean product = null;
		int startpage=((page-1)*4)+1; //읽기 시작할 row 번호..	
		int endpage = page * 4;
		try{
			pstmt = con.prepareStatement(product_list_sql);
			pstmt.setInt(1, startpage);
			pstmt.setInt(2, endpage);
			rs = pstmt.executeQuery();

			while(rs.next()){
				
				product = new ProductBean();
				product.setProduct_num(rs.getInt("product_num"));
				product.setPicture(rs.getString("picture"));
				product.setProduct_id(rs.getString("product_id"));
				product.setProduct_pr(rs.getString("product_pr"));
				productList.add(product);
			}

		}catch(Exception ex){
			System.out.println("getProductList 에러 : " + ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return productList;

	}
	
	//글 내용 보기.	
	public ProductBean ProductDetail(int product_num){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String product_list_sql="select * from product where Product_num = ? ";
		
		ProductBean product = null;
	
		try{
			pstmt = con.prepareStatement(product_list_sql);
			pstmt.setInt(1, product_num);
			rs = pstmt.executeQuery();

			while(rs.next()){
				product = new ProductBean();
				product.setProduct_num(rs.getInt("product_num"));
				product.setProduct_id(rs.getString("product_id"));
				product.setProduct_type(rs.getString("product_type"));
				product.setProduct_info(rs.getString("product_info"));
				product.setProduct_pr(rs.getString("product_pr"));	
				product.setPicture(rs.getString("picture"));
				product.setStarpoint(rs.getDouble("starpoint"));
				
			}
				
			
		}catch(Exception ex){
		
		}finally{
			close(rs);
			close(pstmt);
		}
				
		return product;

	}
	// 별점 등록
	public int updateStarPoint(String login_id, double starpoint, int product_num, String productType){

		PreparedStatement pstmt = null;
		
		String product_starpoint_sql="INSERT INTO pr_star VALUES (?,?,?,?)";
		
		int updateresult = 0;
	
		try{
			pstmt = con.prepareStatement(product_starpoint_sql);
			pstmt.setString(1, login_id);
			pstmt.setDouble(2, starpoint);
			pstmt.setInt(3, product_num);
			pstmt.setString(4, productType);
			
			updateresult = pstmt.executeUpdate();
				
			
		}catch(Exception ex){
			System.out.println("별점 추가에러 " + ex);
		
		}finally{
			close(pstmt);
		}
				
		return updateresult;

	}
	// 별점 계산
	public int starPointCalculate(int product_num, String productType){

		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		
		String select_starpoint_sql="select starpoint from pr_star where star_pr_NUM = ? and star_pr_TYPE = ? ";
		String insert_starpoint_sql = "";
		
		if(productType.equals("건성")) {
			insert_starpoint_sql = "update product set starpoint = ? where product_num = ? and product_Type = ?";
		}
		else if(productType.equals("지성")) {
			insert_starpoint_sql = "update jisung_product set starpoint = ? where product_num = ? and product_Type = ?";
		}
		else if(productType.equals("복합성")) {
			insert_starpoint_sql = "update bokhap_product set starpoint = ? where product_num = ? and product_Type = ?";
		}
		else if(productType.equals("트러블")){
			insert_starpoint_sql = "update trouble_product set starpoint = ? where product_num = ? and product_Type = ?";
		}
		
		int updateresult = 0;
		int sum = 0;
		int count = 0;
		
		try{
			pstmt = con.prepareStatement(select_starpoint_sql);
			
			pstmt.setInt(1, product_num);
			pstmt.setString(2, productType);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				sum += rs.getInt("starpoint");			
				count++;
			}
			
			double AVG = (double)sum/count;
			System.out.println("평균값 계산 ? " + AVG);
			
			pstmt1 = con.prepareStatement(insert_starpoint_sql);
			
			pstmt1.setDouble(1, AVG);
			pstmt1.setInt(2, product_num);
			pstmt1.setString(3, productType);
			
			updateresult = pstmt1.executeUpdate();
			
			
			
		}catch(Exception ex){
			System.out.println("별점 계산에러 " + ex);
		
		}finally{
			close(pstmt);
			close(pstmt1);
			close(rs);
		}
				
		return updateresult;

	}
	//제품 댓글 등록
	public int insertReply(ReplyBean replyBean,ProductBean productBean){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num =0;
		String sql="";
		int insertCount=0;

		try{
			pstmt=con.prepareStatement("select max(coment_num) from pr_coment");
			rs = pstmt.executeQuery();

			if(rs.next())
				num =rs.getInt(1)+1;
			else
				num=1;

			sql="insert into pr_coment (coment_num,coment_id,coment_content,coment_date,PRODUCT_TYPE,PRODUCT_NUM) values(?,?,?,sysdate,?,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, replyBean.getM_ID());
			pstmt.setString(3, replyBean.getFB_REPLY());
			pstmt.setString(4, productBean.getProduct_type());
			pstmt.setInt(5, productBean.getProduct_num());
			
			insertCount=pstmt.executeUpdate();

		}catch(Exception ex){
			System.out.println("Product Reply Insert 에러 : " + ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return insertCount;

	}
	// 댓글 찾기
	public ArrayList<ReplyBean> selectReplylist(ProductBean productBean){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String product_list_sql="SELECT coment_num, coment_id, coment_content, coment_date FroM (select * from pr_coment order by coment_num asc )where product_num = ? and product_type = ?";
		ArrayList<ReplyBean> articleList = new ArrayList<ReplyBean>();
		ReplyBean replyBean = null;
		
		try{
			pstmt = con.prepareStatement(product_list_sql);
			System.out.println(productBean.getProduct_num());
			System.out.println(productBean.getProduct_type());
			
			pstmt.setInt(1, productBean.getProduct_num());
			pstmt.setString(2, productBean.getProduct_type());
			
			rs = pstmt.executeQuery();

			while(rs.next()){
				replyBean = new ReplyBean();
				replyBean.setFB_REPLY(rs.getString("coment_content"));
				replyBean.setM_ID(rs.getString("coment_id"));
				replyBean.setREPLY_DATE(rs.getDate("coment_date"));
				
				articleList.add(replyBean);
			}

		}catch(Exception ex){
			System.out.println("getReply product 에러 : " + ex);
		}finally{
			close(rs);
			close(pstmt);
		}

		return articleList;

	}
	//지성 목록 보기.	
		public ArrayList<JisungBean> selectArticleList2(int page,int limit){

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String product_list_sql="select * from jisung_product where product_num between ? and ?";
			ArrayList<JisungBean> jisungList = new ArrayList<JisungBean>();
			JisungBean jisung = null;
			int startpage=((page-1)*4)+1; //읽기 시작할 row 번호..	
			int endpage = page * 4;
			try{
				pstmt = con.prepareStatement(product_list_sql);
				pstmt.setInt(1, startpage);
				pstmt.setInt(2, endpage);
				rs = pstmt.executeQuery();

				while(rs.next()){
					
					jisung = new JisungBean();
					jisung.setProduct_num(rs.getInt("product_num"));
					jisung.setJisung_picture(rs.getString("jisung_picture"));
					jisung.setProduct_id(rs.getString("product_id"));
					jisung.setProduct_pr(rs.getString("product_pr"));
					jisungList.add(jisung);
				}

			}catch(Exception ex){
				System.out.println("getProductList 에러 : " + ex);
			}finally{
				close(rs);
				close(pstmt);
			}

			return jisungList;

		}
		
		//복합성 목록 보기.	
			public ArrayList<BokhapBean> selectArticleList3(int page,int limit){

				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String product_list_sql="select * from bokhap_product where product_num between ? and ?";
				ArrayList<BokhapBean> bokhapList = new ArrayList<BokhapBean>();
				BokhapBean bokhap = null;
				int startpage=((page-1)*4)+1; //읽기 시작할 row 번호..	
				int endpage = page * 4;
				try{
					pstmt = con.prepareStatement(product_list_sql);
					pstmt.setInt(1, startpage);
					pstmt.setInt(2, endpage);
					rs = pstmt.executeQuery();

					while(rs.next()){
						
						bokhap = new BokhapBean();
						bokhap.setProduct_num(rs.getInt("product_num"));
						bokhap.setBokhap_picture(rs.getString("bokhap_picture"));
						bokhap.setProduct_id(rs.getString("product_id"));
						bokhap.setProduct_pr(rs.getString("product_pr"));
						bokhapList.add(bokhap);
					}

				}catch(Exception ex){
					System.out.println("getProductList 에러 : " + ex);
				}finally{
					close(rs);
					close(pstmt);
				}

				return bokhapList;

			}
			
		//트러블 목록 보기.	
			public ArrayList<TroubleBean> selectArticleList4(int page,int limit){

				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String product_list_sql="select * from trouble_product where product_num between ? and ?";
				ArrayList<TroubleBean> troubleList = new ArrayList<TroubleBean>();
				TroubleBean trouble = null;
				int startpage=((page-1)*4)+1; //읽기 시작할 row 번호..	
				int endpage = page * 4;
				try{
					pstmt = con.prepareStatement(product_list_sql);
					pstmt.setInt(1, startpage);
					pstmt.setInt(2, endpage);
					rs = pstmt.executeQuery();

					while(rs.next()){
								
						trouble = new TroubleBean();
						trouble.setProduct_num(rs.getInt("product_num"));
						trouble.setTrouble_picture(rs.getString("trouble_picture"));
						trouble.setProduct_id(rs.getString("product_id"));
						trouble.setProduct_pr(rs.getString("product_pr"));
						troubleList.add(trouble);
					}

				}catch(Exception ex){
					System.out.println("getProductList 에러 : " + ex);
				}finally{
					close(rs);
					close(pstmt);
				}

				return troubleList;

			}
			
		//지성 내용 보기.	
		public JisungBean JisungDetail(int product_num){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String product_list_sql="select * from jisung_product where Product_num = ? ";
				
		JisungBean jisung = null;
			
		try{
			pstmt = con.prepareStatement(product_list_sql);
			pstmt.setInt(1, product_num);
			rs = pstmt.executeQuery();

			while(rs.next()){
				jisung = new JisungBean();
				jisung.setProduct_num(rs.getInt("product_num"));
				jisung.setProduct_id(rs.getString("product_id"));
				jisung.setProduct_type(rs.getString("product_type"));
				jisung.setProduct_info(rs.getString("product_info"));
				jisung.setProduct_pr(rs.getString("product_pr"));	
				jisung.setJisung_picture(rs.getString("jisung_picture"));
				jisung.setStarpoint(rs.getDouble("starpoint"));
						
			}
				
		}catch(Exception ex){
				
		}finally{
			close(rs);
			close(pstmt);
		}
						
		return jisung;

	}
			
	//복합성 내용 보기.	
	public BokhapBean BokhapDetail(int product_num){

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String product_list_sql="select * from bokhap_product where Product_num = ? ";
				
		BokhapBean bokhap = null;
			
		try{
			pstmt = con.prepareStatement(product_list_sql);
			pstmt.setInt(1, product_num);
			rs = pstmt.executeQuery();

			while(rs.next()){
				bokhap = new BokhapBean();
				bokhap.setProduct_num(rs.getInt("product_num"));
				bokhap.setProduct_id(rs.getString("product_id"));
				bokhap.setProduct_type(rs.getString("product_type"));
				bokhap.setProduct_info(rs.getString("product_info"));
				bokhap.setProduct_pr(rs.getString("product_pr"));	
				bokhap.setBokhap_picture(rs.getString("bokhap_picture"));
				bokhap.setStarpoint(rs.getDouble("starpoint"));
						
			}
				
		}catch(Exception ex){
				
		}finally{
			close(rs);
			close(pstmt);
		}
						
		return bokhap;

	}
			
			
	//트러블 내용 보기.	
			public TroubleBean TroubleDetail(int product_num){

				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String product_list_sql="select * from trouble_product where Product_num = ? ";
						
				TroubleBean trouble = null;
					
				try{
					pstmt = con.prepareStatement(product_list_sql);
					pstmt.setInt(1, product_num);
					rs = pstmt.executeQuery();

					while(rs.next()){
						trouble = new TroubleBean();
						trouble.setProduct_num(rs.getInt("product_num"));
						trouble.setProduct_id(rs.getString("product_id"));
						trouble.setProduct_type(rs.getString("product_type"));
						trouble.setProduct_info(rs.getString("product_info"));
						trouble.setProduct_pr(rs.getString("product_pr"));	
						trouble.setTrouble_picture(rs.getString("trouble_picture"));
						trouble.setStarpoint(rs.getDouble("starpoint"));
								
					}
						
				}catch(Exception ex){
				
				}finally{
					close(rs);
					close(pstmt);
				}
								
				return trouble;

			}
}
