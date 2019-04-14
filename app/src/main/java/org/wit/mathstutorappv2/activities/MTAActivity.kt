package org.wit.mathstutorappv2.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.wit.mathstutorappv2.R
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
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
        //setSupportActionBar(toolbarAdd)

        info("Placemark Activity started..")

        app = application as MainApp

        if (app.challenges.findAll().isEmpty()) {
            //default challenges
            //default addition
            challenge.name = "Addition"
            challenge.minNum = "1"
            challenge.maxNum = "100"
            challenge.make = "default"
            app.challenges.create(challenge.copy())

            //default subtraction
            challenge.name = "Subtraction"
            challenge.minNum = "1"
            challenge.maxNum = "100"
            challenge.make = "default"
            app.challenges.create(challenge.copy())

            //default division
            challenge.name = "Division"
            challenge.minNum = "1"
            challenge.maxNum = "100"
            challenge.make = "default"
            app.challenges.create(challenge.copy())

            //default multiplication
            challenge.name = "Multiplication"
            challenge.minNum = "1"
            challenge.maxNum = "100"
            challenge.make = "default"
            app.challenges.create(challenge.copy())
        }



        radio_group_type.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
                Toast.makeText(applicationContext," Type changed to : ${radio.text}",
                    Toast.LENGTH_SHORT).show()
            })

        if (intent.hasExtra("placemark_edit")) {
            edit = true
            challenge = intent.extras.getParcelable<MTAModel>("placemark_edit")

            var id: Int = radio_group_type.checkedRadioButtonId
            if (id!=-1){ // If any radio button checked from radio group
                // Get the instance of radio button using id
                val radio:RadioButton = findViewById(id)
                Toast.makeText(applicationContext,"Challenge Type : ${radio.text}",
                    Toast.LENGTH_SHORT).show()
                challenge.type = radio.text.toString()


            }else{
                // If no radio button checked in this radio group
                Toast.makeText(applicationContext,"Challenge Type : nothing selected",
                    Toast.LENGTH_SHORT).show()
            }
            challengeName.setText(challenge.name)
            challengeMinNum.setText(challenge.minNum)
            challengeMaxNum.setText(challenge.maxNum)



            btnAdd.setText(R.string.save_challenge)
        }


        btnAdd.setOnClickListener() {

            var id: Int = radio_group_type.checkedRadioButtonId
            if (id!=-1){ // If any radio button checked from radio group
                // Get the instance of radio button using id
                val radio:RadioButton = findViewById(id)
                Toast.makeText(applicationContext,"Challenge Type : ${radio.text}",
                    Toast.LENGTH_SHORT).show()
                challenge.type = radio.text.toString()

            }else{
                // If no radio button checked in this radio group
                Toast.makeText(applicationContext,"Challenge Type : nothing selected",
                    Toast.LENGTH_SHORT).show()
            }

            challenge.name = challengeName.text.toString()
            challenge.minNum = challengeMinNum.text.toString()
            challenge.maxNum = challengeMaxNum.text.toString()
            challenge.make = "custom"

            if (challenge.name.isEmpty()) {
                toast(R.string.enter_challenge_name)
            } else {
                if (edit) {
                    app.challenges.update(challenge.copy())
                } else {

                    app.challenges.create(challenge.copy())
                }
            }
            info("add Button Pressed: $challengeName")
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }

        fun radio_button_click(view: View){
            // Get the clicked radio button instance
            val radio: RadioButton = findViewById(radio_group_type.checkedRadioButtonId)
            Toast.makeText(applicationContext,"On click : ${radio.text}",
                Toast.LENGTH_SHORT).show()
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

