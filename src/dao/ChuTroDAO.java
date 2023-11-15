package dao;

import static connectDatabase.Main.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.ChuPhong;

public class ChuTroDAO {

	public List<ChuPhong> findAll() {
		List<ChuPhong> results = new ArrayList<ChuPhong>();
		Connection con = connect();
		try {
			Statement myStmt = con.createStatement();
			ResultSet myRs = myStmt.executeQuery("{call findAll(chuPhong)}");
			while (myRs.next()) {
				ChuPhong chuPhong = new ChuPhong(myRs.getString(1), myRs.getString(2), myRs.getString(3),
						myRs.getString(4), myRs.getString(5), myRs.getDate(6), myRs.getInt(7));
				results.add(chuPhong);
			}
		} catch (Exception e) {
//			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return results;
	}

	public ChuPhong findOneById(String maPhong) {
		ChuPhong results = null;
		Connection con = connect();
		try {
			Statement myStmt = con.createStatement();
			ResultSet myRs = myStmt.executeQuery("{call findOneById(chuPhong," + maPhong + ")}");
			while (myRs.next()) {
				results = new ChuPhong(myRs.getString(1), myRs.getString(2), myRs.getString(3), myRs.getString(4),
						myRs.getString(5), myRs.getDate(6), myRs.getInt(7));
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
		return results;
	}

	public boolean save(ChuPhong chuPhong, String type) {
		List<ChuPhong> list = findAll();
		if (type.equals("insert")) {
			if (list.contains(chuPhong)) {
				return false;
			}
		}
		String SQL = "{call saveChuPhong(?,?,?,?,?,?,?,?)}}";
		Connection con = connect();
		try {
			PreparedStatement pstms = con.prepareStatement(SQL);
			pstms.setString(1, type);
			pstms.setString(2, chuPhong.getMaChuPhong());
			pstms.setString(3, chuPhong.getHo());
			pstms.setString(4, chuPhong.getTen());
			pstms.setString(5, chuPhong.getSdt());
			pstms.setString(6, chuPhong.getDiaChi());
			pstms.setDate(7, new java.sql.Date(chuPhong.getNgaySinh().toInstant().toEpochMilli()));
			pstms.setInt(8, chuPhong.getGioiTinh());
			pstms.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

		return true;
	}

	public boolean deleteOneById(String maChuPhong) {
		String SQL = "{call deleteOneById(chuPhong," + maChuPhong + ")}";
		Connection con = connect();
		try {
			PreparedStatement pstms = con.prepareStatement(SQL);
			pstms.executeUpdate();
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
