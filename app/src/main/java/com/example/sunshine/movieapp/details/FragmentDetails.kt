package com.example.sunshine.movieapp.details

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.sunshine.movieapp.R

class FragmentDetails : Fragment() {

    companion object {
        fun newInstance() = FragmentDetails()
    }

    private lateinit var viewModel: FragmentDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FragmentDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
