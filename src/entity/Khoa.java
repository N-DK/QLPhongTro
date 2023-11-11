package entity;

import java.util.Objects;

public class Khoa {
	private String ma;
	private String ten;

	public Khoa(String ma, String ten) {
		super();
		this.ma = ma;
		this.ten = ten;
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
		Object[] objects = new Object[2];
		objects[0] = ma;
		objects[1] = ten;
		return objects;
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
		Khoa other = (Khoa) obj;
		return Objects.equals(ma, other.ma);
	}

}
