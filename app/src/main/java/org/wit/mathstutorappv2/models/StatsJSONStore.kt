package org.wit.mathstutorappv2.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.jetbrains.anko.AnkoLogger
import org.wit.mathstutorappv2.helpers.exists
import org.wit.mathstutorappv2.helpers.read
import org.wit.mathstutorappv2.helpers.write

var gson = Gson()
val JSON_FILE_STATS = "stats.json"
val gsonBuilderStats = GsonBuilder().setPrettyPrinting().create()

class StatsJSONStore: StatsStore, AnkoLogger{

    val context: Context


    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE_STATS)) {
            deserialize()
        }
    }

    override fun getStats(): StatsModel {
        return statss
    }

    override fun saveStats(statss: StatsModel){
        getStats()
        var foundStats: StatsModel = getStats()
        if(foundStats != null) {
            foundStats.sessionsTaken = statss.sessionsTaken
            foundStats.sessionsPassed = statss.sessionsPassed
            foundStats.sessionsFailed = statss.sessionsFailed
            foundStats.totalQuestionsAnswered = statss.totalQuestionsAnswered
            foundStats.totalAnsweredCorrect = statss.totalAnsweredCorrect
            foundStats.totalAnsweredWrong = statss.totalAnsweredWrong
        }
        serialize()
    }

    private fun serialize(){
        val jsonString = gsonBuilder.toJson(statss)
        write(context, JSON_FILE_STATS, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE_STATS)
        statss = gson.fromJson(jsonString, statss::class.java)
    }
}