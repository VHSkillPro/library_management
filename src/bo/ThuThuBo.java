package bo;

import java.util.ArrayList;
import java.util.Date;

import bean.ThuThu;
import dao.ThuThuDao;

public class ThuThuBo {
	static public ArrayList<ThuThu> getAllThuThu() {
		return ThuThuDao.getAllThuThu();
	}
	
	static public ThuThu getThuThuByUsername(String username) {
		return ThuThuDao.getThuThuByUsername(username);
	}
	
	static public ThuThu getThuThuByMaThuThu(int maThuThu) {
		return ThuThuDao.getThuThuByMaThuThu(maThuThu);
	}
	
	static public Boolean insertThuThu(ThuThu thuThu) {
		if (ThuThuBo.getThuThuByUsername(thuThu.getUsername()) != null) {
			return false;
		}
		return ThuThuDao.insertThuThu(thuThu);
	}
	
	static public ArrayList<ThuThu> findThuThu(String maThuThu, String hoTen, int gioiTinh, Date fromDate, Date toDate, String email, String soDienThoai, int chucVu) {
		return ThuThuDao.findThuThu(maThuThu, hoTen, gioiTinh, fromDate, toDate, email, soDienThoai, chucVu);
	}
	
	static public Boolean deleteThuThu(int maThuThu) {
		return ThuThuDao.deleteThuThu(maThuThu);
	}
}
