package output

import Book

interface IOutpuInfo {
    fun showMessage(message:String,lineBreak:Boolean = true)
    fun show(userList: List<Book>?,message: String = "All users")
}