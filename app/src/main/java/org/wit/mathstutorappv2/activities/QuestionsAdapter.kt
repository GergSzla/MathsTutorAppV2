package org.wit.mathstutorappv2.activities

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_challenge.view.*
import org.wit.mathstutorappv2.R
import org.wit.mathstutorappv2.models.Question
import org.wit.mathstutorappv2.models.Supplier.questions

class QuestionsAdapter(val context: Context, val questions: List<Question>) : RecyclerView.Adapter<QuestionsAdapter.questionsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): questionsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_challenge, parent, false)
        return questionsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    override fun onBindViewHolder(holder: questionsViewHolder, position: Int) {
        val question = questions[position]
        holder.setData(question, position)
    }
    inner class questionsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(question: Question?, pos:Int) {
            itemView.numberX.text = question!!.noX
            itemView.symbol.text = question!!.symbol
            itemView.numberY.text = question!!.noY
            //itemView.challengeAnswer.text = question.answer ---> Get user input + Compare to actual answer
        }

    }
}