package org.wit.mathstutorappv2.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_mta.*
import kotlinx.android.synthetic.main.activity_mta_list.*
import kotlinx.android.synthetic.main.card_mta.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast
import org.wit.mathstutorappv2.R
import org.wit.mathstutorappv2.main.MainApp
import org.wit.mathstutorappv2.models.MTAModel
import kotlinx.android.synthetic.main.card_mta.challengeName as challengeName1

class MTAListActivity : AppCompatActivity(), MTAListener {

    lateinit var app: MainApp

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
            R.id.item_add -> startActivityForResult<MTAActivity>(0)
            R.id.page_stats -> startActivity<MTAStatsActivity>()
            R.id.youtube_videos -> startActivity<MTAYoutubeActivity>()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onMTAHold(challenge: MTAModel) {
        startActivityForResult(intentFor<MTAActivity>().putExtra("challenge_edit ", challenge), 0)
    }

    override fun onMTAClick(challenge: MTAModel) {
        startActivityForResult(intentFor<MTAQuestionsListActivity>().putExtra("challenge_start ", challenge), 0) //change to MTAActivity? Start Challenge from there ?

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadChallenges()
        super.onActivityResult(requestCode, resultCode, data)
    }
}
