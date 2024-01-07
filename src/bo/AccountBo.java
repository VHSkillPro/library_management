package bo;

import java.util.ArrayList;
import java.util.Date;

import bean.Account;
import dao.AccountDao;

public class AccountBo {
	static public Account getAccountByUsername(String username) {
		return AccountDao.getAccountByUsername(username);
	}
	
	static public ArrayList<Account> getAllAccount() {
		return AccountDao.getAllAccount();
	}

	static public ArrayList<Account> findAccount(String username, int role, Date fromDate, Date toDate){
		return AccountDao.findAccount(username, role, fromDate, toDate);
	}
	
	static public Boolean insertAccount(Account account) {
		if (AccountDao.getAccountByUsername(account.getUsername()) != null) {
			return false;
		}
		return AccountDao.insertAccount(account);
	}
	
	static public Boolean updatePassword(String username, String password) {
		if (AccountDao.getAccountByUsername(username) == null) {
			return false;
		}
		return AccountDao.updatePassword(username, password);
	}
	
	static public Boolean deleteAccount(String username) {
		return AccountDao.deleteAccount(username);
	}
}
