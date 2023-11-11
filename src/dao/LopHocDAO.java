package dao;

import static connectDatabase.Main.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.ChuyenNganh;
import entity.LopHoc;

public class LopHocDAO {
	private ChuyenNganhDAO cnDAO = new ChuyenNganhDAO();

	public List<LopHoc> findAll() {
		List<LopHoc> resutls = new ArrayList<LopHoc>();
		Connection con = connect();
		try {
			Statement myStmt = con.createStatement();
			ResultSet myRs = myStmt.executeQuery("{call findAll(lop)}");
			while (myRs.next()) {
				ChuyenNganh chuyenNganh = cnDAO.findOneById(myRs.getString(4));
				LopHoc lop = new LopHoc(myRs.getString(1), myRs.getString(2), myRs.getString(3), chuyenNganh);
				resutls.add(lop);
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

	public LopHoc findOneById(String maLop) {
		LopHoc resutls = null;
		Connection con = connect();
		try {
			Statement myStmt = con.createStatement();
			ResultSet myRs = myStmt.executeQuery("{call findOneById(lop," + maLop + ")}");
			while (myRs.next()) {
				ChuyenNganh chuyenNganh = cnDAO.findOneById(myRs.getString(4));
				resutls = new LopHoc(myRs.getString(1), myRs.getString(2), myRs.getString(3), chuyenNganh);
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

	public boolean save(LopHoc lop, String type) {
		List<LopHoc> list = findAll();
		if (type.equals("insert")) {
			if (list.contains(lop)) {
				return false;
			}
		} else {
			if (!list.contains(lop)) {
				System.out.println(type);
				return false;
			}
		}
		String SQL = "{call saveLop(?,?,?,?,?)}}";
		Connection con = connect();
		try {
			PreparedStatement pstms = con.prepareStatement(SQL);
			pstms.setString(1, type);
			pstms.setString(2, lop.getMa());
			pstms.setString(3, lop.getTen());
			pstms.setString(4, lop.getGvcn());
			pstms.setString(5, lop.getChuyenNganh().getMa());
			pstms.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

		return true;
	}
	
	public boolean deleteOneById(String maLop) {
		String SQL = "{call deleteOneById(lop," + maLop + ")}";
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
				System.out.println(e.getMessage());
			}
		}
		return true;
	}
}
