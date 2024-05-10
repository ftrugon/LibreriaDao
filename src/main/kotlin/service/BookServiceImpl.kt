package service

import Book
import dao.IBookDao

class BookServiceImpl(private val bookDao: IBookDao) : IBookDao {

    override fun insertBook(book: Book): Book? {
        return bookDao.insertBook(book)
    }

    override fun deleteById(id: String): Boolean {
        return bookDao.deleteById(id)
    }

    override fun update(book: Book): Book? {
        return bookDao.update(book)
    }

    override fun selectById(id: String): Book? {
        return bookDao.selectById(id)
    }

    override fun getAll(): List<Book>? {
        return bookDao.getAll()
    }
}
