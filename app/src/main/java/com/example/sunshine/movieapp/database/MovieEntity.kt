package com.example.sunshine.movieapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sunshine.movieapp.domain.Domain

@Entity
data class MovieEntity(
    val title: String
    , val description: String
    , val releaseDate: String
    , val rating: Float
    , val posterPath: String?
    , val backDropPath: String?
    ,@PrimaryKey val id: Long
)
 /*val MovieEntity.launchUri: Uri
    get() {
        return Uri.parse("vnd.youtube:$videoUrl")
    }*/
/**
 * extension function to transfer from DataBase object to domain object
 */
fun List<MovieEntity>.asDomainModel():List<Domain>
 {
     return map {
         Domain(
             movieId = it.id,
             title = it.title
             , description = it.description
             , releaseDate = it.releaseDate
             , rating = it.rating
             , posterPath = it.posterPath
             , backDropPath = it.backDropPath
         )
     }
 }