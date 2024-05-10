package db_conn

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.sql.SQLTimeoutException

object DataBase {
    class DatabaseTimeoutException(s: String) : Exception()
    class SqlErrorException(s: String) : Exception()

    private const val URL = "jdbc:h2:./default"
    private const val USER = "user"
    private const val PASSWORD = "user"

    init {
        try {
            Class.forName("org.h2.Driver")
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
    }

    fun getConnection(): Connection =
        try {
            DriverManager.getConnection(URL, USER, PASSWORD)
        } catch (e: SQLTimeoutException) {
            throw DatabaseTimeoutException("La conexión ha excedido el tiempo de espera permitido.")
        } catch (e: SQLException) {
            throw SqlErrorException("Error de SQL: ${e.message}")
        }
}