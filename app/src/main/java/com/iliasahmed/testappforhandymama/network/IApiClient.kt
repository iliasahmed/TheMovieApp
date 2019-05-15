package com.iliasahmed.testappforhandymama.network

import com.google.gson.JsonObject
import com.iliasahmed.testappforhandymama.utils.UrlUtils
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface IApiClient {

    @GET(UrlUtils.GENRE_LIST)
    abstract fun genreList(@Query("api_key") api_key: String): Call<JsonObject>

    @GET(UrlUtils.POPULAR_MOVIE_LIST)
    abstract fun popularMovies(@Query("api_key") api_key: String, @Query("page") page: Int): Call<JsonObject>

    @GET(UrlUtils.SEARCH_MOVIES)
    abstract fun searchMovies(@Query("api_key") api_key: String, @Query("page") page: Int, @Query("query") query: String): Call<JsonObject>

    @GET(UrlUtils.UPCOMING_MOVIE_LIST)
    abstract fun upcomingMovies(@Query("api_key") api_key: String, @Query("page") page: Int): Call<JsonObject>

    @GET(UrlUtils.NOW_PLAYING_MOVIE_LIST)
    abstract fun nowPlayingMovie(@Query("api_key") api_key: String, @Query("page") page: Int): Call<JsonObject>

    @GET(UrlUtils.TOP_RATED_MOVIE_LIST)
    abstract fun topRatedMovie(@Query("api_key") api_key: String, @Query("page") page: Int): Call<JsonObject>

    @GET(UrlUtils.DISCOVER_MOVIE_LIST)
    abstract fun discoverMovie(@Query("api_key") api_key: String, @Query("page") page: Int): Call<JsonObject>

    @GET(UrlUtils.PERSON_LIST)
    abstract fun personList(@Query("api_key") api_key: String, @Query("page") page: Int): Call<JsonObject>

    @GET(UrlUtils.BASE_URL+"genre/{id}/movies")
    abstract fun filterByGenre(@Path("id") id: Int, @Query("api_key") api_key: String, @Query("page") page: Int): Call<JsonObject>

    @GET(UrlUtils.DISCOVER_MOVIE_LIST)
    abstract fun filterDiscoverMovie(@Query("api_key") api_key: String,
                                     @Query("page") page: Int,
                                     @Query("primary_release_year") releaseYear: Int,
                                     @Query("vote_count.gte") voteCount: Int,
                                     @Query("vote_average.lte") lessVote: Int,
                                     @Query("vote_average.gte") graterVote: Int,
                                     @Query("with_runtime.lte") runtimeLess: Int,
                                     @Query("with_runtime.gte") runTimeGrater: Int): Call<JsonObject>


}