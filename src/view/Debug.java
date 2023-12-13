package view;

import dao.Database;

public class Debug {

	public Debug() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		/// Test ket noi database
		Database.getInstance("35.240.220.181", "library_mangement", "sqlserver", "123123");
		if (Database.getConnection() != null) {
			System.out.printf("Ket noi database thanh cong");
		}
		else {
			System.out.printf("Ket noi database that bai");
		}
	}

}
