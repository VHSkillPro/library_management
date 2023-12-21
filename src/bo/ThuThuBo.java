package bo;

import java.util.ArrayList;

import bean.ThuThu;
import dao.ThuThuDao;

public class ThuThuBo {
	static public ArrayList<ThuThu> getAllDocGia() {
		return ThuThuDao.getAllThuThu();
	}
	
	static public ThuThu getThuThuByUsername(String username) {
		return ThuThuDao.getThuThuByUsername(username);
	}
	
	static public ThuThu getThuThuByMaThuThu(int maThuThu) {
		return ThuThuDao.getThuThuByMaThuThu(maThuThu);
	}
	
	static public Boolean insertThuThu(ThuThu thuThu) {
		return ThuThuDao.insertThuThu(thuThu);
	}
	
	static public Boolean deleteThuThu(int maThuThu) {
		return ThuThuDao.deleteThuThu(maThuThu);
	}
}
