package org.wit.mathstutorappv2.activities

/*
This adds functionality to activity_results_add.xml
Simply takes a single input from a user (their score from the challenge they took)
and performs calculations and saves that sessions score to the stats page.
 */

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_mta.toolbarAdd
import kotlinx.android.synthetic.main.activity_results_add.*
import org.jetbrains.anko.AnkoLogger
import org.wit.mathstutorappv2.R
import org.wit.mathstutorappv2.main.MainApp
import org.wit.mathstutorappv2.models.MTAModel
import org.wit.mathstutorappv2.models.statss

class ResultsAddActivity : AppCompatActivity(), AnkoLogger {

    lateinit var app: MainApp
    var challenge = MTAModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results_add)
        toolbarAdd.title = title
        app = application as MainApp

        /*
        When btnAddScore button is pressed:
         */
        btnAddScore.setOnClickListener {
               var lastSessionScore = txtScore.text.toString()
                statss.sessionsTaken += 1           //increments sessionsTaken as a new session has just been finished

                if(lastSessionScore.toInt() > 4 ){  //if the last session score is greater than 4, session was passed
                    statss.sessionsPassed += 1      //increments sessionsPassed by 1 each time the last session was passed

                } else if(lastSessionScore.toInt() <= 4){ //if the last session score is less than or = 4, session was failed
                    statss.sessionsFailed += 1            //increments sessionsFailed by 1 each time a session was failed
                }

                statss.totalQuestionsAnswered +=10      //each challenge contains 10 questions thus total questions/session increments by 10
                statss.totalAnsweredCorrect += lastSessionScore.toInt() //increments by the users inserted score
                statss.totalAnsweredWrong += (10-lastSessionScore.toInt()) // increments totalAnsweredWrong by the remainder of (10(questions) minus the
                                                                            //amount of questions answered correctly

                app.stats.saveStats(statss)             //saves stats to json file
                finish()
        }
    }
}