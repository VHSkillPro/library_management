package bo;

import java.util.ArrayList;

import bean.Account;
import dao.AccountDao;

public class AccountBo {
	static public Account getAccountByUsername(String username) {
		return AccountDao.getAccountByUsername(username);
	}
	
	static public ArrayList<Account> getAllAccount() {
		return AccountDao.getAllAccount();
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
