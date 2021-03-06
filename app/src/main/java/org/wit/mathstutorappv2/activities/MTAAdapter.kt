package org.wit.mathstutorappv2.activities


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_mta.view.*
import org.wit.mathstutorappv2.R
import org.wit.mathstutorappv2.models.MTAModel

interface MTAListener {
    fun onMTAHold(challenge: MTAModel)
    fun onMTAClick(challenge: MTAModel)
}

class MTAAdapter constructor(private var challenges: List<MTAModel>,
                             private val listener: MTAListener) : RecyclerView.Adapter<MTAAdapter.MainHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_mta, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val challenge = challenges[holder.adapterPosition]
        holder.bind(challenge, listener)
    }

    override fun getItemCount(): Int = challenges.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(challenge: MTAModel, listener: MTAListener) {
            itemView.challengeName.text = challenge.name
            itemView.challengeMinNum.text = challenge.minNum
            itemView.challengeMaxNum.text = challenge.maxNum
            itemView.challengeType.text = challenge.type
            itemView.challengeMake.text = challenge.make


            /*
            If the card is held, the onMTAHold(challenge) will take in the challenge that was held
             and the onMTAHold() function will run (MTAListActivity.kt). This will start the activity with the
             intent of editing that particular card.
             */
            itemView.setOnLongClickListener {
                listener.onMTAHold(challenge)
                true
            }

            /*
            If the card is clicked/pressed, the onMTAClick(challenge) will take in the challenge that was clicked
             and the onMTAClick() function will run (MTAListActivity.kt). This will start the activity with the
             intent of starting a challenge.
             */
            itemView.setOnClickListener {
                listener.onMTAClick(challenge)
            }
        }
    }
}