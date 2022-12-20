package com.example.dogbreeds.activities

import android.R
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.dogbreeds.adapters.MainViewPagerAdapter
import com.example.dogbreeds.fragments.AllDogsFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        StrictMode.setThreadPolicy(ThreadPolicy.Builder().permitAll().build())
        setContentView(com.example.dogbreeds.R.layout.activity_main)
        setupViewPager()
    }

    private fun setupViewPager() {
        val adapter = MainViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(AllDogsFragment(), resources.getString(com.example.dogbreeds.R.string.all_dogs_tab_title))
        //adapter.addFragment(FavouriteDogsFragment(), resources.getString(R.string.favourite_dogs_tab_title))
        viewPager.adapter = adapter
        tablayout.setupWithViewPager(viewPager)
    }

}