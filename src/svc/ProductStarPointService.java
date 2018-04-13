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
		//DAO ȣ�� ����� ���� ����
		//insert ������ �����ϸ� 1�̻��� ���� ���� �ް� insert�� ����� ���� ������
		//0�� ���� �ް� ��. ���� �޴� ���� ���� ����, ���� ���θ� �Ǵ���.
		
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
		//DAO ȣ�� ����� ���� ����
		//insert ������ �����ϸ� 1�̻��� ���� ���� �ް� insert�� ����� ���� ������
		//0�� ���� �ް� ��. ���� �޴� ���� ���� ����, ���� ���θ� �Ǵ���.
		
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
