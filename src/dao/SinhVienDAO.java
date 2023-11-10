package dao;

import java.sql.Connection;
import static connectDatabase.Main.connect;
import entity.SinhVien;

public class SinhVienDAO extends AbstractDAO<SinhVien> {

	public SinhVienDAO() {
		sql = "{call findAllSinhVien}";
	}

	public void insert(SinhVien sinhVien) {
		Connection con = connect();
	}
}
