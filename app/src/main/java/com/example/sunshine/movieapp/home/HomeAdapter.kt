package com.example.sunshine.movieapp.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sunshine.movieapp.databinding.MovieListBinding
import com.example.sunshine.movieapp.domain.Domain
import com.example.sunshine.movieapp.home.HomeAdapter.viewHolder.Companion.from
class HomeAdapter(val onClickListener:OnClickListener) : ListAdapter<Domain, RecyclerView.ViewHolder>(diffCallBack) {
    /**
     * diff util class to calculate the difference between two list if the the old list has changed
     *with minimum changes it can do
     */
    object diffCallBack : DiffUtil.ItemCallback<Domain>() {
        override fun areItemsTheSame(oldItem: Domain, newItem: Domain): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Domain, newItem: Domain): Boolean {
            return oldItem.movieId== newItem.movieId
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
        fun bind(movie: Domain, onClickListener: OnClickListener)
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
    class OnClickListener(val onClickListener:(Domain)->Unit)
    {
        fun onClick(movie: Domain)=onClickListener(movie)
    }


}