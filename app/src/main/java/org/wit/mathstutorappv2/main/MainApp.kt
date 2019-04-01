package org.wit.mathstutorappv2.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.mathstutorappv2.models.MTAJSONStore
import org.wit.mathstutorappv2.models.MTAStore

class MainApp : Application(), AnkoLogger {

    lateinit var challenges: MTAStore

    override fun onCreate() {
        super.onCreate()
        challenges = MTAJSONStore(applicationContext)
        info("Placemark started")
    }
}