package svc;

import static db.jdbcUtil.close;
import static db.jdbcUtil.commit;
import static db.jdbcUtil.getConnection;
import static db.jdbcUtil.rollback;

import java.sql.Connection;

import dao.ProductDAO;

public class ProductStarPointService {
	public boolean ProductStarPoint(String login_id,double starpoint, int product_num, String productType) throws Exception{
		// TODO Auto-generated method stub
		
		boolean updateSucess = false;
		ProductDAO productDAO = ProductDAO.getInstance();
		Connection con = getConnection();
		productDAO.setConnection(con);
		//DAO 호출 결과를 담을 변수
		//insert 쿼리가 성공하면 1이상의 값을 리턴 받고 insert가 제대로 되지 않으면
		//0을 리턴 받게 됨. 리턴 받는 값을 통해 성공, 실패 여부를 판단함.
		
		int updateCount = productDAO.updateStarPoint(login_id,starpoint,product_num,productType);
		if(updateCount > 0 ) {
			updateSucess = true;
			commit(con);
		}
		else {
			rollback(con);
		}
		
		close(con);
		return updateSucess;
		
	}
	public boolean starPointAVG(int product_num, String productType) throws Exception{
		// TODO Auto-generated method stub
		
		boolean insertSucess = false;
		ProductDAO productDAO = ProductDAO.getInstance();
		Connection con = getConnection();
		productDAO.setConnection(con);
		//DAO 호출 결과를 담을 변수
		//insert 쿼리가 성공하면 1이상의 값을 리턴 받고 insert가 제대로 되지 않으면
		//0을 리턴 받게 됨. 리턴 받는 값을 통해 성공, 실패 여부를 판단함.
		
		int insertCheck = productDAO.starPointCalculate(product_num, productType);
		
		if(insertCheck > 0 ) {
			insertSucess = true;
			commit(con);
		}
		else {
			rollback(con);
		}
		
		close(con);
		return insertSucess;
		
	}
	
}
