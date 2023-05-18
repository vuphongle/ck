package entity;

public class PhongBan {
	private String maPhong;
	private String tenPhong;
	public String getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}
	public String getTenPhong() {
		return tenPhong;
	}
	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}
	public PhongBan() {
		super();
	}
	public PhongBan(String maPhong) {
		this.maPhong = maPhong;
	}
	public PhongBan(String maPhong, String tenPhong) {
		super();
		this.maPhong = maPhong;
		this.tenPhong = tenPhong;
	}
	
	
}
