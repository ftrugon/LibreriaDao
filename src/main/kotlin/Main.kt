import dao.BookDao
import db_conn.DataSourceFactory
import output.Console
import service.BookServiceImpl

fun main() {

    val console = Console()

    val dataSource = DataSourceFactory.getDS(DataSourceFactory.DataSourceType.HIKARI)

    // Creamos la instancia de bookDAO
    val bookDao = BookDao(dataSource,console)

    // Creamos la instancia de bookService
    val bookService = BookServiceImpl(bookDao)

    // Creamos un nuevo usuario
    val newBook = Book(name = "Como subir de hierro", author = "Riot Games", publicYear = "2024", tematic = "Informativo")
    var createdBook = bookService.create(newBook)
    console.showMessage("Created book: ${createdBook ?: "Error"}")

    // Obtenemos un usuario por su ID
    val foundBook =
        if (createdBook != null) {
            bookService.getById(createdBook.id)
        } else {
            null
        }
    console.showMessage("Found book: ${foundBook ?: "Error"}")

    // Actualizamos el usuario
    val updatedBook = foundBook?.copy(name = "Como subir de bronze")
    val savedBook =
        if (updatedBook != null){
            bookService.update(updatedBook)
        }else {
            null
        }
    console.showMessage("Updated book: ${savedBook?: "Error"}")

    val otherBook = Book(name = "Hello World", author = "Maria Gomez", publicYear = "2024", tematic = "Informativo")
    createdBook = bookService.create( otherBook )
    console.showMessage("Created book: ${createdBook?: "Error"}")


    // Obtenemos todos los usuarios
    var allBooks = bookService.getAll()
    console.show(allBooks)

    // Eliminamos el usuario
    if (savedBook != null) {
        if (bookService.delete(savedBook.id))console.showMessage("book delete OK!")
        else console.showMessage("book delete not OK ._.")
    }

    // Obtenemos todos los usuarios
    allBooks = bookService.getAll()
    console.show(allBooks)

    // Eliminamos el usuario
    if (savedBook != null) {
        if (bookDao.deleteById(savedBook.id))console.showMessage("book delete OK!")
        else console.showMessage("book delete not OK ._.")
    }
    // Obtenemos todos los usuarios
    allBooks = bookService.getAll()
    console.show(allBooks)
}
