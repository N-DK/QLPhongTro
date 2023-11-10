package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SinhVien extends Person {
	private String maSinhVien;
	private String queQuan;
	private String maLop;

	public SinhVien(String ho, String ten, int gioiTinh, Date ngaySinh, String sdt, String maSinhVien, String queQuan,
			String maLop) {
		super(ho, ten, gioiTinh, ngaySinh, sdt);
		this.maSinhVien = maSinhVien;
		this.queQuan = queQuan;
		this.maLop = maLop;
	}

	public String getMaSinhVien() {
		return maSinhVien;
	}

	public void setMaSinhVien(String maSinhVien) {
		this.maSinhVien = maSinhVien;
	}

	public String getQueQuan() {
		return queQuan;
	}

	public void setQueQuan(String queQuan) {
		this.queQuan = queQuan;
	}

	public String getMaLop() {
		return maLop;
	}

	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}

	public Object[] getObjects() {
		Object[] objects = new Object[8];
		objects[0] = maSinhVien;
		objects[1] = getHo();
		objects[2] = getTen();
		objects[3] = maLop;
		objects[4] = queQuan;
		objects[5] = getGioiTinh() == 1 ? "Nam" : "Nữ";
		objects[6] = new SimpleDateFormat("dd-MM-yyyy").format(getNgaySinh());
		objects[7] = getSdt();

		return objects;
	}

}
