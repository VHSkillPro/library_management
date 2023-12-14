package bo;

import java.util.ArrayList;

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
	
	static public Boolean insertDocGia(DocGia docGia) {
		return DocGiaDao.insertDocGia(docGia);
	}
	
	static public Boolean deleteDocGia(int maDocGia) {
		return DocGiaDao.deleteDocGia(maDocGia);
	}
}
