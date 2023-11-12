package dao;

//import static connectDatabase.Main.connect;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//import entity.HopDong;
//import entity.Khoa;

public class HopDongDAO {
//	public List<HopDong> findAll() {
//		List<HopDong> resutls = new ArrayList<HopDong>();
//		Connection con = connect();
//		try {
//			Statement myStmt = con.createStatement();
//			ResultSet myRs = myStmt.executeQuery("{call findAll(khoa)}");
//			while (myRs.next()) {
//				Khoa khoa = new Khoa(myRs.getString(1), myRs.getString(2));
//				resutls.add(khoa);
//			}
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		} finally {
//			try {
//				if (con != null) {
//					con.close();
//				}
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//		}
//
//		return resutls;
//	}
}
