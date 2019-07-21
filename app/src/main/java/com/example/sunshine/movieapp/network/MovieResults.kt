package com.example.sunshine.movieapp.network

import android.os.Parcelable
import com.example.sunshine.movieapp.database.MovieEntity
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

data class MovieResults(@Json(name = "results") val movies: List<Movies>)
data class Movies(
    val id: Long
    , val title: String
    , @Json(name = "poster_path") val posterUrl: String?
    , @Json(name = "vote_average") val rating: Float
    , @Json(name = "backdrop_path") val backDropUrl: String?
    , @Json(name = "overview") val description: String
    , @Json(name = "release_date") val releaseDate: String
)
/**
 * extension function to transfer from data transfer object server to dataBase object entity
 */
fun MovieResults.asDataBaseModel(): List<MovieEntity> {
    return movies.map {
        MovieEntity(
            id = it.id,
            title = it.title
            , description = it.description
            , releaseDate = it.releaseDate
            , rating = it.rating
            , posterPath = it.posterUrl
            , backDropPath = it.backDropUrl
        )
    }

}