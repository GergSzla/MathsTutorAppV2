package org.wit.mathstutorappv2.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_mta.toolbarAdd
import kotlinx.android.synthetic.main.activity_results_add.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast
import org.wit.mathstutorappv2.R
import org.wit.mathstutorappv2.main.MainApp
import org.wit.mathstutorappv2.models.MTAModel
import org.wit.mathstutorappv2.models.Question

class ResultsAddActivity : AppCompatActivity(), AnkoLogger {

    lateinit var app: MainApp
    var challenge = MTAModel()
    var question = Question ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results_add)



        toolbarAdd.title = title


        app = application as MainApp

        btnAddScore.setOnClickListener {
            toast("Button Pressed")
        }
    }

}