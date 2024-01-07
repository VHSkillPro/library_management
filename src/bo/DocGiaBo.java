package bo;

import java.util.ArrayList;
import java.util.Date;

import bean.DocGia;
import dao.DocGiaDao;

public class DocGiaBo {
	static public ArrayList<DocGia> getAllDocGia() {
		return DocGiaDao.getAllDocGia();
	}
	
	static public DocGia getDocGiaByUsername(String username) {
		return DocGiaDao.getDocGiaByUsername(username);
	}
	
	static public DocGia getDocGiaByMaDocGia(int maDocGia) {
		return DocGiaDao.getDocGiaByMaDocGia(maDocGia);
	}
	
	static public ArrayList<DocGia> findDocGia(String maDocGia, String hoTen, int gioiTinh, Date fromDate, Date toDate, String email, String soDienThoai) {
		return DocGiaDao.findDocGia(maDocGia, hoTen, gioiTinh, fromDate, toDate, email, soDienThoai);
	}
	
	static public Boolean insertDocGia(DocGia docGia) {
		return DocGiaDao.insertDocGia(docGia);
	}
	
	static public Boolean updateDocGia(int maDocGia, String hoTen, boolean gioiTinh, Date ngaySinh, String email, String soDienThoai, String diaChi) {
		if (getDocGiaByMaDocGia(maDocGia) == null) {
			return false;
		}
		return DocGiaDao.updateDocGia(maDocGia, hoTen, gioiTinh, ngaySinh, email, soDienThoai, diaChi);
	}
	
	static public Boolean deleteDocGia(int maDocGia) {
		return DocGiaDao.deleteDocGia(maDocGia);
	}
}
