import dao.BookDao
import db_conn.DataSourceFactory
import output.Console
import service.BookServiceImpl

fun main() {

    val console = Console()

    val dataSource = DataSourceFactory.getDS(DataSourceFactory.DataSourceType.HIKARI)

    // Creamos la instancia de UserDAO
    val bookDao = BookDao(dataSource,console)

    // Creamos la instancia de UserService
    val bookService = BookServiceImpl(bookDao)

    // Creamos un nuevo usuario
    val newBook = Book(name = "Como subir de hierro", author = "Riot Games", publicYear = "2024", tematic = "Informativo")
    var createdBook = bookService.insertBook(newBook)
    console.showMessage("Created user: ${createdBook ?: "Error"}")

    // Obtenemos un usuario por su ID
    val foundBook =
        if (createdBook != null) {
            bookService.selectById(createdBook.id)
        } else {
            null
        }
    console.showMessage("Found user: ${foundBook ?: "Error"}")

    // Actualizamos el usuario
    val updatedBook = foundBook?.copy(name = "Como subir de bronze")
    val savedBook =
        if (updatedBook != null){
            bookService.update(updatedBook)
        }else {
            null
        }
    console.showMessage("Updated user: ${savedBook?: "Error"}")

    val otherBook = Book(name = "Hello World", author = "Maria Gomez", publicYear = "2024", tematic = "Informativo")
    createdBook = bookService.insertBook( otherBook )
    console.showMessage("Created user: ${createdBook?: "Error"}")


    // Obtenemos todos los usuarios
    var allBooks = bookService.getAll()
    console.show(allBooks)

    // Eliminamos el usuario
    if (savedBook != null) {
        if (bookService.deleteById(savedBook.id))console.showMessage("User delete OK!")
        else console.showMessage("User delete not OK ._.")
    }

    // Obtenemos todos los usuarios
    allBooks = bookService.getAll()
    console.show(allBooks)

    // Eliminamos el usuario
    if (savedBook != null) {
        if (bookDao.deleteById(savedBook.id))console.showMessage("User delete OK!")
        else console.showMessage("User delete not OK ._.")
    }
    // Obtenemos todos los usuarios
    allBooks = bookService.getAll()
    console.show(allBooks)
}
