package dao;

import static connectDatabase.Main.connect;
import connectDatabase.Main;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import entity.ChuPhong;

public class ChuTroDAO {

	ChuPhong cp;

	public ChuTroDAO() {
		cp = new ChuPhong();
	}

	public boolean themList(ChuPhong cp) {
		ArrayList<ChuPhong> ds = docTuDTB();
		if (ds.contains(cp)) {
			return false;
		}
		return true;
	}

	// ============= Add findAll
	public List<ChuPhong> findAll() {
		List<ChuPhong> results = new ArrayList<ChuPhong>();
		Connection con = connect();
		try {
			Statement myStmt = con.createStatement();
			ResultSet myRs = myStmt.executeQuery("{call findAll(chuPhong)}");
			while (myRs.next()) {
				ChuPhong chuPhong = new ChuPhong(myRs.getString(1), myRs.getString(2), myRs.getString(3), myRs.getString(4), myRs.getString(5), myRs.getDate(6), Integer.parseInt(myRs.getString(7)));
				results.add(chuPhong);
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
	// =============

	// ============= Add findOneById
	public ChuPhong findOneById(String maChuPhong) {
		ChuPhong results = null;
		Connection con = connect();
		try {
			Statement myStmt = con.createStatement();
			ResultSet myRs = myStmt.executeQuery("{call findOneById(chuPhong)}");
			while (myRs.next()) {
				results = new ChuPhong(myRs.getString(1), myRs.getString(2), myRs.getString(3), myRs.getString(4),
						myRs.getString(5), myRs.getDate(6), Integer.parseInt(myRs.getString(7)));
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
	// ===============
	
	// =============== Add save
	// ===============

	public boolean them(ChuPhong cp) {

		Connection con = Main.connect();
		PreparedStatement stmt = null;
		int n = 0;
		try {

			stmt = con.prepareStatement("{call themChuPhong(?,?,?,?,?,?,?)}");
			stmt.setString(1, cp.getMaChuPhong());
			stmt.setString(2, cp.getHo());
			stmt.setString(3, cp.getTen());
			stmt.setString(4, cp.getSdt());
			stmt.setString(5, cp.getDiaChi());
			stmt.setDate(6, new java.sql.Date(cp.getNgaySinh().toInstant().toEpochMilli()));

			stmt.setInt(7, cp.getGioiTinh());

			n = stmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return true;
	}

	public ArrayList<ChuPhong> docTuDTB() {
		ArrayList<ChuPhong> ds = new ArrayList<ChuPhong>();
		try {

			Connection con = Main.connect();
			String sql = "{call layBangChuPhong}";

			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String ma = rs.getString(1);
				String ho = rs.getString(2);
				String ten = rs.getString(3);
				String sdt = rs.getString(4);
				String diachi = rs.getString(5);
				Date ngaysinh = rs.getDate(6);
				int gioitinh = rs.getInt(7);
				cp = new ChuPhong(ma, ho, ten, sdt, diachi, ngaysinh, gioitinh);
				ds.add(cp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}

	public boolean delete(String ma) {
		Connection con = Main.connect();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("{call xoaChuPhong(?)}");
			stmt.setString(1, ma);
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return n > 0;
	}

	public boolean updateChuPhong(ChuPhong cp) {
		Connection con = Main.connect();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("update ChuPhong " + "set Ho =?," + "Ten = ?," + "Sdt = ?," + "DiaChi = ?,"
					+ "NgaySinh=?," + "GioiTinh = ?" + "where MaChuPhong=?");

			stmt.setString(1, cp.getHo());
			stmt.setString(2, cp.getTen());
			stmt.setString(3, cp.getSdt());
			stmt.setString(4, cp.getDiaChi());
			stmt.setDate(5, new java.sql.Date(cp.getNgaySinh().toInstant().toEpochMilli()));
			stmt.setInt(6, cp.getGioiTinh());
			stmt.setString(7, cp.getMaChuPhong());
			System.out.println("d");
			n = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return n > 0;
	}
}
