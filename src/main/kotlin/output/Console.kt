package output

import Book

class Console:IOutpuInfo {
    override fun showMessage(message:String, lineBreak:Boolean){
        if (lineBreak){
            println(message)
        }else print(message)
    }

    override fun show(userList: List<Book>?, message: String){
        if (userList != null){
            if(userList.isNotEmpty()){
                showMessage(message)
                userList.forEachIndexed { index, book ->
                    showMessage("\t${index +1 }. $book")
                }
            }else {
                showMessage("No users found")
            }
        }else{
            showMessage("The list was null")
        }
    }
}