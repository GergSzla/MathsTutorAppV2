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
        getStats() //gets current stats
        var foundStats: StatsModel = getStats() //assigns current stats
        if(foundStats != null) {
            foundStats.sessionsTaken = statss.sessionsTaken  //updates all statistics values
            foundStats.sessionsPassed = statss.sessionsPassed
            foundStats.sessionsFailed = statss.sessionsFailed
            foundStats.totalQuestionsAnswered = statss.totalQuestionsAnswered
            foundStats.totalAnsweredCorrect = statss.totalAnsweredCorrect
            foundStats.totalAnsweredWrong = statss.totalAnsweredWrong
        }
        serialize() //writes to json file
    }

    override fun deleteStats (statss: StatsModel){
        getStats()
        var foundStats: StatsModel = getStats()
        if(foundStats != null) { //on btnReset.click: all values set to 0
            foundStats.sessionsTaken = 0
            foundStats.sessionsPassed = 0
            foundStats.sessionsFailed = 0
            foundStats.totalQuestionsAnswered =0
            foundStats.totalAnsweredCorrect = 0
            foundStats.totalAnsweredWrong = 0
        }
        serialize() //saves again to json
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