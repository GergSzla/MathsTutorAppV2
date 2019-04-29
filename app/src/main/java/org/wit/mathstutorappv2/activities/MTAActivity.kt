package org.wit.mathstutorappv2.activities

/*
This class provides functionality to the activity_mta.xml layout. This is for creating new challenge cards.
 */

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.wit.mathstutorappv2.R
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_mta.*
import kotlinx.android.synthetic.main.activity_mta.challengeMaxNum
import kotlinx.android.synthetic.main.activity_mta.challengeMinNum
import kotlinx.android.synthetic.main.activity_mta.challengeName
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.longToast
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

        info("MTA Activity started..")

        app = application as MainApp




        /*
        REFERENCE: https://android--code.blogspot.com/2018/02/android-kotlin-radiogroup-and.html
        Radio buttons for the challenges.
         */
        radio_group_type.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)
                Toast.makeText(applicationContext," Type changed to : ${radio.text}",
                    Toast.LENGTH_SHORT).show()
            })

        if (intent.hasExtra("challenge_edit ")) {
            edit = true
            challenge = intent.extras.getParcelable<MTAModel>("challenge_edit ")

            var id: Int = radio_group_type.checkedRadioButtonId
                if (id!=-1){                                         // If any radio button checked from radio group
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




        btnAdd.setOnClickListener {                 /*
                                                    button to add challenges
                                                    reused buttons.
                                                    */
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


            /*
            minimum range cannot exceed 500, this statement autosets the minimum number to 500
            if it has exceeded 500.
             */
            if(challenge.minNum.toInt() < 0  || challenge.minNum.toInt() > 500 ){

                var maxMinNum = 500
                challenge.minNum = maxMinNum.toString()
                longToast("Minimum number/range cannot exceed 500! \nNOTE: Minimum number auto-set to: 500")
            }

            /*
            max number cannot be less than the minimum range.
            If it is less, the statement sets it to 20 int more than the min no
            for range.
             */
            if(challenge.maxNum.toInt() <= challenge.minNum.toInt()){
                var minMaxNum = challenge.minNum.toInt() + 20
                challenge.maxNum = minMaxNum.toString()
                longToast("Maximum number/range cannot be less than minimum number! \nNOTE: Maximum number has been set to ${challenge.maxNum} ")
            }

            /*
            maximum range cannot exceed 5000, this statement autosets the minimum number to 5000
            if it has exceeded 5000.
             */
            if(challenge.maxNum.toInt() > 5000 ){
                var maxMaxNum = 5000
                challenge.maxNum = maxMaxNum.toString()
                longToast("Maximum number/range cannot exceed 5000! \nNOTE: Maximum number auto-set to: 5000")
            }

            /*
            No fields can be left empty when creating a challenge
             */
            if (challenge.name.isEmpty() || challenge.minNum.isEmpty() || challenge.maxNum.isEmpty() || challenge.type.isEmpty()) {
                toast(R.string.enter_challenge_name_num)
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

