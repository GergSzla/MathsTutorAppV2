package org.wit.mathstutorappv2.models

data class Question (var id:Int = 0,
                     var noX:String ="",
                     var symbol:String = "",
                     var noY:String = "",
                     var questionAnswer:Int = 0
                     )

object Supplier{

    val questions = listOf<Question>(Question(id = 0, noX = "", symbol = "", noY = "", questionAnswer = 0))
}