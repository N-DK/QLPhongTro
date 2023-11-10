package entity;

public class LopHoc {
	private String ma;
	private String ten;
	private String gvcn;

	public LopHoc(String ma, String ten, String gvcn) {
		super();
		this.ma = ma;
		this.ten = ten;
		this.gvcn = gvcn;
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

}
