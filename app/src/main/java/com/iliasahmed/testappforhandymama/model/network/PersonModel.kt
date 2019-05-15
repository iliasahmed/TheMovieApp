package com.iliasahmed.testappforhandymama.model.network


data class PersonModel(
        var popularity: Float,
        var profile_path: String,
        var name: String,
        var known_for: Array<MovieModel>,
        var adult: Boolean
)
