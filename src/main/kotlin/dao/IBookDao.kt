package dao

import Book

interface IBookDao {

    fun insertBook(book: Book): Book?
    fun update(book: Book): Book?
    fun deleteById(id: String):Boolean
    fun selectById(id: String): Book?
    fun getAll(): List<Book>?
}