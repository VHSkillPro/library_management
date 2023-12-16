package bo;
import java.util.ArrayList;

import bean.ChiTietPhieuMuon;
import dao.ChiTietPhieuMuonDao;

public class ChiTietPhieuMuonBo {
	static public ArrayList<ChiTietPhieuMuon> getCTPMByMaPhieuMuon(int maPM) {
		return ChiTietPhieuMuonDao.getCTPMByMaPhieuMuon(maPM);
	}
}
