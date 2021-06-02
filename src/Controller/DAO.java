package Controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
	private Connection connection;
	
	public Connection getConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://localhost:1433;databaseName=QLTDien;";
			connection= DriverManager.getConnection(url,"sa","sa");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return connection;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DAO database= new DAO();
		database.getConnection();
	}

}
