package service

import Book

interface IBookService {
    fun create(book: Book): Book?
    fun getById(id: String): Book?
    fun update(book: Book): Book?
    fun delete(id: String): Boolean
    fun getAll(): List<Book>?
}
