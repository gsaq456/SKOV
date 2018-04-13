package db;

import java.sql.Connection;
import java.sql.*;
import javax.naming.*;
import javax.sql.DataSource;


//DB와 관련된 작업을 모아놓은 클래스
public class jdbcUtil {
	
	public static Connection getConnection() {
		Connection con = null;
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			//""안의 내용은 context.xml에 있는 name의 값
			DataSource ds = (DataSource)envCtx.lookup("jdbc/OracleDB");
			con = ds.getConnection();
			con.setAutoCommit(false);
			System.out.println("connect success");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void close(Connection con) {
		try {
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void close (Statement stmt) {
		try {
			stmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close (ResultSet rs) {
		try {
			rs.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void commit(Connection con) {
		try {
			con.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}public static void rollback(Connection con) {
		try {
			con.rollback();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
