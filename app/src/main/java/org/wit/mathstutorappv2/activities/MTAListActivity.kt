package org.wit.mathstutorappv2.activities

/*
This page is responsible for listing the card challenges in the recyclerViews.
Also adds Click/LongClick functionality to each card.
 */

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_mta_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.wit.mathstutorappv2.R
import org.wit.mathstutorappv2.main.MainApp
import org.wit.mathstutorappv2.models.MTAModel
import kotlinx.android.synthetic.main.card_mta.challengeName as challengeName1

class MTAListActivity : AppCompatActivity(), MTAListener {

    lateinit var app: MainApp
    var challenge = MTAModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mta_list)
        app = application as MainApp

        toolbarMain.title = title
        //setSupportActionBar(toolbarMain)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = MTAAdapter(app.challenges.findAll(), this)
        loadChallenges()




    }



    private fun loadChallenges() {

        /*
        The following if statement creates default challenges as soon as the application is started.
        This is done if the challenges list is empty and contains no challenges.
        The user can delete these "default" challenges, but once there is no challenges left, they will be
        recreated.
         */
        if (app.challenges.findAll().isEmpty()) {
            //default challenges
            //default addition
            challenge.name = "Addition"
            challenge.minNum = "1"
            challenge.maxNum = "400"
            challenge.type = "Addition (+)"
            challenge.make = "default"
            app.challenges.create(challenge.copy())

            //default subtraction
            challenge.name = "Subtraction"
            challenge.minNum = "1"
            challenge.maxNum = "100"
            challenge.type = "Subtraction (-)"
            challenge.make = "default"
            app.challenges.create(challenge.copy())

            //default division
            challenge.name = "Division"
            challenge.minNum = "1"
            challenge.maxNum = "70"
            challenge.type = "Division (÷)"
            challenge.make = "default"
            app.challenges.create(challenge.copy())

            //default multiplication
            challenge.name = "Multiplication"
            challenge.minNum = "1"
            challenge.maxNum = "50"
            challenge.type = "Multiplication (×)"
            challenge.make = "default"
            app.challenges.create(challenge.copy())

        }
        showChallenges( app.challenges.findAll())

    }

    fun showChallenges (challenges: List<MTAModel>) {
        recyclerView.adapter = MTAAdapter(challenges, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            /*
            3 buttons are shown in the menu bar
            The user can add a new challenge (item_add loads up MTAActivity),
            View their statistics (page_stats loads up MTAStatsActivity),
            View youtube videos through the YouTube API (youtube_videos loads up MTAYoutubeActivity)
             */
            R.id.item_add -> startActivityForResult<MTAActivity>(0)
            R.id.page_stats -> startActivity<MTAStatsActivity>()
            R.id.youtube_videos -> startActivity<MTAYoutubeActivity>()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onMTAHold(challenge: MTAModel) {
        /*
        When a Challenge card is held, this function runs MTAActivity with the intent of editing a challenge ("challenge_edit ")
        for a particular challenge
         */
        startActivityForResult(intentFor<MTAActivity>().putExtra("challenge_edit ", challenge), 0)
    }

    override fun onMTAClick(challenge: MTAModel) {
        /*
        When a Challenge card is clicked, this function runs MTAQuestionsListActivity with the intent of starting a challenge ("challenge_start ")
        for a particular challenge
         */
        startActivityForResult(intentFor<MTAQuestionsListActivity>().putExtra("challenge_start ", challenge), 0) //change to MTAActivity? Start Challenge from there ?

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadChallenges()
        super.onActivityResult(requestCode, resultCode, data)
    }
}
