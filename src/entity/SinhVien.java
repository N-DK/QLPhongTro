package entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class SinhVien extends Person {
	private String maSinhVien;
	private String queQuan;
	private LopHoc lopHoc;

	public SinhVien(String ho, String ten, int gioiTinh, Date ngaySinh, String sdt, String maSinhVien, String queQuan,
			LopHoc lopHoc) {
		super(ho, ten, gioiTinh, ngaySinh, sdt);
		this.maSinhVien = maSinhVien;
		this.queQuan = queQuan;
		this.lopHoc = lopHoc;
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

	public LopHoc getLopHoc() {
		return lopHoc;
	}

	public void setLopHoc(LopHoc lopHoc) {
		this.lopHoc = lopHoc;
	}

	public Object[] getObjects() {
		Object[] objects = new Object[8];
		objects[0] = maSinhVien;
		objects[1] = getHo();
		objects[2] = getTen();
		objects[3] = lopHoc.getMa();
		objects[4] = queQuan;
		objects[5] = getGioiTinh() == 1 ? "Nam" : "Nữ";
		objects[6] = new SimpleDateFormat("dd-MM-yyyy").format(getNgaySinh());
		objects[7] = getSdt();

		return objects;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maSinhVien);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SinhVien other = (SinhVien) obj;
		return Objects.equals(maSinhVien, other.maSinhVien);
	}

}
