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
import entity.PhongTro;

public class PhongTroDAO {
	private ChuTroDAO ctDAO = new ChuTroDAO();

	public List<PhongTro> findAll() {
		List<PhongTro> results = new ArrayList<PhongTro>();
		Connection con = connect();
		try {
			Statement myStmt = con.createStatement();
			ResultSet myRs = myStmt.executeQuery("{call findAll(phongTro)}");
			while (myRs.next()) {
				System.out.println(myRs.getString(4));
				ChuPhong chuPhong = ctDAO.findOneById(myRs.getString(2));
				PhongTro phongTro = new PhongTro(myRs.getString(1), myRs.getString(4), myRs.getFloat(3), chuPhong,
						myRs.getInt(5));
				results.add(phongTro);
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

	public PhongTro findOneById(String maPhong) {
		PhongTro results = null;
		Connection con = connect();
		try {
			Statement myStmt = con.createStatement();
			ResultSet myRs = myStmt.executeQuery("{call findOneById(phongTro," + maPhong + ")}");
			while (myRs.next()) {
				ChuPhong chuPhong = ctDAO.findOneById(myRs.getString(4));
				results = new PhongTro(myRs.getString(1), myRs.getString(2), Float.parseFloat(myRs.getString(3)),
						chuPhong, Integer.parseInt(myRs.getString(5)));
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

	public boolean save(PhongTro phongTro, String type) {
		List<PhongTro> list = findAll();
		if (type.equals("insert")) {
			if (list.contains(phongTro)) {
				return false;
			}
		} else {
			if (!list.contains(phongTro)) {
				return false;
			}
		}
		String SQL = "{call savePhongTro(?,?,?,?,?,?)}}";
		Connection con = connect();
		try {
			PreparedStatement pstms = con.prepareStatement(SQL);
			pstms.setString(1, type);
			pstms.setString(2, phongTro.getMaPhong());
			pstms.setString(3, phongTro.getChuPhong().getMaChuPhong());
			pstms.setFloat(4, phongTro.getGia());
			pstms.setString(5, phongTro.getDiaChi());
			pstms.setInt(6, phongTro.getTinhTrang());
			pstms.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

		return true;
	}

	public boolean deleteOneById(String maPhong) {
		String SQL = "{call deleteOneById(phongTro," + maPhong + ")}";
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
