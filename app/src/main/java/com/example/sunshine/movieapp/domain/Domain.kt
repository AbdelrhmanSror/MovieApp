package com.example.sunshine.movieapp.domain

import android.os.Parcelable
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
* Domain objects are plain Kotlin data classes that represent the things in  app. These are the
* objects that should be displayed on screen, or manipulated by the app.
*
* @see database for objects that are mapped to the database
* @see network for objects that parse or prepare network calls
*/
@Parcelize
data class Domain(
    val title: String
    , val description: String
    , val releaseDate: String
    , val rating: Float
    , val posterPath: String?
    , val backDropPath: String?
    , val movieId: Long
):Parcelable