package com.example.sunshine.movieapp.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.sunshine.movieapp.R
import com.example.sunshine.movieapp.databinding.HomeFragmentBinding
import com.example.sunshine.movieapp.network.sort


class HomeFragment : Fragment() {

    /**
     * init viewModel using lazy property
     */
    private val homeViewModel:HomeViewModel by lazy {
        val homeViewModel=ViewModelProviders.of(this,HomeViewModelFactory(activity!!.application)).get(HomeViewModel::class.java)
        homeViewModel
    }
    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding =HomeFragmentBinding.inflate(inflater,container,false)
        binding.setLifecycleOwner(this)
        binding.movieRecyclerView.adapter=HomeAdapter(HomeAdapter.OnClickListener {
          //  val intent=Intent(Intent.ACTION_VIEW,it.launchUri)
           // startActivity(intent)

        })
        binding.movies=homeViewModel
        setHasOptionsMenu(true)
        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.home_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        homeViewModel.menuItemSelected(when(item?.itemId)
        {
            R.id.popularity-> sort.POPULARITY_DESC.value
            R.id.releaseDate-> sort.RELEASE_DATE_DESC.value
            else-> sort.REVENUE_DESC.value
        })
        return true
    }
}
