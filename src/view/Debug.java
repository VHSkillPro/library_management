package view;

import dao.Database;

public class Debug {

	public static void main(String[] args) {
		/// ---------- Test ket noi database [OK]
		Database.getInstance("35.240.220.181", "library_mangement", "sqlserver", "123123");
		if (Database.getConnection() != null) {
			System.out.printf("Ket noi database thanh cong\n");
		}
		else {
			System.out.printf("Ket noi database that bai\n");
		}
		
		/// ---------- Test SHA-256 [OK]
//		System.out.printf("Hash-256 for \"admin\" : " + SHA256.getString("admin"));
		
		/// ---------- Test get account by username [OK]
//		Account account = AccountBo.getAccountByUsername("admin");
//		if (account == null) {
//			System.out.printf("Khong ton tai tai khoan\n");
//		}
//		else {
//			System.out.printf(account.toString());
//		}
		
		/// Test insert account [OK]
//		Account account = new Account("admin", SHA256.getString("admin"), 0, new Date());
//		if (AccountBo.insertAccount(account)) {
//			System.out.printf("Them tai khoan thanh cong\n");
//		}
//		else {
//			System.out.printf("Them tai khoan that bai\n");
//		}
		
		// Test delete account [OK]
//		if (AccountBo.deleteAccount("admin")) {
//			System.out.printf("Xoa tai khoan thanh cong\n");
//		}
//		else {
//			System.out.printf("Xoa tai khoan that bai\n");
//		}
//		
//		try {
//			String sql = "delete from DocGia where maDocGia = ?";
//			PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
//			stmt.setInt(1, -1);
//			ResultSet rs = stmt.executeQuery();
//			
//			int cnt = 0;
//			while (rs.next()) {
//				cnt++;
//			}
//			
//			System.out.print(cnt);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
	}

}
