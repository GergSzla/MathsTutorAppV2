package org.wit.mathstutorappv2.activities


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_mta.view.*
import org.wit.mathstutorappv2.R
import org.wit.mathstutorappv2.models.MTAModel

interface MTAListener {
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
            itemView.placemarkTitle.text = challenge.title
            itemView.description.text = challenge.description
            itemView.setOnClickListener { listener.onMTAClick(challenge) }
        }
    }
}