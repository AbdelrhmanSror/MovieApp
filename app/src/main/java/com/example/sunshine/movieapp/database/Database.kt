package com.example.sunshine.movieapp.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
@Dao
interface MovieDao{

    //inserting movies data into data base
    @Query("SELECT * FROM movieentity ")
    fun getMovies():LiveData<List<MovieEntity>>
    //insert and update at the same time if the data is existed in database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( movies:List<MovieEntity>)
    //clear all data from database
    @Query("DELETE FROM movieentity")
    fun clear()
}
@Database(entities = [MovieEntity::class],version = 1)
abstract class MovieDatabase():RoomDatabase()
{
    abstract val movieDao:MovieDao
    companion object {
        @Volatile
        private lateinit var INSTANCE: MovieDatabase
        fun getDataBase(context: Context): MovieDatabase {
            synchronized(MovieDatabase::class.java)
            {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(context, MovieDatabase::class.java, "Movies").build()

                }
            }
            return INSTANCE
        }
    }
}