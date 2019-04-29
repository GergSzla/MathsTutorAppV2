package org.wit.mathstutorappv2.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MTAModel(var id: Long = 0,
                    var name: String = "",
                    var minNum: String = "", //minimum number range
                    var maxNum: String = "", //maximum number range
                    var type: String = "", //type of challenge eg.Addition, Subtraction etc.
                    var make: String ="") : Parcelable  //make of challenge eg. Default (auto-made), Custom