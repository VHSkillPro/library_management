package bo;

import java.util.ArrayList;

import bean.Book;
import dao.BookDao;

public class BookBo {
	static public ArrayList<Book> getAllBook() {
		return BookDao.getAllBook();
	}
	static public Boolean insertBook(Book books) {
		return BookDao.insertBook(books);
	}
	static public Boolean deleteBook(Book books) {
		return BookDao.deleteBook(books);
	}
	static public Boolean updateBook(Book books) {
		return BookDao.updateBook(books);
	}
	static public ArrayList<Book> findbyBookName(String name) {
		return BookDao.findByBookName(name);
	}
	static public Book findByBookId(int bookId) {
		return BookDao.findByBookId(bookId);
	}
	static public ArrayList<Book> findBookbyAuthor(String tacGia) {
		return BookDao.findBookbyAuthor(tacGia);
	}
	static public ArrayList<Book> findBookbyNXB(String NXB) {
		return BookDao.findBookbyNXB(NXB);
	}
	static public ArrayList<Book> findBookbyType(String type) {
		return BookDao.findBookbyType(type);
	}
	static public ArrayList<Book> SearchBook(String maSach, String tenSach, String tacGia, String NXB, String theLoai) {
		return BookDao.SearchBook(maSach, tenSach, tacGia, NXB, theLoai);
	}
	
	static public int findPosInArraybyId(int bookId, ArrayList<Book> lst) {
		int pos = -1;
		for (int i = 0; i < lst.size(); i++) {
			if (Integer.valueOf(lst.get(i).getMaSach()) == bookId) {
				pos = i;
				break;
			}
		}
		return pos;
	}
}
