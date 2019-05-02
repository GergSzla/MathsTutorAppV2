package org.wit.mathstutorappv2.activities

/*
This page is responsible for listing the card questions in the recyclerViews.
New questions generated every time the user enters into a challenge.
 */

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_mta_list.toolbarMain
import kotlinx.android.synthetic.main.activity_questions_list.*
import org.jetbrains.anko.longToast
import org.wit.mathstutorappv2.main.MainApp
import org.wit.mathstutorappv2.models.MTAModel
import org.jetbrains.anko.startActivity
import org.wit.mathstutorappv2.R
import org.wit.mathstutorappv2.models.Question
//import kotlin.String as String1


class MTAQuestionsListActivity : AppCompatActivity() {

    lateinit var app: MainApp
    var challenge = MTAModel()
    var question = Question()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions_list)
        app = application as MainApp

        toolbarMain.title = title

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerViewQuestion.layoutManager = layoutManager

        val adapter = QuestionsAdapter(this, app.questions.findAllQuestions())
        recyclerViewQuestion.adapter = adapter

        if (intent.hasExtra("challenge_start ")) {
            app.challenges.findAll()
            challenge = intent.extras.getParcelable<MTAModel>("challenge_start ") //gets the challenge that was selected
            app.questions.deleteQuestions(question) //if any questions are left in the list from another challenge, this clears it all to start from scratch
            longToast("${challenge.name} Started!")

            /*
            takes in the min and max numbers of the challenge selected
             */
            var QMinNo = challenge.minNum
            var QMaxNo = challenge.maxNum

            // q = 10 questions
            for (q in 0 until 10) {
                /*
                gets two random numbers between the min and max number values
                 */
                var randomNumber1 = (QMinNo.toInt()..QMaxNo.toInt()).random()
                var randomNumber2 = (QMinNo.toInt()..QMaxNo.toInt()).random()


                /*
                this if statement converts the detailed type of the challenge symbol to a less complicated one
                to be used for the questions. Thus the questions look more like maths and less like a sentence.
                 */
                if (challenge.type.contains("Addition (+)")) {

                    var newSymbol = "+"

                    question.id += 1
                    question.noX = randomNumber1.toString()
                    question.symbol = newSymbol
                    question.noY = randomNumber2.toString()
                    question.questionAnswer = randomNumber1.toDouble() + randomNumber2.toDouble()
                    app.questions.createQuestions(question.copy())


                } else if (challenge.type.contains("Subtraction (-)")) {

                    var newSymbol = "-"

                    question.id += 1
                    question.noX = randomNumber1.toString()
                    question.symbol = newSymbol
                    question.noY = randomNumber2.toString()
                    question.questionAnswer = randomNumber1.toDouble() - randomNumber2.toDouble()
                    app.questions.createQuestions(question.copy())

                } else if (challenge.type.contains("Division (÷)")) {

                    var newSymbol = "÷"

                    question.id += 1
                    question.noX = randomNumber1.toString()
                    question.symbol = newSymbol
                    question.noY = randomNumber2.toString()
                    question.questionAnswer = randomNumber1.toDouble() / randomNumber2.toDouble()
                    app.questions.createQuestions(question.copy())

                } else if (challenge.type.contains("Multiplication (×)")) {

                    var newSymbol = "×"

                    question.id += 1
                    question.noX = randomNumber1.toString()
                    question.symbol = newSymbol
                    question.noY = randomNumber2.toString()
                    question.questionAnswer = randomNumber1.toDouble() * randomNumber2.toDouble()
                    app.questions.createQuestions(question.copy())


                }

            }
            loadQuestions()
        }
    }

    private fun loadQuestions() {
        showQuestions(app.questions.findAllQuestions())
    }

    fun showQuestions(questions: List<Question>) {
        recyclerViewQuestion.adapter = QuestionsAdapter(this, questions)
        recyclerViewQuestion.adapter?.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_challenge, menu)
        if (menu != null) menu.getItem(0).setVisible(true)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_cancel -> {

                /*
                 if canceled, questions are deleted,
                 if reopened, new questions are generated
                 */

                app.questions.deleteQuestions(question)
                finish()
            }
            R.id.item_finish -> {

                //starts activity for ResultAddActivity.kt
                startActivity<ResultsAddActivity>()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}