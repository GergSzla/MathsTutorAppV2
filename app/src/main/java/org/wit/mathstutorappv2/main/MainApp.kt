package org.wit.mathstutorappv2.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.longToast
import org.wit.mathstutorappv2.models.*

class MainApp : Application(), AnkoLogger {

    lateinit var challenges: MTAStore
    lateinit var questions: QuestionStore
    lateinit var stats: StatsStore

    override fun onCreate() {
        super.onCreate()
        challenges = MTAJSONStore(applicationContext)
        questions = MTAJSONStore(applicationContext)
        stats = StatsJSONStore(applicationContext)

        info("MTA App started")
        longToast("Hold card to edit." +
                "\nTap to begin challenge.")
    }
}