package org.wit.mathstutorappv2.models


data class StatsModel (
    var sessionsTaken:Int = 0,
    var sessionsPassed:Int = 0,
    var sessionsFailed:Int = 0,

    var totalQuestionsAnswered:Int = 0,
    var totalAnsweredCorrect:Int = 0,
    var totalAnsweredWrong: Int = 0

)



    var statss = StatsModel(sessionsTaken = 0, sessionsPassed = 0, sessionsFailed = 0, totalQuestionsAnswered = 0, totalAnsweredCorrect = 0, totalAnsweredWrong= 0)