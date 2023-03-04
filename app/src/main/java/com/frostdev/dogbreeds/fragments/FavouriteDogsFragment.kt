package com.frostdev.dogbreeds.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.frostdev.dogbreeds.R
import com.frostdev.dogbreeds.databinding.FragmentFavouriteDogsBinding
import com.frostdev.dogbreeds.helpers.PersistentSettings
import javax.inject.Inject

class FavouriteDogsFragment : Fragment() {

    @Inject
    lateinit var mPersistentSettings: PersistentSettings

    private lateinit var binding: FragmentFavouriteDogsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourite_dogs, container, false)
        return binding.root
    }
}

