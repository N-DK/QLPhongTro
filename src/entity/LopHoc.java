package entity;

public class LopHoc {
	private String ma;
	private String ten;
	private String gvcn;
	private ChuyenNganh chuyenNganh;

	public LopHoc(String ma, String ten, String gvcn, ChuyenNganh chuyenNganh) {
		super();
		this.ma = ma;
		this.ten = ten;
		this.gvcn = gvcn;
		this.chuyenNganh = chuyenNganh;
	}

	public ChuyenNganh getChuyenNganh() {
		return chuyenNganh;
	}

	public void setChuyenNganh(ChuyenNganh chuyenNganh) {
		this.chuyenNganh = chuyenNganh;
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

	public String getGvcn() {
		return gvcn;
	}

	public void setGvcn(String gvcn) {
		this.gvcn = gvcn;
	}

	public Object[] getObjects() {
		Object[] objects = new Object[4];
		objects[0] = ma;
		objects[1] = ten;
		objects[2] = gvcn;
		objects[3] = chuyenNganh.getMa();
		return objects;
	}

}
