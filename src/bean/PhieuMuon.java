package bean;

import java.util.Date;

public class PhieuMuon {
	private int maPhieuMuon;
	private Date ngayMuon, ngayTra;
	private int trangThai;
	private int maDocGia;
	private int maThuThu;
	public PhieuMuon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PhieuMuon(int maPhieuMuon, Date ngayMuon, Date ngayTra, int trangThai, int maDocGia, int maThuThu) {
		super();
		this.maPhieuMuon = maPhieuMuon;
		this.ngayMuon = ngayMuon;
		this.ngayTra = ngayTra;
		this.trangThai = trangThai;
		this.maDocGia = maDocGia;
		this.maThuThu = maThuThu;
	}
	public int getMaPhieuMuon() {
		return maPhieuMuon;
	}
	public void setMaPhieuMuon(int maPhieuMuon) {
		this.maPhieuMuon = maPhieuMuon;
	}
	public Date getNgayMuon() {
		return ngayMuon;
	}
	public void setNgayMuon(Date ngayMuon) {
		this.ngayMuon = ngayMuon;
	}
	public Date getNgayTra() {
		return ngayTra;
	}
	public void setNgayTra(Date ngayTra) {
		this.ngayTra = ngayTra;
	}
	public int getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
	public int getMaDocGia() {
		return maDocGia;
	}
	public void setMaDocGia(int maDocGia) {
		this.maDocGia = maDocGia;
	}
	public int getMaThuThu() {
		return maThuThu;
	}
	public void setMaThuThu(int maThuThu) {
		this.maThuThu = maThuThu;
	}
	@Override
	public String toString() {
		return "PhieuMuon [maPhieuMuon=" + maPhieuMuon + ", ngayMuon=" + ngayMuon + ", ngayTra=" + ngayTra
				+ ", trangThai=" + trangThai + ", maDocGia=" + maDocGia + ", maThuThu=" + maThuThu + "]";
	}
	
}
