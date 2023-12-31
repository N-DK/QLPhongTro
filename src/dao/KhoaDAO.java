package dao;

import static connectDatabase.Main.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Khoa;

public class KhoaDAO {
	public List<Khoa> findAll() {
		List<Khoa> resutls = new ArrayList<Khoa>();
		Connection con = connect();
		try {
			Statement myStmt = con.createStatement();
			ResultSet myRs = myStmt.executeQuery("{call findAll(khoa)}");
			while (myRs.next()) {
				Khoa khoa = new Khoa(myRs.getString(1), myRs.getString(2));
				resutls.add(khoa);
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

	public Khoa findOneById(String maKhoa) {
		Khoa resutls = null;
		Connection con = connect();
		try {
			Statement myStmt = con.createStatement();
			ResultSet myRs = myStmt.executeQuery("{call findOneById(khoa," + maKhoa + ")}");
			while (myRs.next()) {
				resutls = new Khoa(myRs.getString(1), myRs.getString(2));
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

	public boolean save(Khoa khoa, String type) {
		List<Khoa> list = findAll();
		if (type.equals("insert")) {
			if (list.contains(khoa)) {
				return false;
			}
		}
		String SQL = "{call saveKhoa(?,?,?)}";
		Connection con = connect();
		try {
			PreparedStatement pstmt = con.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, type);
			pstmt.setString(2, khoa.getMa());
			pstmt.setString(3, khoa.getTen());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
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

	public boolean deleteOneById(String maKhoa) {
		String SQL = "{call deleteOneById(khoa," + maKhoa + ")}";
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
