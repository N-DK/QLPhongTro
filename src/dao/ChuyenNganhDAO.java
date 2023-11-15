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
import entity.Khoa;

public class ChuyenNganhDAO {
	private KhoaDAO khoaDAO = new KhoaDAO();

	public List<ChuyenNganh> findAll() {
		List<ChuyenNganh> resutls = new ArrayList<ChuyenNganh>();
		Connection con = connect();
		try {
			Statement myStmt = con.createStatement();
			ResultSet myRs = myStmt.executeQuery("{call findAll(chuyenNganh)}");
			while (myRs.next()) {
				Khoa khoa = khoaDAO.findOneById(myRs.getString(3));
				ChuyenNganh chuyenNganh = new ChuyenNganh(myRs.getString(1), myRs.getString(2), khoa);
				resutls.add(chuyenNganh);
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

	public ChuyenNganh findOneById(String maCN) {
		ChuyenNganh resutls = null;
		Connection con = connect();
		try {
			Statement myStmt = con.createStatement();
			ResultSet myRs = myStmt.executeQuery("{call findOneById(chuyenNganh," + maCN + ")}");
			while (myRs.next()) {
				Khoa khoa = khoaDAO.findOneById(myRs.getString(3));
				resutls = new ChuyenNganh(myRs.getString(1), myRs.getString(2), khoa);
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

	public boolean save(ChuyenNganh chuyenNganh, String type) {
		List<ChuyenNganh> list = findAll();
		if (type.equals("insert")) {
			if (list.contains(chuyenNganh)) {
				return false;
			}
		}
		String SQL = "{call saveChuyenNganh(?,?,?,?)}}";
		Connection con = connect();
		try {
			PreparedStatement pstms = con.prepareStatement(SQL);
			pstms.setString(1, type);
			pstms.setString(2, chuyenNganh.getMa());
			pstms.setString(3, chuyenNganh.getTen());
			pstms.setString(4, chuyenNganh.getKhoa().getMa());
			pstms.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

		return true;
	}

	public boolean deleteOneById(String macn) {
		String SQL = "{call deleteOneById(chuyenNganh," + macn + ")}";
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
