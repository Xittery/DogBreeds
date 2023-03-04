package com.frostdev.dogbreeds.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.frostdev.dogbreeds.R
import com.frostdev.dogbreeds.adapters.MainViewPagerAdapter
import com.frostdev.dogbreeds.databinding.ActivityMainBinding
import com.frostdev.dogbreeds.fragments.AllDogsFragment
import com.frostdev.dogbreeds.fragments.FavouriteDogsFragment

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_DogBreeds)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupViewPager()
    }

    private fun setupViewPager() {
        val adapter = MainViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(AllDogsFragment(), resources.getString(R.string.all_dogs_tab_title))
        adapter.addFragment(FavouriteDogsFragment(), resources.getString(R.string.favourite_dogs_tab_title))
        binding?.viewPager?.adapter = adapter
        binding?.tabLayout?.setupWithViewPager(binding?.viewPager, true)
    }

}