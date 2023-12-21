package bean;

import java.util.Date;

public class ThuThu {
	private int maThuThu;
	private String hoTen;
	private boolean gioiTinh;
	private String email;
	private String soDienThoai;
	private String diaChi;
	private String username;
	public int getMaThuThu() {
		return maThuThu;
	}
	public void setMaThuThu(int maThuThu) {
		this.maThuThu = maThuThu;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public boolean getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSoDienThoai() {
		return soDienThoai;
	}
	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "ThuThu [maThuThu=" + maThuThu + ", hoTen=" + hoTen + ", gioiTinh=" + gioiTinh
				+ ", email=" + email + ", soDienThoai=" + soDienThoai + ", diaChi=" + diaChi + ", username=" + username
				+ "]";
	}
	public ThuThu(int maThuThu, String hoTen, boolean gioiTinh, String email, String soDienThoai, String diaChi, String username) {
		super();
		this.maThuThu = maThuThu;
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.email = email;
		this.soDienThoai = soDienThoai;
		this.diaChi = diaChi;
		this.username = username;
	}
	public ThuThu() {
		super();
		// TODO Auto-generated constructor stub
	}
}
