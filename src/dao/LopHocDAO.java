package dao;

import static connectDatabase.Main.connect;

import java.sql.Connection;
import java.sql.ResultSet;
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
				LopHoc lop = new LopHoc(myRs.getString(1), myRs.getString(2), myRs.getString(3), chuyenNganh);
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
}
