package com.example.sunshine.movieapp.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sunshine.movieapp.database.MovieDatabase
import com.example.sunshine.movieapp.network.sort
import com.example.sunshine.movieapp.repositry.MoviesRepos
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

enum class MoviesStatus { LOADING, ERROR, DONE }
class HomeViewModel(val moviesRepos: MoviesRepos) : ViewModel() {
    //job for viewModel so we cancel coroutine by it
    private val viewModelJob = Job()
    //scope for coroutine
    private val Scope = CoroutineScope(Dispatchers.Main + viewModelJob)
    /**
     * live data to observe the changes that happens to moviesDatabase
     *
     */
    val getMovies=moviesRepos.getMovies()

    /**
     * live data to observe the changes that happens to status
     */
    private val _status = MutableLiveData<MoviesStatus>()
    val status: LiveData<MoviesStatus>
        get() = _status

    init {
        startFetching()
    }

    private fun startFetching(sortBy:String= sort.POPULARITY_DESC.value) {
        /**
         * start fetching movies data then when data is fetched we assign it to _getMovie
         */
        Scope.launch {
            try {
                //status is loading before start fetching
                _status.value = MoviesStatus.LOADING
                //fetching data from server and insert it into database
                moviesRepos.refreshMovies(sortBy)
                //status is done after finishing fetching
                _status.value = MoviesStatus.DONE


            } catch (thowable: Throwable) {
                //status is error when something is happen
                _status.value = MoviesStatus.ERROR
            }


        }

    }

    /**
     *  when user select an item from menu i set the item selected here
     *   to fetch from server based on selection
     */
    fun menuItemSelected(item:String)
    {
        Scope.launch {
            moviesRepos.clearALLMovies()
            startFetching(item)
        }
    }
    /**
     * when viewModel got cancelled we cancel coroutine job as well
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
class HomeViewModelFactory(application: Application):ViewModelProvider.Factory
{
    val database=MovieDatabase.getDataBase(application)
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java))
        {
            return HomeViewModel(MoviesRepos(database)) as T
        }
        throw IllegalArgumentException("unknown class")
    }

}
