package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.Book;

//Hiển thị danh sách sách trong kho
//Tạo form thêm sách
//Xoá sách
//Tạo form chỉnh sửa sách
//Tìm kiếm sách

public class BookDao {
	static public ArrayList<Book> getAllBook() {
		ArrayList<Book> listBook = new ArrayList<Book>();
		try {
			String sql = "select *from Sach";
			PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int maSach = rs.getInt("maSach");
				String tenSach = rs.getString("tenSach");
				String tacGia = rs.getString("tacGia");
				String nhaXuatBan = rs.getString("nhaXuatBan");
				double donGia = rs.getDouble("donGia");
				int soLuong = rs.getInt("soLuong");
				String theLoai = rs.getString("theLoai");
				int maThuThu = rs.getInt("maThuThu");
				listBook.add(new Book(maSach, tenSach, tacGia, nhaXuatBan, donGia, soLuong, theLoai, maThuThu));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listBook;
	}

	static public Boolean insertBook(Book books ) {
		try {
			String sql = "insert into Sach(tenSach, tacGia, nhaXuatBan, donGia, soLuong, theLoai, maThuThu) "
					+ "values(?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
			stmt.setString(1, books.getTenSach());
			stmt.setString(2, books.getTacGia());
			stmt.setString(3, books.getNhaXuatBan());
			stmt.setDouble(4, books.getDonGia());
			stmt.setInt(5, books.getSoLuong());
			stmt.setString(6, books.getTheLoai());
			stmt.setInt(7, books.getMaThuThu());
			stmt.executeUpdate();
			return true;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return false;
	}
	static public Boolean deleteBook(Book books) {
		try {
			String sql = "delete from Sach where maSach = ?";
			PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
			stmt.setInt(1, books.getMaSach());
			stmt.executeUpdate();
			return true;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return false;
	}
	static public Boolean updateBook(Book books) {
		try {
			String sql = "update Sach set tenSach = ? where maSach = ?";
			PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
			stmt.setString(1, books.getTenSach());
			stmt.setInt(2, books.getMaSach());
			stmt.executeUpdate();
			return true;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return false;
	}
	static public Book findByBookName(String name) {
		try {
			ArrayList<Book> listBook = getAllBook();
			for (Book x : listBook) {
				if (x.getTenSach().toLowerCase().trim().contains(name.toLowerCase().trim())) {
					return x;
				}
			}
			return null;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}
	static public Book findByBookId(int bookId) {
		try {
			ArrayList<Book> listBook = getAllBook();
			for (Book x : listBook) {
				if (x.getMaSach() == bookId) {
					return x;
				}
			}
			return null;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}
}
