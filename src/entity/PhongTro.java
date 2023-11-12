package entity;

import java.text.DecimalFormat;
import java.util.Objects;

public class PhongTro {
	private String maPhong;
	private String diaChi;
	private float gia;
	private ChuPhong chuPhong;
	private int tinhTrang;

	public PhongTro(String maPhong, String diaChi, float gia, ChuPhong chuPhong, int tinhTrang) {
		super();
		this.maPhong = maPhong;
		this.diaChi = diaChi;
		this.gia = gia;
		this.chuPhong = chuPhong;
		this.tinhTrang = tinhTrang;
	}

	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public float getGia() {
		return gia;
	}

	public void setGia(float gia) {
		this.gia = gia;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public int getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(int tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public ChuPhong getChuPhong() {
		return chuPhong;
	}

	public void setChuPhong(ChuPhong chuPhong) {
		this.chuPhong = chuPhong;
	}

	public Object[] getObject() {
		DecimalFormat decimalFormat = new DecimalFormat("#,###");
		Object[] objects = new Object[5];
		objects[0] = maPhong;
		objects[1] = diaChi;
		objects[2] = decimalFormat.format(gia);
		objects[3] = chuPhong.getMaChuPhong();
		objects[4] = tinhTrang == 1 ? "Disable" : "Enable";
		return objects;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maPhong);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhongTro other = (PhongTro) obj;
		return Objects.equals(maPhong, other.maPhong);
	}
}
