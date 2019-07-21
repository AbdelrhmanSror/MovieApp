package com.example.sunshine.movieapp.network

import com.squareup.moshi.Json


data class VideoResult(@Json(name = "results") val movies: List<Video>)
data class Video(val key:String)