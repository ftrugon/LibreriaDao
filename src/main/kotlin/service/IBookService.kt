package service

import Book

interface IBookService {
    fun create(user: Book): Book?
    fun getById(id: String): Book?
    fun update(user: Book): Book?
    fun delete(id: String): Boolean
    fun getAll(): List<Book>?
}
