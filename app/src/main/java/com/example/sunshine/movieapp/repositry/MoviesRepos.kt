package com.example.sunshine.movieapp.repositry

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.example.sunshine.movieapp.API_KEY
import com.example.sunshine.movieapp.database.MovieDatabase
import com.example.sunshine.movieapp.database.MovieEntity
import com.example.sunshine.movieapp.network.MovieService
import com.example.sunshine.movieapp.network.asDataBaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepos(private val database: MovieDatabase) {
    //function to get list of movies in database
    fun getMovies():LiveData<PagedList<MovieEntity>>{
        val pagingFactory=database.movieDao.getMovies()
        return pagingFactory.toLiveData(pageSize = 14)


    }
   suspend fun refreshMovies(sort:String)
    {
        //using coroutine to update the list of Movie in dataBase
        withContext(Dispatchers.IO)
        {
            val movies=MovieService.movie.getMovies(API_KEY,sort).await()
            database.movieDao.insertAll(movies.asDataBaseModel())
        }
    }
    suspend fun clearALLMovies()
    {
        withContext(Dispatchers.IO)
        {
            database.movieDao.clear()
        }
    }

}