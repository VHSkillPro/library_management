package bean;

import java.util.Date;

public class DocGia {
	private int maDocGia;
	private String hoTen;
	private boolean gioiTinh;
	private Date ngaySinh;
	private String email;
	private String soDienThoai;
	private String diaChi;
	private String username;
	
	public DocGia(int maDocGia, String hoTen, boolean gioiTinh, Date ngaySinh, String email, String soDienThoai, String diaChi, String username) {
		this.maDocGia = maDocGia;
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.email = email;
		this.soDienThoai = soDienThoai;
		this.diaChi = diaChi;
		this.username = username;
	}

	public int getMaDocGia() {
		return maDocGia;
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

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
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

	@Override
	public String toString() {
		return "DocGia [maDocGia=" + maDocGia + ", hoTen=" + hoTen + ", gioiTinh=" + gioiTinh + ", email=" + email
				+ ", soDienThoai=" + soDienThoai + ", username=" + username + "]";
	}
}
