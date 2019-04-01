package org.wit.mathstutorappv2.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.wit.mathstutorappv2.R
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_mta.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.mathstutorappv2.main.MainApp
import org.wit.mathstutorappv2.models.MTAModel

class MTAActivity : AppCompatActivity(), AnkoLogger {

    var challenge = MTAModel()
    lateinit var app: MainApp
    var edit = false;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mta)
        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)
        info("Placemark Activity started..")

        app = application as MainApp

        if (intent.hasExtra("placemark_edit")) {
            edit = true
            challenge = intent.extras.getParcelable<MTAModel>("placemark_edit")
            placemarkTitle.setText(challenge.title)
            description.setText(challenge.description)

            btnAdd.setText(R.string.save_placemark)
        }

        btnAdd.setOnClickListener() {
            challenge.title = placemarkTitle.text.toString()
            challenge.description = description.text.toString()
            if (challenge.title.isEmpty()) {
                toast(R.string.enter_placemark_title)
            } else {
                if (edit) {
                    app.challenges.update(challenge.copy())
                } else {

                    app.challenges.create(challenge.copy())
                }
            }
            info("add Button Pressed: $placemarkTitle")
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }




    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_mta, menu)
        if (edit && menu != null) menu.getItem(0).setVisible(true)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_delete -> {
                app.challenges.delete(challenge)
                finish()
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

