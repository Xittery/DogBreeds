package com.frostdev.dogbreeds.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.frostdev.dogbreeds.R
import com.frostdev.dogbreeds.adapters.MainViewPagerAdapter
import com.frostdev.dogbreeds.databinding.ActivityMainBinding
import com.frostdev.dogbreeds.fragments.AllDogsFragment
import com.frostdev.dogbreeds.fragments.FavouriteDogsFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var firstTab = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_DogBreeds)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupViewPager()
    }

    private fun setupViewPager() {
        val allDogs = AllDogsFragment()
        val favDogs = FavouriteDogsFragment()
        val adapter = MainViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(allDogs, resources.getString(R.string.all_dogs_tab_title))
        adapter.addFragment(favDogs, resources.getString(R.string.favourite_dogs_tab_title))
        binding.viewPager.adapter = adapter
        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                if(tab.position == 1) {
                    favDogs.reloadFavouriteDogs()
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
        binding.tabLayout.setupWithViewPager(binding.viewPager, true)
    }

}