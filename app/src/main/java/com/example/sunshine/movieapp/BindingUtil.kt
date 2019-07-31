package com.example.sunshine.movieapp

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.sunshine.movieapp.home.HomeAdapter
import com.example.sunshine.movieapp.home.MoviesStatus
import androidx.paging.PagedList
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.sunshine.movieapp.database.MovieEntity


//take an image url and download the poster using glide and load it to image
@BindingAdapter("imgSrcUrl")
fun imageSrc(imageView: ImageView, imgUrl: String?) {

    Glide.with(imageView.context)
        .load("https://image.tmdb.org/t/p/w500/$imgUrl").transform(RoundedCorners(10))
        .apply(RequestOptions().placeholder(R.drawable.loading_img).error(R.drawable.ic_connection_error))
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(imageView)

}

//setup adapter with list of movies
@BindingAdapter("movieList")
fun listMovie(recyclerView: RecyclerView, movieList: PagedList<MovieEntity>) {
    val adapter = recyclerView.adapter as HomeAdapter
    adapter.submitList(movieList)
}

@BindingAdapter("imageStatus")
fun status(imageView: ImageView, status: MoviesStatus?) {
    when (status) {
        MoviesStatus.ERROR -> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.ic_connection_error)
        }
        MoviesStatus.DONE -> imageView.visibility = View.GONE
        else -> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.loading_animation)
        }

    }
}