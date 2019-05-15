package com.iliasahmed.testappforhandymama.ui.popular

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.iliasahmed.testappforhandymama.helper.DataFetchingListener
import com.iliasahmed.testappforhandymama.model.ApiHelper
import com.iliasahmed.testappforhandymama.model.network.GenreModel
import com.iliasahmed.testappforhandymama.model.network.MovieModel
import com.iliasahmed.testappforhandymama.model.network.PersonModel
import com.iliasahmed.testappforhandymama.utils.UrlUtils
import com.orhanobut.logger.Logger
import retrofit2.Response

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    var movieLiveData: MutableLiveData<List<MovieModel>> = MutableLiveData()
    var personLiveData: MutableLiveData<List<PersonModel>> = MutableLiveData()
    var genreLiveData: MutableLiveData<List<GenreModel>> = MutableLiveData()
    var isDataLoading: Boolean = false
    var totalCount: Int = 0


    fun fetchGenreList() {
        isDataLoading = true
        ApiHelper.fetchGenreList(UrlUtils.API_KEY, object : DataFetchingListener<Response<JsonObject>> {
            override fun onDataFetched(response: Response<JsonObject>) {
                genreLiveData.value = Gson().fromJson<List<GenreModel>>(response.body()!!.get("genres"), object : TypeToken<List<GenreModel>>() {}.type)
                isDataLoading = false
            }

            override fun onFailed(status: Int) {
                movieLiveData.value = ArrayList()
                isDataLoading = false
            }

        })
    }

    fun fetchPopularMovie(page: Int) {
        isDataLoading = true
        ApiHelper.fetchPopularMovie(page, UrlUtils.API_KEY, object : DataFetchingListener<Response<JsonObject>> {
            override fun onDataFetched(response: Response<JsonObject>) {
                totalCount = Gson().fromJson(response.body()!!.get("total_results"), object : TypeToken<Int>() {}.type)
                Logger.d(totalCount)
                movieLiveData.value = Gson().fromJson<List<MovieModel>>(response.body()!!.get("results"), object : TypeToken<List<MovieModel>>() {}.type)
                isDataLoading = false
            }

            override fun onFailed(status: Int) {
                movieLiveData.value = ArrayList()
                isDataLoading = false
            }

        })
    }

    fun searchPopularMovie(page: Int, query: String) {
        isDataLoading = true
        ApiHelper.searchPopularMovie(page, UrlUtils.API_KEY, query, object : DataFetchingListener<Response<JsonObject>> {
            override fun onDataFetched(response: Response<JsonObject>) {
                totalCount = Gson().fromJson(response.body()!!.get("total_results"), object : TypeToken<Int>() {}.type)
                Logger.d(totalCount)
                movieLiveData.value = Gson().fromJson<List<MovieModel>>(response.body()!!.get("results"), object : TypeToken<List<MovieModel>>() {}.type)
                isDataLoading = false
            }

            override fun onFailed(status: Int) {
                movieLiveData.value = ArrayList()
                isDataLoading = false
            }

        })
    }

    fun fetchUpcomingMovie(page: Int) {
        isDataLoading = true
        ApiHelper.fetchUpcomingMovie(page, UrlUtils.API_KEY, object : DataFetchingListener<Response<JsonObject>> {
            override fun onDataFetched(response: Response<JsonObject>) {
                totalCount = Gson().fromJson(response.body()!!.get("total_results"), object : TypeToken<Int>() {}.type)
                Logger.d(totalCount)
                movieLiveData.value = Gson().fromJson<List<MovieModel>>(response.body()!!.get("results"), object : TypeToken<List<MovieModel>>() {}.type)
                isDataLoading = false
            }

            override fun onFailed(status: Int) {
                movieLiveData.value = ArrayList()
                isDataLoading = false
            }

        })
    }

    fun fetchNowPlayingMovie(page: Int) {
        isDataLoading = true
        ApiHelper.fetchNowPlayingMovie(page, UrlUtils.API_KEY, object : DataFetchingListener<Response<JsonObject>> {
            override fun onDataFetched(response: Response<JsonObject>) {
                totalCount = Gson().fromJson(response.body()!!.get("total_results"), object : TypeToken<Int>() {}.type)
                Logger.d(totalCount)
                movieLiveData.value = Gson().fromJson<List<MovieModel>>(response.body()!!.get("results"), object : TypeToken<List<MovieModel>>() {}.type)
                isDataLoading = false
            }

            override fun onFailed(status: Int) {
                movieLiveData.value = ArrayList()
                isDataLoading = false
            }

        })
    }

    fun fetchTopRatedMovie(page: Int) {
        isDataLoading = true
        ApiHelper.fetchTopRatedMovie(page, UrlUtils.API_KEY, object : DataFetchingListener<Response<JsonObject>> {
            override fun onDataFetched(response: Response<JsonObject>) {
                totalCount = Gson().fromJson(response.body()!!.get("total_results"), object : TypeToken<Int>() {}.type)
                Logger.d(totalCount)
                movieLiveData.value = Gson().fromJson<List<MovieModel>>(response.body()!!.get("results"), object : TypeToken<List<MovieModel>>() {}.type)
                isDataLoading = false
            }

            override fun onFailed(status: Int) {
                movieLiveData.value = ArrayList()
                isDataLoading = false
            }

        })
    }

    fun fetchDiscoverMovie(page: Int) {
        isDataLoading = true
        ApiHelper.fetchDiscoverMovie(page, UrlUtils.API_KEY, object : DataFetchingListener<Response<JsonObject>> {
            override fun onDataFetched(response: Response<JsonObject>) {
                totalCount = Gson().fromJson(response.body()!!.get("total_results"), object : TypeToken<Int>() {}.type)
                Logger.d(totalCount)
                movieLiveData.value = Gson().fromJson<List<MovieModel>>(response.body()!!.get("results"), object : TypeToken<List<MovieModel>>() {}.type)
                isDataLoading = false
            }

            override fun onFailed(status: Int) {
                movieLiveData.value = ArrayList()
                isDataLoading = false
            }

        })
    }

    fun fetchPersonList(page: Int) {
        isDataLoading = true
        ApiHelper.fetchPersonList(page, UrlUtils.API_KEY, object : DataFetchingListener<Response<JsonObject>> {
            override fun onDataFetched(response: Response<JsonObject>) {
                totalCount = Gson().fromJson(response.body()!!.get("total_results"), object : TypeToken<Int>() {}.type)
                Logger.d(totalCount)
                personLiveData.value = Gson().fromJson<List<PersonModel>>(response.body()!!.get("results"), object : TypeToken<List<PersonModel>>() {}.type)
                isDataLoading = false
            }

            override fun onFailed(status: Int) {
                movieLiveData.value = ArrayList()
                isDataLoading = false
            }

        })
    }

    fun fetchMovieByGenre(page: Int, id: Int) {
        isDataLoading = true
        ApiHelper.fetchMovieByGenre(page, id, UrlUtils.API_KEY, object : DataFetchingListener<Response<JsonObject>> {
            override fun onDataFetched(response: Response<JsonObject>) {
                totalCount = Gson().fromJson(response.body()!!.get("total_results"), object : TypeToken<Int>() {}.type)
                Logger.d(totalCount)
                movieLiveData.value = Gson().fromJson<List<MovieModel>>(response.body()!!.get("results"), object : TypeToken<List<MovieModel>>() {}.type)
                isDataLoading = false
            }

            override fun onFailed(status: Int) {
                movieLiveData.value = ArrayList()
                isDataLoading = false
            }

        })
    }

    fun fetchMovieByDiscoverFilter(page: Int, releaseYear: String, graterVote: String,lessVote: String,runTimeGrater: String, runtimeLess: String) {
        isDataLoading = true
        ApiHelper.fetchDiscoverMovieByFilter(page, releaseYear, graterVote, lessVote, runTimeGrater, runtimeLess, UrlUtils.API_KEY, object : DataFetchingListener<Response<JsonObject>> {
            override fun onDataFetched(response: Response<JsonObject>) {
                totalCount = Gson().fromJson(response.body()!!.get("total_results"), object : TypeToken<Int>() {}.type)
                Logger.d(totalCount)
                movieLiveData.value = Gson().fromJson<List<MovieModel>>(response.body()!!.get("results"), object : TypeToken<List<MovieModel>>() {}.type)
                isDataLoading = false
            }

            override fun onFailed(status: Int) {
                movieLiveData.value = ArrayList()
                isDataLoading = false
            }

        })
    }

}