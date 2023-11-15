package dao;

import static connectDatabase.Main.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return resutls;
	}

	public boolean save(SinhVien sinhVien, String type) {
		List<SinhVien> list = findAll();
		if (type.equals("insert")) {
			if (list.contains(sinhVien)) {
				return false;
			}
		}
		String SQL = "{call saveSinhVien(?,?,?,?,?,?,?,?,?)}}";
		Connection con = connect();
		try {
			PreparedStatement pstms = con.prepareStatement(SQL);
			pstms.setString(1, type);
			pstms.setString(2, sinhVien.getMaSinhVien());
			pstms.setString(3, sinhVien.getHo());
			pstms.setString(4, sinhVien.getTen());
			pstms.setString(5, sinhVien.getLopHoc().getMa());
			pstms.setString(6, sinhVien.getQueQuan());
			pstms.setInt(7, sinhVien.getGioiTinh());
			pstms.setDate(8, new java.sql.Date(sinhVien.getNgaySinh().toInstant().toEpochMilli()));
			pstms.setString(9, sinhVien.getSdt());
			pstms.executeUpdate();
		} catch (Exception e) {
			return false;
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return true;
	}

	public boolean deleteOneById(String maSV) {
		String SQL = "{call deleteOneById(sinhVien," + maSV + ")}";
		Connection con = connect();
		try {
			PreparedStatement pstmt = con.prepareStatement(SQL);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			return false;
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	public List<SinhVien> findBy(String masv, String ho, String ten, String maLop, String queQuan, String maKhoa,
			String maCN) {
		List<SinhVien> results = new ArrayList<SinhVien>();
		Connection con = connect();
		String SQL = "{call findSinhVien(?,?,?,?,?,?,?)}";
		try {
			PreparedStatement pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, masv);
			pstmt.setString(2, ho);
			pstmt.setString(3, ten);
			pstmt.setString(4, maLop);
			pstmt.setString(5, queQuan);
			pstmt.setString(6, maKhoa);
			pstmt.setString(7, maCN);
			ResultSet myRs = pstmt.executeQuery();
			while (myRs.next()) {
				LopHoc lop = lopDAO.findOneById(myRs.getString("MaLop"));
				SinhVien sinhVien = new SinhVien(myRs.getString("Ho"), myRs.getString("Ten"),
						Integer.parseInt(myRs.getString("GioiTinh")), myRs.getDate("NgaySinh"), myRs.getString("Sdt"),
						myRs.getString("MaSinhVien"), myRs.getString("QueQuan"), lop);
				results.add(sinhVien);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return results;
	}
}
