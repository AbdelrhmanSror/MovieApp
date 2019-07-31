package com.example.sunshine.movieapp.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity
data class MovieEntity(
    val title: String
    , val description: String
    , val releaseDate: String
    , val rating: Float
    , val posterPath: String?
    , val backDropPath: String?
    ,@PrimaryKey val id: Long
):Parcelable
 /*val MovieEntity.launchUri: Uri
    get() {
        return Uri.parse("vnd.youtube:$videoUrl")
    }*/
/**
 * extension function to transfer from DataBase object to domain object
 */
/*
fun List<MovieEntity>.asDomainModel():List<MovieEntity>
 {
      return map {
          MovieEntity(
              movieId = it.id,
              title = it.title
              , description = it.description
              , releaseDate = it.releaseDate
              , rating = it.rating
              , posterPath = it.posterPath
              , backDropPath = it.backDropPath
          )
     }
 }*/
