package connectDatabase;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
	public static Connection connect() {
		Connection con = null;
		try {

			
			String url = "jdbc:sqlserver://localhost:1433;databaseName=QLPhongTro;encrypt=false";
			String user = "user";

			String password = "123";
			con = DriverManager.getConnection(url, user, password);
			System.out.println("connect");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
