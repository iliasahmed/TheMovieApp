package com.iliasahmed.testappforhandymama.ui.person

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.iliasahmed.testappforhandymama.R
import com.iliasahmed.testappforhandymama.model.network.PersonModel
import com.iliasahmed.testappforhandymama.utils.UrlUtils

class PersonAdapter(val personList: List<PersonModel>, context: Activity) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PersonViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person: PersonModel = personList.get(position)
        holder.bind(person)
    }


    class PersonViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(R.layout.person_view, parent, false)) {
        private var tvPersonName: TextView? = null
        private var tvKnownFor: TextView? = null
        private var ivPerson: ImageView? = null


        init {
            tvPersonName = itemView.findViewById(R.id.tvPersonName)
            tvKnownFor = itemView.findViewById(R.id.tvKnownFor)
            ivPerson = itemView.findViewById(R.id.ivPerson)
        }

        fun bind(person: PersonModel) {
            tvPersonName?.text = person.name
            if (person.known_for.size > 0) {
                try {
                    tvKnownFor?.text = person.known_for.get(0).title
                }catch (e: Exception){
                    e.printStackTrace()
                }

            }


            Glide.with(itemView)
                    .load(UrlUtils.IMAGE_BASE + person.profile_path)
                    .apply(RequestOptions().placeholder(R.drawable.placeholder))
                    .into(this.ivPerson!!)
        }

    }
}