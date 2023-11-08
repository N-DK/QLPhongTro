package service;

import java.sql.Connection;
import java.util.List;

import dto.ISinhVien;
import entity.SinhVien;
import static connectDatabase.Main.connect;

public class SinhVienService implements ISinhVien{

	@Override
	public List<SinhVien> findAll() {
		Connection con = connect();
		return null;
	}

}
