package com.frostdev.dogbreeds.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.frostdev.dogbreeds.R
import com.frostdev.dogbreeds.activities.DetailActivity
import com.frostdev.dogbreeds.databinding.FragmentAllDogsBinding
import com.frostdev.dogbreeds.viewmodels.AllDogsListViewModel

class AllDogsFragment() : Fragment() {

    private lateinit var binding: FragmentAllDogsBinding
    private lateinit var breedSelectionListener: BreedSelectionListener

    interface BreedSelectionListener {
        fun onBreedSelected(breed: String, subBreeds: java.util.ArrayList<String>?)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_dogs, container, false)
        breedSelectionListener = object : BreedSelectionListener {
            override fun onBreedSelected(breed: String, subBreeds: java.util.ArrayList<String>?) {
                startActivity(Intent(context, DetailActivity::class.java)
                    .putExtra("breed", breed)
                    .putExtra("subBreeds", subBreeds)
                )
            }
        }
        val viewModel = AllDogsListViewModel(breedSelectionListener)
        binding.viewModel = viewModel
        return binding.root
    }
}

