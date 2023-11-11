package dao;

import connectDatabase.Main;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import entity.ChuPhong;

public class ChuTroDAO {
	private ArrayList<ChuPhong> ds;
	ChuPhong cp;
	public ChuTroDAO() {
		ds = new ArrayList<ChuPhong>();
		cp = new ChuPhong();
	}
	public boolean themList(ChuPhong cp) {
		ArrayList<ChuPhong> ds = docTuDTB();
		if(ds.contains(cp)) {
			return false;
		}
		return true;
	}
	public boolean them(ChuPhong cp) {
	
		Connection con = Main.connect();
		PreparedStatement stmt = null;
		int n=0;
		try {
			
			stmt = con.prepareStatement("{call themChuPhong(?,?,?,?,?,?,?)}");
			stmt.setString(1,cp.getMaChuPhong());
			stmt.setString(2,cp.getHo());
			stmt.setString(3,cp.getTen());
			stmt.setString(4,cp.getSdt());
			stmt.setString(5,cp.getDiaChi());
			stmt.setDate(6,new java.sql.Date(cp.getNgaySinh().toInstant().toEpochMilli()));	
			System.out.println(cp.getGioiTinh());
			stmt.setInt(7, cp.getGioiTinh());
			
			n = stmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return true;
	}
	public ArrayList<ChuPhong> docTuDTB() {
	try {
		ArrayList< ChuPhong> ds = new ArrayList<ChuPhong>();
		Connection con = Main.connect();
		String sql ="{call layBangChuPhong}";
		
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		while (rs.next()) {
			String ma = rs.getString(1);
			String ho =rs.getString(2);
			String ten =rs.getString(3);
			String sdt =rs.getString(4);
			String diachi =rs.getString(5);
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
}
