package dao;

import static connectDatabase.Main.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.LopHoc;
import entity.SinhVien;

public class SinhVienDAO {

	private LopHocDAO lopDAO = new LopHocDAO();

	public List<SinhVien> findAll() {
		List<SinhVien> resutls = new ArrayList<SinhVien>();
		Connection con = connect();
		try {
			Statement myStmt = con.createStatement();
			ResultSet myRs = myStmt.executeQuery("{call findAll(sinhVien)}");
			while (myRs.next()) {
				LopHoc lop = lopDAO.findOneById(myRs.getString("MaLop"));
				SinhVien sinhVien = new SinhVien(myRs.getString("Ho"), myRs.getString("Ten"),
						Integer.parseInt(myRs.getString("GioiTinh")), myRs.getDate("NgaySinh"), myRs.getString("Sdt"),
						myRs.getString("MaSinhVien"), myRs.getString("QueQuan"), lop);
				resutls.add(sinhVien);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		return resutls;
	}

	public boolean insert(SinhVien sinhVien) {
		// TODO Auto-generated method stub
//		insertLopHoc(?,?,?) INSERT INTO ma, ten, gv values(?, ?, ?)
//		String SQL = "{call insertLopHoc(?,?,?)}";
//		Connection con = connect();
//		try {
//			PreparedStatement pstmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
//			pstmt.setString(1, clazz.getId());
//			pstmt.setString(2, clazz.getClassName());
//			pstmt.setString(3, clazz.getTeacher());
//			int affectedRows = pstmt.executeUpdate();
//			if (affectedRows > 0) {
//				try (ResultSet rs = pstmt.getGeneratedKeys()) {
//					if (rs.next()) {
//						System.out.println(rs.getLong(1));
//					}
//				} catch (SQLException ex) {
//					System.out.println(ex.getMessage());
//				}
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			System.out.println(e.getMessage());
//		}
		
		return true;
	}

	public void update(SinhVien sinhVien) {

	}

	public List<SinhVien> findBy(String masv, String ho, String ten, String maLop, String queQuan) {
		List<SinhVien> results = new ArrayList<SinhVien>();
		return results;
	}
}
