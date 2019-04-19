package org.wit.mathstutorappv2.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_mta_list.*
import kotlinx.android.synthetic.main.activity_mta_list.toolbarMain
import kotlinx.android.synthetic.main.activity_questions_list.*
import org.jetbrains.anko.longToast
import org.wit.mathstutorappv2.main.MainApp
import org.wit.mathstutorappv2.models.MTAModel
import kotlinx.android.synthetic.main.card_challenge.*
import org.wit.mathstutorappv2.models.Question
import org.wit.mathstutorappv2.models.Supplier
import org.wit.mathstutorappv2.models.Supplier.questions
import java.util.Collections.list
import java.util.Collections.newSetFromMap


class MTAQuestionsListActivity : AppCompatActivity(){

    lateinit var app: MainApp
    var challenge = MTAModel()
    var question = Question()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(org.wit.mathstutorappv2.R.layout.activity_questions_list)
        app = application as MainApp

        toolbarMain.title = title

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerViewQuestion.layoutManager = layoutManager

        val adapter = QuestionsAdapter(this, Supplier.questions)
        recyclerViewQuestion.adapter = adapter


        if (intent.hasExtra("challenge_start ")) {
            app.challenges.findAll()
            challenge = intent.extras.getParcelable<MTAModel>("challenge_start ")
            longToast("This will display the questions for the challenge : ${challenge.name} ")

            var QMinNo = challenge.minNum
            var QMaxNo = challenge.maxNum





            var randomNumber1 = (QMinNo.toInt()..QMaxNo.toInt()).random()
            var randomNumber2 = (QMinNo.toInt()..QMaxNo.toInt()).random()
            var QAnswer:Int

            question.noX = randomNumber1.toString()
            question.symbol = challenge.type
            question.noY = randomNumber2.toString()
            app.questions.createQuestions(question.copy())

            loadQuestions()


            // q = 10 questions
           /* for (q in 0 until 10){
                if ((randomNumber1 > QMinNo.toInt() && randomNumber1 < QMaxNo.toInt())&&(randomNumber2 > QMinNo.toInt() && randomNumber2 < QMaxNo.toInt())){
                    numberX.setText(randomNumber1.toString())
                    numberY.setText(randomNumber2.toString())
                }
            }*/



            }


        }
        private fun loadQuestions() {
            showQuestions(app.questions.findAllQuestions())

        }

        fun showQuestions(questions: List<Question>) {
            recyclerViewQuestion.adapter = QuestionsAdapter(this, questions )
            recyclerViewQuestion.adapter?.notifyDataSetChanged()

    }



}