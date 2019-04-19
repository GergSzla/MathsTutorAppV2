package org.wit.mathstutorappv2.models

data class Question (var noX:String ="",
                     var symbol:String = "",
                      var noY:String = ""
                     )

object Supplier{

    val questions = listOf<Question>(Question(noX = "", symbol = "", noY = ""))
}