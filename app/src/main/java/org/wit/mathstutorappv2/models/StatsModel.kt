package org.wit.mathstutorappv2.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class StatsModel (
                  var totalScore:Int = 0,
                  var sessionsTaken:Int = 0,

                  var addResultAvg: String ="",
                  var subResultAvg: String ="",
                  var divResultAvg: String ="",
                  var multResultAvg: String ="",

                  var addCorrect: String ="",
                  var addWrong: String ="",

                  var subCorrect: String ="",
                  var subWrong: String ="",

                  var divCorrect: String ="",
                  var divWrong: String ="",

                  var multCorrect: String ="",
                  var multWrong: String =""




                  ) : Parcelable