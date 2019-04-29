package org.wit.mathstutorappv2.models

data class Question (var id:Int = 0,
                     var noX:String ="",    //number a in ... a + b = c
                     var symbol:String = "",
                     var noY:String = "",   //number b in ... a + b = c
                     var questionAnswer:Double = 0.0  //(answer) number c in ... a + b = c
                     )