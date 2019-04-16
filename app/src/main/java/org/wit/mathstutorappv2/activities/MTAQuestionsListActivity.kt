package org.wit.mathstutorappv2.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_mta_list.*
import kotlinx.android.synthetic.main.card_mta.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import org.wit.mathstutorappv2.R
import org.wit.mathstutorappv2.main.MainApp
import org.wit.mathstutorappv2.models.MTAModel

class MTAQuestionsListActivity : AppCompatActivity(){

    lateinit var app: MainApp
    var challenge = MTAModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions_list)
        app = application as MainApp

        toolbarMain.title = title
        //setSupportActionBar(toolbarMain)

       /* val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = MTAAdapter(app.challenges.findAll(), this)*/


        if (intent.hasExtra("challenge_start ")) {
            app.challenges.findAll()
            challenge = intent.extras.getParcelable<MTAModel>("challenge_start ")
            longToast("This will display the questions for the challenge : ${challenge.name} ")




        }


    }



}