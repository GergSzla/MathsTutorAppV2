package org.wit.mathstutorappv2.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_mta_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.wit.mathstutorappv2.R
import org.wit.mathstutorappv2.main.MainApp
import org.wit.mathstutorappv2.models.MTAModel

class MTAListActivity : AppCompatActivity(), MTAListener {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mta_list)
        app = application as MainApp
        toolbarMain.title = title
        setSupportActionBar(toolbarMain)

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
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onMTAClick(challenge: MTAModel) {
        startActivityForResult(intentFor<MTAActivity>().putExtra("placemark_edit", challenge), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadChallenges()
        super.onActivityResult(requestCode, resultCode, data)
    }
}
