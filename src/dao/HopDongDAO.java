package dao;

import static connectDatabase.Main.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.HopDong;
import entity.PhongTro;
import entity.SinhVien;

public class HopDongDAO {
	private PhongTroDAO phongTroDAO = new PhongTroDAO();
	private SinhVienDAO svDAO = new SinhVienDAO();

	public List<HopDong> findAll() {
		List<HopDong> resutls = new ArrayList<HopDong>();
		Connection con = connect();
		try {
			Statement myStmt = con.createStatement();
			ResultSet myRs = myStmt.executeQuery("{call findAll(hopDong)}");
			while (myRs.next()) {
				PhongTro phongTro = phongTroDAO.findOneById(myRs.getString(4));
				SinhVien sinhVien = svDAO.findBy(myRs.getString(3), null, null, null, null, null, null).get(0);
				HopDong hopDong = new HopDong(myRs.getString(1), sinhVien, phongTro, myRs.getDate(2), myRs.getDate(5));
				resutls.add(hopDong);
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

	public boolean save(HopDong hopDong, String type) {
		List<HopDong> list = findAll();
		if (type.equals("insert")) {
			if (list.contains(hopDong)) {
				return false;
			}
		}
		String SQL = "{call saveHopDong(?,?,?,?,?,?)}";
		Connection con = connect();
		try {
			PreparedStatement pstmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, type);
			pstmt.setString(2, hopDong.getMa());
			pstmt.setString(3, hopDong.getSinhVien().getMaSinhVien());
			pstmt.setString(4, hopDong.getPhongTro().getMaPhong());
			pstmt.setDate(5, new java.sql.Date(hopDong.getNgayKiHopDong().toInstant().toEpochMilli()));
			pstmt.setDate(6, new java.sql.Date(hopDong.getNgayHetHopDong().toInstant().toEpochMilli()));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
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

	public boolean deleteOneById(String maHD, String maPhong) {
		String SQL = "{call deleteHopDong(" + maHD + ", " + maPhong + ")}";
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
}
