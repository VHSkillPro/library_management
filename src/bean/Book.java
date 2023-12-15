package bean;

public class Book {
	private int maSach;
	private String tenSach;
	private String tacGia;
	private String nhaXuatBan;
	private double donGia;
	private int soLuong;
	private String theLoai;
	private int maThuThu;
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(int maSach, String tenSach, String tacGia, String nhaXuatBan, double donGia, int soLuong,
			String theLoai, int maThuThu) {
		super();
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.tacGia = tacGia;
		this.nhaXuatBan = nhaXuatBan;
		this.donGia = donGia;
		this.soLuong = soLuong;
		this.theLoai = theLoai;
		this.maThuThu = maThuThu;
	}

	public int getMaSach() {
		return maSach;
	}

	public void setMaSach(int maSach) {
		this.maSach = maSach;
	}

	public String getTenSach() {
		return tenSach;
	}

	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}

	public String getTacGia() {
		return tacGia;
	}

	public void setTacGia(String tacGia) {
		this.tacGia = tacGia;
	}

	public String getNhaXuatBan() {
		return nhaXuatBan;
	}

	public void setNhaXuatBan(String nhaXuatBan) {
		this.nhaXuatBan = nhaXuatBan;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getTheLoai() {
		return theLoai;
	}

	public void setTheLoai(String theLoai) {
		this.theLoai = theLoai;
	}

	public int getMaThuThu() {
		return maThuThu;
	}

	public void setMaThuThu(int maThuThu) {
		this.maThuThu = maThuThu;
	}
	
}
