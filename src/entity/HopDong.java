package entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class HopDong {
	private String ma;
	private SinhVien sinhVien;
	private PhongTro phongTro;
	private Date ngayKiHopDong;
	private Date ngayHetHopDong;

	public HopDong(String ma, SinhVien sinhVien, PhongTro phongTro, Date ngayKiHopDong, Date ngayHetHopDong) {
		super();
		this.ma = ma;
		this.sinhVien = sinhVien;
		this.phongTro = phongTro;
		this.ngayKiHopDong = ngayKiHopDong;
		this.ngayHetHopDong = ngayHetHopDong;
	}

	public String getMa() {
		return ma;
	}

	public void setMa(String ma) {
		this.ma = ma;
	}

	public SinhVien getSinhVien() {
		return sinhVien;
	}

	public void setSinhVien(SinhVien sinhVien) {
		this.sinhVien = sinhVien;
	}

	public PhongTro getPhongTro() {
		return phongTro;
	}

	public void setPhongTro(PhongTro phongTro) {
		this.phongTro = phongTro;
	}

	public Date getNgayKiHopDong() {
		return ngayKiHopDong;
	}

	public void setNgayKiHopDong(Date ngayKiHopDong) {
		this.ngayKiHopDong = ngayKiHopDong;
	}

	public Date getNgayHetHopDong() {
		return ngayHetHopDong;
	}

	public void setNgayHetHopDong(Date ngayHetHopDong) {
		this.ngayHetHopDong = ngayHetHopDong;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ma);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HopDong other = (HopDong) obj;
		return Objects.equals(ma, other.ma);
	}

	public Object[] getObjects() {
		Object[] objects = new Object[5];
		objects[0] = ma;
		objects[1] = sinhVien.getMaSinhVien();
		objects[2] = phongTro.getMaPhong();
		objects[3] = new SimpleDateFormat(("dd-MM-yyyy")).format(ngayKiHopDong);
		objects[4] = new SimpleDateFormat(("dd-MM-yyyy")).format(ngayHetHopDong);
		return objects;
	}
}
