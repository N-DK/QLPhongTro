package connectDatabase;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
	public static Connection connect() {
		Connection con = null;
		try {
<<<<<<< HEAD
			
			String url = "jdbc:sqlserver://localhost:1433;databaseName=QLPhongTro;encrypt=false";
			String user = "user";
=======
			String url = "jdbc:sqlserver://localhost:1433;databaseName=QLPhongTro;encrypt=false";
			String user = "sa";
>>>>>>> 7dec6fc5f219c28b7de406250d9b4ebe5f4dc059
			String password = "123";
			con = DriverManager.getConnection(url, user, password);
			System.out.println("connect");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
