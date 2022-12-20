package com.example.dogbreeds.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.dogbreeds.R
import com.example.dogbreeds.databinding.FragmentFavouriteDogsBinding
import com.example.dogbreeds.viewmodels.FavoriteDogsListViewModel

class FavouriteDogsFragment : Fragment() {

    private lateinit var binding: FragmentFavouriteDogsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favourite_dogs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = FavoriteDogsListViewModel()
        binding = DataBindingUtil.setContentView(requireActivity(), R.id.viewPager)
        binding.viewModel = viewModel
    }
}

