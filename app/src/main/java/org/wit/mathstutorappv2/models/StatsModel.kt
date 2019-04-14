package org.wit.mathstutorappv2.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class StatsModel (var addResultAvg: String ="",
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