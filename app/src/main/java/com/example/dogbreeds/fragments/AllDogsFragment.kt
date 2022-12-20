package com.example.dogbreeds.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.dogbreeds.R
import com.example.dogbreeds.activities.DetailActivity
import com.example.dogbreeds.databinding.FragmentAllDogsBinding
import com.example.dogbreeds.viewmodels.AllDogsListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class AllDogsFragment : Fragment() {

    private lateinit var binding: FragmentAllDogsBinding
    private lateinit var breedSelectionListener: BreedSelectionListener

    interface BreedSelectionListener {
        fun onBreedSelected(breed: String, subBreeds: java.util.ArrayList<String>?)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_all_dogs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        breedSelectionListener = object : BreedSelectionListener {
            override fun onBreedSelected(breed: String, subBreeds: java.util.ArrayList<String>?) {
                startActivity(Intent(context, DetailActivity::class.java)
                    .putExtra("breed", breed)
                    .putExtra("subBreeds", subBreeds)
                )
            }
        }
        val viewModel = AllDogsListViewModel(breedSelectionListener)
        binding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_all_dogs)
        binding.viewModel = viewModel
    }
}

