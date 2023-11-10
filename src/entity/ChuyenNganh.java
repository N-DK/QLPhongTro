package entity;

public class ChuyenNganh {
	private String ma;
	private String ten;
	private Khoa khoa;

	public ChuyenNganh(String ma, String ten, Khoa khoa) {
		super();
		this.ma = ma;
		this.ten = ten;
		this.khoa = khoa;
	}

	public Khoa getKhoa() {
		return khoa;
	}

	public void setKhoa(Khoa khoa) {
		this.khoa = khoa;
	}

	public String getMa() {
		return ma;
	}

	public void setMa(String ma) {
		this.ma = ma;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public Object[] getObjects() {
		Object[] objects = new Object[3];
		objects[0] = ma;
		objects[1] = ten;
		objects[2] = khoa.getMa();
		return objects;

	}

}
