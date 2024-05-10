import java.util.UUID

data class Book(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val author: String,
    val publicYear: String,
    val tematic: String
)