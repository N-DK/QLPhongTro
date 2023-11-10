package dao;

import static connectDatabase.Main.connect;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import entity.SinhVien;

public class SinhVienDAO extends AbstractDAO<SinhVien> {

	public SinhVienDAO() {
		sqlFind = "{call findAllSinhVien}";
	}

	public boolean insert(SinhVien sinhVien) {
		Connection con = connect();
		List<SinhVien> list = findAll();
		if (list.contains(sinhVien))
			return false;
		return true;
	}

	public void update(SinhVien sinhVien) {
		
	}
	
	public List<SinhVien> findBy(String masv, String ho, String ten, String maLop, String queQuan) {
		List<SinhVien> results = new ArrayList<SinhVien>();
		return results;
	}
}
