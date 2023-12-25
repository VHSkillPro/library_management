package bean;

public class ChiTietPhieuMuon {
	private int maPhieuMuon;
	private int maSach;
	private int soLuong;
	public int getMaPhieuMuon() {
		return maPhieuMuon;
	}
	public void setMaPhieuMuon(int maPhieuMuon) {
		this.maPhieuMuon = maPhieuMuon;
	}
	public int getMaSach() {
		return maSach;
	}
	public void setMaSach(int maSach) {
		this.maSach = maSach;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	@Override
	public String toString() {
		return "ChiTietPhieuMuon [maPhieuMuon=" + maPhieuMuon + ", maSach=" + maSach + ", soLuong=" + soLuong + "]";
	}
	public ChiTietPhieuMuon(int maPhieuMuon, int maSach, int soLuong) {
		super();
		this.maPhieuMuon = maPhieuMuon;
		this.maSach = maSach;
		this.soLuong = soLuong;
	}
	public ChiTietPhieuMuon() {
		super();
		// TODO Auto-generated constructor stub
	}
}
