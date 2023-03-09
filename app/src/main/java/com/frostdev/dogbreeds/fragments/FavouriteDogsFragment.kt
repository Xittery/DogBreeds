package com.frostdev.dogbreeds.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.frostdev.dogbreeds.R
import com.frostdev.dogbreeds.activities.DetailActivity
import com.frostdev.dogbreeds.adapters.AllDogsAdapter
import com.frostdev.dogbreeds.adapters.FavoriteDogsAdapter
import com.frostdev.dogbreeds.databinding.FragmentAllDogsBinding
import com.frostdev.dogbreeds.databinding.FragmentFavouriteDogsBinding
import com.frostdev.dogbreeds.helpers.PersistentDogs
import com.frostdev.dogbreeds.viewmodels.AllDogsListViewModel
import com.frostdev.dogbreeds.viewmodels.FavoriteDogsListViewModel
import kotlinx.android.synthetic.main.fragment_all_dogs.*
import kotlinx.android.synthetic.main.fragment_favourite_dogs.*
import javax.inject.Inject

class FavouriteDogsFragment : Fragment() {

    private lateinit var favDogsViewModel : FavoriteDogsListViewModel
    private lateinit var favDogsAdapter: FavoriteDogsAdapter
    private lateinit var binding: FragmentFavouriteDogsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourite_dogs, container, false)
        binding.lifecycleOwner = this
        binding.fragment = this
        favDogsViewModel = FavoriteDogsListViewModel()
        binding.viewModel = favDogsViewModel
        favDogsViewModel.loadFavourites()
        favDogsAdapter = FavoriteDogsAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favDogsViewModel.favDogsList.observe(viewLifecycleOwner, Observer { dogs ->
            if(dogs.isNotEmpty()) {
                nofavs.visibility = View.GONE
            }
            favDogsAdapter.updateSingleDogList(dogs)
            fav_progress_loader.visibility = View.GONE
        })
    }

    fun getRecyclerAdapter(): FavoriteDogsAdapter {
        return favDogsAdapter
    }
}

