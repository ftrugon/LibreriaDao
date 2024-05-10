package dao

import Book
import com.zaxxer.hikari.HikariDataSource
import db_conn.DataBase
import output.IOutpuInfo
import java.sql.SQLException
import javax.sql.DataSource

class BookDao(
    private val dataSource: DataSource,
    private val console: IOutpuInfo
): IBookDao {
    override fun insertBook(book: Book): Book?{
        val sql = "INSERT INTO BOOKS (id, name, author,publicYear,tematic) VALUES (?, ?, ?, ?, ?)"
        return try{
        //    DataBase.getConnection().use { conn ->
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, book.id)
                    stmt.setString(2, book.name)
                    stmt.setString(3, book.author)
                    stmt.setString(4, book.publicYear)
                    stmt.setString(5, book.tematic)
                    val rs = stmt.executeUpdate()

                    if (rs == 1){
                        book
                    }else {
                        null
                    }

                }
            }
        }catch (e: SQLException){
            null
        }
    }


    override fun update(book: Book): Book? {
        val sql = "UPDATE BOOKS SET name = ?, author = ?, publicyear = ?, tematic = ? WHERE id = ?"
        return try {
         //   DataBase.getConnection().use { conn ->
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, book.name)
                    stmt.setString(2, book.author)
                    stmt.setString(3, book.publicYear)
                    stmt.setString(4, book.tematic)
                    stmt.setString(5, book.id)
                    val rs = stmt.executeUpdate()

                    if (rs == 1){
                        book
                    }else {
                        console.showMessage("*Error* inset query failed! ($rs records)")
                        null
                    }
                }
            }
        }catch (e:SQLException){
            console.showMessage("*Error*, insert query failed! ${e.message}")
            null
        }
    }

    override fun deleteById(id: String):Boolean {
        val sql = "DELETE FROM BOOKS WHERE id = ?"
        return try{
    //        DataBase.getConnection().use { conn ->
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, id.toString())
                    return (stmt.executeUpdate() == 1)
                }
            }
        }catch (e: SQLException){
            console.showMessage("*Error*, insert query failed! ${e.message}")
            false
        }
    }


    override fun selectById(id: String): Book? {
        val sql = "SELECT * FROM BOOKS WHERE id = ?"
        return try{
         //   DataBase.getConnection().use { conn ->
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, id.toString())
                    val rs = stmt.executeQuery()
                    if (rs.next()) {
                        Book(
                            id = rs.getString("id"),
                            name = rs.getString("name"),
                            author = rs.getString("author"),
                            publicYear = rs.getString("publicyear"),
                            tematic = rs.getString("tematic")
                        )
                    } else {
                        null
                    }
                }
            }
        }catch (e: SQLException){
            console.showMessage("*Error*, insert query failed! ${e.message}")
            null
        }
    }

    override fun getAll(): List<Book>? {
        val sql = "SELECT * FROM BOOKS"
        return try {
            //DataBase.getConnection().use { conn ->
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    val rs = stmt.executeQuery()
                    val books = mutableListOf<Book>()
                    while (rs.next()) {
                        books.add(
                            Book(
                                id = rs.getString("id"),
                                name = rs.getString("name"),
                                author = rs.getString("author"),
                                publicYear = rs.getString("publicyear"),
                                tematic = rs.getString("tematic")
                            )
                        )
                    }
                    books
                }
            }
        }catch (e: SQLException){
            console.showMessage("*Error*, insert query failed! ${e.message}")
            null
        }
    }



}