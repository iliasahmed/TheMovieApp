package com.iliasahmed.testappforhandymama.model

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.iliasahmed.testappforhandymama.helper.DataFetchingListener
import com.iliasahmed.testappforhandymama.network.ApiClient
import com.iliasahmed.testappforhandymama.network.IApiClient
import com.orhanobut.logger.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

public class ApiHelper {

    companion object {
        fun fetchGenreList(api_key:String, dataFetchingListener: DataFetchingListener<Response<JsonObject>>) {
            val iApiClient = ApiClient.getClient().create(IApiClient::class.java)
            val call = iApiClient.genreList(api_key)
            call.enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if (response.code() == 200){
                        dataFetchingListener.onDataFetched(response)
                    }else{
                        dataFetchingListener.onFailed(response.code())
                    }
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    dataFetchingListener.onFailed(0)
                }
            })
        }

        fun fetchPopularMovie(page: Int, api_key:String, dataFetchingListener: DataFetchingListener<Response<JsonObject>>) {
            val iApiClient = ApiClient.getClient().create(IApiClient::class.java)
            val call = iApiClient.popularMovies(api_key, page)
            call.enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if (response.code() == 200){
                        dataFetchingListener.onDataFetched(response)
                    }else{
                        dataFetchingListener.onFailed(response.code())
                    }
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    dataFetchingListener.onFailed(0)
                }
            })
        }

        fun fetchMovieByGenre(page: Int, id: Int, api_key:String, dataFetchingListener: DataFetchingListener<Response<JsonObject>>) {
            val iApiClient = ApiClient.getClient().create(IApiClient::class.java)
            val call = iApiClient.filterByGenre(id, api_key, page)
            call.enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if (response.code() == 200){
                        dataFetchingListener.onDataFetched(response)
                    }else{
                        dataFetchingListener.onFailed(response.code())
                    }
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    dataFetchingListener.onFailed(0)
                }
            })
        }

        fun searchPopularMovie(page: Int, api_key:String, query: String, dataFetchingListener: DataFetchingListener<Response<JsonObject>>) {
            val iApiClient = ApiClient.getClient().create(IApiClient::class.java)
            val call = iApiClient.searchMovies(api_key, page, query)
            call.enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if (response.code() == 200){
                        dataFetchingListener.onDataFetched(response)
                    }else{
                        dataFetchingListener.onFailed(response.code())
                    }
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    dataFetchingListener.onFailed(0)
                }
            })
        }

        fun fetchUpcomingMovie(page: Int, api_key:String, dataFetchingListener: DataFetchingListener<Response<JsonObject>>) {
            val iApiClient = ApiClient.getClient().create(IApiClient::class.java)
            val call = iApiClient.upcomingMovies(api_key, page)
            call.enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if (response.code() == 200){
                        dataFetchingListener.onDataFetched(response)
                    }else{
                        dataFetchingListener.onFailed(response.code())
                    }
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    dataFetchingListener.onFailed(0)
                }
            })
        }

        fun fetchNowPlayingMovie(page: Int, api_key:String, dataFetchingListener: DataFetchingListener<Response<JsonObject>>) {
            val iApiClient = ApiClient.getClient().create(IApiClient::class.java)
            val call = iApiClient.nowPlayingMovie(api_key, page)
            call.enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if (response.code() == 200){
                        dataFetchingListener.onDataFetched(response)
                    }else{
                        dataFetchingListener.onFailed(response.code())
                    }
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    dataFetchingListener.onFailed(0)
                }
            })
        }

        fun fetchTopRatedMovie(page: Int, api_key:String, dataFetchingListener: DataFetchingListener<Response<JsonObject>>) {
            val iApiClient = ApiClient.getClient().create(IApiClient::class.java)
            val call = iApiClient.topRatedMovie(api_key, page)
            call.enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if (response.code() == 200){
                        dataFetchingListener.onDataFetched(response)
                    }else{
                        dataFetchingListener.onFailed(response.code())
                    }
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    dataFetchingListener.onFailed(0)
                }
            })
        }

        fun fetchDiscoverMovie(page: Int, api_key:String, dataFetchingListener: DataFetchingListener<Response<JsonObject>>) {
            val iApiClient = ApiClient.getClient().create(IApiClient::class.java)
            val call = iApiClient.discoverMovie(api_key, page)
            call.enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if (response.code() == 200){
                        dataFetchingListener.onDataFetched(response)
                    }else{
                        dataFetchingListener.onFailed(response.code())
                    }
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    dataFetchingListener.onFailed(0)
                }
            })
        }

        fun fetchDiscoverMovieByFilter(page: Int, releaseYear: String, graterVote: String,lessVote: String,runTimeGrater: String, runtimeLess: String, api_key:String, dataFetchingListener: DataFetchingListener<Response<JsonObject>>) {
            val iApiClient = ApiClient.getClient().create(IApiClient::class.java)
            val call = iApiClient.filterDiscoverMovie(api_key, page, releaseYear.toInt(), 200, graterVote.toInt(), lessVote.toInt(), runTimeGrater.toInt(), runtimeLess.toInt())
            call.enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if (response.code() == 200){
                        dataFetchingListener.onDataFetched(response)
                    }else{
                        dataFetchingListener.onFailed(response.code())
                    }
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    dataFetchingListener.onFailed(0)
                }
            })
        }

        fun fetchPersonList(page: Int, api_key:String, dataFetchingListener: DataFetchingListener<Response<JsonObject>>) {
            val iApiClient = ApiClient.getClient().create(IApiClient::class.java)
            val call = iApiClient.personList(api_key, page)
            call.enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if (response.code() == 200){
                        dataFetchingListener.onDataFetched(response)
                    }else{
                        dataFetchingListener.onFailed(response.code())
                    }
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    dataFetchingListener.onFailed(0)
                }
            })
        }

    }
}