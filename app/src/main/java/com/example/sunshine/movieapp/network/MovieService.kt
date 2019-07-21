package com.example.sunshine.movieapp.network

import com.example.sunshine.movieapp.BASE_URL
import com.example.sunshine.movieapp.network.sort.*
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

/**
 * enum class for sorting movies
 */
enum class sort(val value:String)
{
    POPULARITY_ASC("popularity.asc"),
    POPULARITY_DESC("popularity.desc"),
    RELEASE_DATE_ASC("release_date.asc"),
    RELEASE_DATE_DESC("release_date.desc"),
    REVENUE_ASC("revenue.asc"),
    REVENUE_DESC("revenue.desc"),
    PRIMARY_RELEASE_DATE_ASC("primary_release_date.asc"),
    PRIMARY_RELEASE_DATE_DESC("primary_release_date.DESC"),
    VOTE_AVERAGE_ASC("vote_average.asc"),
    VOTE_AVERAGE_DESC("vote_average.desc"),
    VOTE_COUNT_ASC("vote_count.asc"),
    VOTE_COUNT_desc("vote_count.desc")
}
/**
 * lib to convert from json to kotlin object

 */
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
/**
 * creating retrofit with with moshiConverterFactory and Coroutine Adapter

 */
private val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory()).build()

/**
 * interface that retrofit will provide an implementation for it

 */
interface MovieApiService {
    /**
     * returning defered object
     */
    @GET("discover/movie")
    fun getMovies(@Query("api_key") api_key: String, @Query("sort_by") sort: String): Deferred<MovieResults>
    @GET("movie/{id}/videos")
    fun getTrailers(@Path("id")videoId:Int,@Query("api_key") api_key: String): Deferred<VideoResult>
}

/**
 * initializing retrofit using lazy property so it will be initialized at the time we need it
 */
object MovieService {
    val movie: MovieApiService by lazy {
        retrofit.create(MovieApiService::class.java)
    }
}
