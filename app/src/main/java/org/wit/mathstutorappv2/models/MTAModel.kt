package org.wit.mathstutorappv2.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MTAModel(var id: Long = 0,
                    var name: String = "",
                    var minNum: String = "",
                    var maxNum: String = "",
                    var type: String = "",
                    var make: String ="") : Parcelable