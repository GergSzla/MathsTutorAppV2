package org.wit.mathstutorappv2.models

import com.google.gson.annotations.SerializedName


data class StatsModel (
    @SerializedName("sessionsTaken") var sessionsTaken:Int = 0,
    @SerializedName("sessionsPassed")var sessionsPassed:Int = 0,
    @SerializedName("sessionsFailed")var sessionsFailed:Int = 0,

    @SerializedName("totalQuestionsAnswered")var totalQuestionsAnswered:Int = 0,
    @SerializedName("totalAnsweredCorrect")var totalAnsweredCorrect:Int = 0,
    @SerializedName("totalAnsweredWrong")var totalAnsweredWrong: Int = 0

)



    var statss = StatsModel(sessionsTaken = 0, sessionsPassed = 0, sessionsFailed = 0, totalQuestionsAnswered = 0, totalAnsweredCorrect = 0, totalAnsweredWrong= 0)