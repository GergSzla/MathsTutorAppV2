package org.wit.mathstutorappv2.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_mta.toolbarAdd
import kotlinx.android.synthetic.main.activity_results_add.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.wit.mathstutorappv2.R
import org.wit.mathstutorappv2.main.MainApp
import org.wit.mathstutorappv2.models.MTAModel
import org.wit.mathstutorappv2.models.Question
import org.wit.mathstutorappv2.models.StatsModel
import org.wit.mathstutorappv2.models.statss

class ResultsAddActivity : AppCompatActivity(), AnkoLogger {

    lateinit var app: MainApp
    var challenge = MTAModel()
    var stats = StatsModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results_add)



        toolbarAdd.title = title


        app = application as MainApp

        btnAddScore.setOnClickListener {

           var lastSessionScore = txtScore.text.toString()

            statss.sessionsTaken += 1

            if(lastSessionScore.toInt() > 4 ){
                statss.sessionsPassed += 1
            } else if(lastSessionScore.toInt() <= 4){
                statss.sessionsFailed += 1
            }

            statss.totalQuestionsAnswered +=10
            statss.totalAnsweredCorrect += lastSessionScore.toInt()
            statss.totalAnsweredWrong += (10-lastSessionScore.toInt())

            app.stats.saveStats(statss)
            finish()

        }
    }

}