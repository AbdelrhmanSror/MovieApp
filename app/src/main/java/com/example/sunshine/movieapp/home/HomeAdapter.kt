package com.example.sunshine.movieapp.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sunshine.movieapp.database.MovieEntity
import com.example.sunshine.movieapp.databinding.MovieListBinding
import com.example.sunshine.movieapp.home.HomeAdapter.viewHolder.Companion.from
class HomeAdapter(val onClickListener:OnClickListener) : PagedListAdapter<MovieEntity, RecyclerView.ViewHolder>(diffCallBack) {
    /**
     * diff util class to calculate the difference between two list if the the old list has changed
     *with minimum changes it can do
     */
    object diffCallBack : DiffUtil.ItemCallback<MovieEntity>() {
        override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
            return oldItem.id== newItem.id
        }

    }

    /**
     * view holder with data binding
     */
    class viewHolder(val item: MovieListBinding) : RecyclerView.ViewHolder(item.root) {
        companion object {
            fun from(parent: ViewGroup): viewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val root = MovieListBinding.inflate(inflater, parent, false)
                return viewHolder(root)
            }
        }
        fun bind(movie:MovieEntity?, onClickListener: OnClickListener)
        {
            item.movie=movie
            item.listenerCallBack=onClickListener
            item.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return from(parent)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item=getItem(position)
        (holder as viewHolder).bind(item,onClickListener)

    }
    //listener class to be used when click event happens
    class OnClickListener(val onClickListener:(MovieEntity)->Unit)
    {
        fun onClick(movie: MovieEntity)=onClickListener(movie)
    }


}