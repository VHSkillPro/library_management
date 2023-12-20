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
}
