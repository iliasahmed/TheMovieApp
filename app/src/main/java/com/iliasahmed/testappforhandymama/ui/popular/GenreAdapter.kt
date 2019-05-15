package com.iliasahmed.testappforhandymama.ui.popular

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.iliasahmed.testappforhandymama.R
import com.iliasahmed.testappforhandymama.helper.DataFetchingListener
import com.iliasahmed.testappforhandymama.model.network.GenreModel
import com.iliasahmed.testappforhandymama.model.network.MovieModel
import com.iliasahmed.testappforhandymama.utils.UrlUtils
import com.orhanobut.logger.Logger
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class GenreAdapter(var genreList: List<GenreModel>, var context: Activity, val listener: OnGenreItemClickedListener<GenreModel>): RecyclerView.Adapter<GenreAdapter.GenreViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return GenreViewHolder(inflater, parent, listener, genreList)
    }

    override fun getItemCount(): Int {
        return genreList.size
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val genre: GenreModel = genreList.get(position)
        holder.bind(genre)
    }

    interface OnGenreItemClickedListener<GenreModel>{
        fun onGenreItemClicked(genre: GenreModel)
    }


    class GenreViewHolder(inflater: LayoutInflater, parent: ViewGroup, listener: OnGenreItemClickedListener<GenreModel>, genreList: List<GenreModel>): RecyclerView.ViewHolder(inflater.inflate(R.layout.genre_item_view, parent, false)){
        private var tvName: TextView? = null
        private var checkBox: CheckBox? = null

        init {
            tvName = itemView.findViewById(R.id.tvGenreName)
            checkBox = itemView.findViewById(R.id.checkbox)

            itemView.setOnClickListener(View.OnClickListener {
                checkBox!!.isChecked = true
                listener.onGenreItemClicked(genreList.get(adapterPosition))
            })

            checkBox!!.setOnClickListener(View.OnClickListener {
                listener.onGenreItemClicked(genreList.get(adapterPosition))
            })

        }

        fun bind(genre: GenreModel) {
            tvName?.text = genre.name
        }


    }
}