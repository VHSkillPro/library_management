package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.xml.crypto.Data;

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

	static public Boolean insertBook(Book books) {
		try {
			if (findByBookId(books.getMaSach()) != null) {
				int soLuong = findByBookId(books.getMaSach()).getSoLuong() + books.getSoLuong();
				String sql = "update Sach set soLuong = ? where maSach = ?";
				PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
				stmt.setInt(1, soLuong);
				stmt.setInt(2, books.getMaSach());
				stmt.executeUpdate();
			}
			else {
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
			}
			return true;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return false;
	}
	static public Boolean deleteBook(Book books) {
		try {
			String sql = "declare @masach int,\r\n"
					+ "	@result int\r\n"
					+ "\r\n"
					+ "execute proc_Xoa \r\n"
					+ "	@masach = ?,\r\n"
					+ "	@result = @result output\r\n"
					+ "\r\n"
					+ "select @result as Mathongbao";
			PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
			stmt.setInt(1, books.getMaSach());
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				int op = rs.getInt(1);
				if (op == 0) JOptionPane.showMessageDialog(null, "Sách này đang được mượn");
				else JOptionPane.showInternalMessageDialog(null, "Xóa thành công");
			}
			return true;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return false;
	}
	static public Boolean updateBook(Book books) {
		try {
			
			
			String sql = "declare @maSach int,\r\n"
					+ "	@tenSach nvarchar(255),\r\n"
					+ "	@tacGia nvarchar(255),\r\n"
					+ "	@nhaXuatBan nvarchar(255),\r\n"
					+ "	@donGia money,\r\n"
					+ "	@soLuong int,\r\n"
					+ "	@theLoai nvarchar(255),\r\n"
					+ "	@maThuThu int\r\n"
					+ "\r\n"
					+ "execute proc_Them\r\n"
					+ "	@maSach = ?,\r\n"
					+ "	@tenSach = ?,\r\n"
					+ "	@tacGia = ?,\r\n"
					+ "	@nhaXuatBan = ?,\r\n"
					+ "	@donGia = ?,\r\n"
					+ "	@soLuong = ?,\r\n"
					+ "	@theLoai = ?,\r\n"
					+ "	@maThuThu = ?";
			PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
			stmt.setString(1, books.getTenSach());
			stmt.setString(2, books.getTacGia());
			stmt.setString(3,  books.getNhaXuatBan());
			stmt.setDouble(4, books.getDonGia());
			stmt.setInt(5, books.getSoLuong());
			stmt.setString(6, books.getTheLoai());
			stmt.setInt(7, books.getMaThuThu());
			stmt.setInt(8, books.getMaSach());
			stmt.executeUpdate();
			return true;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return false;
	}
	static public ArrayList<Book> findByBookName(String name) {
		try {
			ArrayList<Book> listBook = getAllBook();
			ArrayList<Book> containsBook = new ArrayList<Book>();
			for (Book x : listBook) {
				if (x.getTenSach().toLowerCase().trim().contains(name.toLowerCase().trim())) {
					containsBook.add(x);
				}
			}
			return containsBook;
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
	static public ArrayList<Book> findBookbyAuthor(String tacGia) {
		try {
			ArrayList<Book> lst = getAllBook();
			ArrayList<Book> ans = new ArrayList<Book>();
			for (Book x : lst) {
				if (x.getTacGia().toLowerCase().trim().contains(tacGia.toLowerCase().trim())) {
					ans.add(x);
				}
			}
			return ans;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}
	static public ArrayList<Book> findBookbyNXB(String NXB) {
		try {
			ArrayList<Book> lst = getAllBook();
			ArrayList<Book> ans = new ArrayList<Book>();
			for (Book x : lst) {
				if (x.getNhaXuatBan().toLowerCase().trim().contains(NXB.toLowerCase().trim())) {
					ans.add(x);
				}
			}
			return ans;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}
	static public ArrayList<Book> findBookbyType(String type) {
		try {
			ArrayList<Book> lst = getAllBook();
			ArrayList<Book> ans = new ArrayList<Book>();
			for (Book x : lst) {
				if (x.getTheLoai().toLowerCase().trim().contains(type.toLowerCase().trim())) {
					ans.add(x);
				}
			}
			return ans;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}
	
	static public ArrayList<Book> SearchBook(String maSach, String tenSach, String tacGia, String NXB, String theLoai) {
		try {
			String sql = "declare @maSach nvarchar(255),\r\n"
					+ "	@tenSach nvarchar(255),\r\n"
					+ "	@tacGia nvarchar(255),\r\n"
					+ "	@NXB nvarchar(255),\r\n"
					+ "	@theLoai nvarchar(255)\r\n"
					+ "\r\n"
					+ "execute proc_Timkiem\r\n"
					+ "	@maSach = ?,\r\n"
					+ "	@tenSach = ?,\r\n"
					+ "	@tacGia = ?,\r\n"
					+ "	@NXB = ?,\r\n"
					+ "	@theLoai = ?";
			PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
			stmt.setString(1, maSach);
			stmt.setString(2, tenSach);
			stmt.setString(3, tacGia);
			stmt.setString(4, NXB);
			stmt.setString(5, theLoai);
			ResultSet rs = stmt.executeQuery();
			ArrayList<Book> lst = new ArrayList<Book>();
			while (rs.next()) {
				Integer masach = Integer.parseInt(rs.getString(1));
				String tensach = rs.getString(2);
				String tacgia = rs.getString(3);
				String nxb = rs.getString(4);
				Double dongia = Double.parseDouble(rs.getString(5));
				Integer soluong = Integer.parseInt(rs.getString(6));
				String theloai = rs.getString(7);
				Integer maThuThu = Integer.parseInt(rs.getString(8));
				lst.add(new Book(masach, tensach, tacgia, nxb, dongia, soluong, theloai, maThuThu));
			}
			return lst;
		} catch (Exception e) {
			e.getStackTrace();
		}
		return null;
	}
}
