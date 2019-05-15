package com.iliasahmed.testappforhandymama.ui

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.iliasahmed.testappforhandymama.R
import com.iliasahmed.testappforhandymama.model.network.MovieModel
import com.iliasahmed.testappforhandymama.utils.UrlUtils
import com.orhanobut.logger.Logger
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class MovieAdapter(val movieList: List<MovieModel>, context: Activity): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie: MovieModel = movieList.get(position)
        holder.bind(movie)
    }


    class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup): RecyclerView.ViewHolder(inflater.inflate(R.layout.movie_view, parent, false)){
        private var tvRating: TextView? = null
        private var tvName: TextView? = null
        private var tvYear: TextView? = null
        private var ivMovie: ImageView? = null


        init {
            tvName = itemView.findViewById(R.id.tvMovieName)
            tvRating = itemView.findViewById(R.id.tvRating)
            tvYear = itemView.findViewById(R.id.tvReleaseYear)
            ivMovie = itemView.findViewById(R.id.ivMovie)
        }

        fun bind(movie: MovieModel) {
            tvName?.text = movie.title
            tvRating?.text = movie.vote_average.toString()
            tvYear?.text = getConvertedTime(movie.release_date)

            Glide.with(itemView)
                .load(UrlUtils.IMAGE_BASE+movie.poster_path)
                .apply(RequestOptions().placeholder(R.drawable.placeholder))
                .into(this.ivMovie!!)
        }

        private fun getConvertedTime(time: String): String {
            var dateTime = ""
            var dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
            var date = Date()
            try {
                date = dateFormat.parse(time)
            } catch (e: ParseException) {
                e.printStackTrace()
            } catch (e: Exception) {
                Logger.d(e.message)
            }

            dateFormat = SimpleDateFormat("yyyy", Locale.US)
            dateTime = dateFormat.format(date)


            return dateTime
        }
    }
}