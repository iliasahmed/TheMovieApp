package com.iliasahmed.testappforhandymama.utils

object UrlUtils {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val GENRE_LIST = "$BASE_URL${"genre/movie/list"}"
    const val POPULAR_MOVIE_LIST = "$BASE_URL${"movie/popular"}"
    const val UPCOMING_MOVIE_LIST = "$BASE_URL${"movie/upcoming"}"
    const val NOW_PLAYING_MOVIE_LIST = "$BASE_URL${"movie/now_playing"}"
    const val TOP_RATED_MOVIE_LIST = "$BASE_URL${"movie/top_rated"}"
    const val DISCOVER_MOVIE_LIST = "$BASE_URL${"discover/movie"}"
    const val PERSON_LIST = "$BASE_URL${"person/popular"}"
    const val SEARCH_MOVIES = "$BASE_URL${"search/movie"}"

    const val API_KEY = "a46eb73522e4f9bab582d848401dcae1"
    const val IMAGE_BASE = "https://image.tmdb.org/t/p/original"
}