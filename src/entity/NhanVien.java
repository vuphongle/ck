package entity;

public class NhanVien {
	private String maNV;
	private String ho;
	private String ten;
	private int tuoi;
	private Boolean phai;
	private String maPhong;
	private float tienLuong;
	
	private PhongBan phong;

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
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

	public int getTuoi() {
		return tuoi;
	}

	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}

	public Boolean getPhai() {
		return phai;
	}

	public void setPhai(Boolean phai) {
		this.phai = phai;
	}

	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public float getTienLuong() {
		return tienLuong;
	}

	public void setTienLuong(float tienLuong) {
		this.tienLuong = tienLuong;
	}

	public PhongBan getPhong() {
		return phong;
	}

	public void setPhong(PhongBan phong) {
		this.phong = phong;
	}

	public NhanVien(String maNV, String ho, String ten, int tuoi, Boolean phai, String maPhong, float tienLuong,
			PhongBan phong) {
		super();
		this.maNV = maNV;
		this.ho = ho;
		this.ten = ten;
		this.tuoi = tuoi;
		this.phai = phai;
		this.maPhong = maPhong;
		this.tienLuong = tienLuong;
		this.phong = phong;
	}
	
	
	

}
