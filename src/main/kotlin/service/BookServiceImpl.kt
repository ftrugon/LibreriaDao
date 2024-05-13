package service

import Book
import dao.IBookDao

class BookServiceImpl(private val bookDao: IBookDao) : IBookService {

    override fun create(book: Book): Book? {
        return bookDao.insertBook(book)
    }

    override fun delete(id: String): Boolean {
        return bookDao.deleteById(id)
    }

    override fun update(book: Book): Book? {
        return bookDao.update(book)
    }

    override fun getById(id: String): Book?{
        return bookDao.selectById(id)
    }

    override fun getAll(): List<Book>? {
        return bookDao.getAll()
    }
}
