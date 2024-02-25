package conn_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
	private static final String url = "jdbc:postgresql://Localhost:5432/posjava";
	private static final String password = "admin";
	private static final String user = "postgres";
	private static Connection conn = null;
	
	
	static {
		conn();
	}
	
	public SingleConnection() {
		conn();
	}
	
	
	private static void conn() {
		try {
			if(conn == null) {
				Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection(url, user, password);
				conn.setAutoCommit(false);
				System.out.println("it´s working");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return conn;
	}

}
