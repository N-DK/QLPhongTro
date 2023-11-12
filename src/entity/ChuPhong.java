package entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class ChuPhong {

	private String maChuPhong, ho, ten, sdt;
	private String diaChi;
	private Date ngaySinh;
	private int gioiTinh;

	@Override
	public int hashCode() {
		return Objects.hash(maChuPhong);
	}

	public ChuPhong() {
		super();
	}

	public ChuPhong(String maChuPhong, String ho, String ten, String sdt, String diaChi, Date ngaySinh, int gioiTinh) {
		super();
		this.maChuPhong = maChuPhong;
		this.ho = ho;
		this.ten = ten;
		this.sdt = sdt;
		this.diaChi = diaChi;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChuPhong other = (ChuPhong) obj;
		return Objects.equals(maChuPhong, other.maChuPhong);
	}

	public String getMaChuPhong() {
		return maChuPhong;
	}

	public void setMaChuPhong(String maChuPhong) {
		this.maChuPhong = maChuPhong;
	}

	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public int getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(int gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public Object[] getObjects() {
		Object[] objects = new Object[7];
		objects[0] = maChuPhong;
		objects[1] = getHo();
		objects[2] = getTen();
		objects[3] = getSdt();
		objects[4] = getDiaChi();
		objects[5] = new SimpleDateFormat("dd-MM-yyyy").format(getNgaySinh());
		objects[6] = getGioiTinh() == 1 ? "Nam" : "Nữ";

		return objects;
	}

}
