package com.frostdev.dogbreeds.fragments

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.frostdev.dogbreeds.R
import com.frostdev.dogbreeds.activities.DetailActivity
import com.frostdev.dogbreeds.adapters.AllDogsAdapter
import com.frostdev.dogbreeds.databinding.FragmentAllDogsBinding
import com.frostdev.dogbreeds.viewmodels.AllDogsListViewModel
import kotlinx.android.synthetic.main.fragment_all_dogs.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllDogsFragment : Fragment() {

    private lateinit var breedSelectionListener: BreedSelectionListener
    private lateinit var allDogsViewModel : AllDogsListViewModel
    private lateinit var allDogsAdapter: AllDogsAdapter
    private lateinit var binding: FragmentAllDogsBinding

    interface BreedSelectionListener {
        fun onBreedSelected(breed: String, subBreeds: java.util.ArrayList<String>?)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_dogs, container, false)
        binding.lifecycleOwner = this
        binding.fragment = this
        allDogsViewModel = AllDogsListViewModel()
        binding.alldogsviewmodel = allDogsViewModel
        allDogsViewModel.loadBreeds()
        breedSelectionListener = object : BreedSelectionListener {
            override fun onBreedSelected(breed: String, subBreeds: java.util.ArrayList<String>?) {
                startActivity(Intent(context, DetailActivity::class.java)
                    .putExtra("breed", breed)
                    .putExtra("subBreeds", subBreeds)
                )}
        }
        allDogsAdapter = AllDogsAdapter(breedSelectionListener)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        allDogsViewModel.allDogsList.observe(viewLifecycleOwner, Observer { dogs ->
            allDogsAdapter.updateSingleDogList(dogs)
            progress_loader.visibility = View.GONE
        })
    }

    fun getRecyclerAdapter(): AllDogsAdapter {
        return allDogsAdapter
    }

}

