package com.example.dogbreeds.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.dogbreeds.R
import com.example.dogbreeds.databinding.ActivityDetailBinding
import com.example.dogbreeds.helpers.PersistentSettings
import com.example.dogbreeds.viewmodels.DetailListViewModel
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    val ACTIVE_FAVORITES = "activeFavorites"

    @Inject
    lateinit var mPersistentSettings: PersistentSettings

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailSelectionListener: DetailSelectionListener
    private lateinit var mBreed: String
    private var mSubBreeds: List<String>? = null

    interface DetailSelectionListener {
        fun onImageSelected(image: String)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        mBreed = intent.getStringExtra("breed")!!
        mSubBreeds = intent.getStringArrayListExtra("subBreeds")!!
        detailSelectionListener = object : DetailSelectionListener {
            override fun onImageSelected(image: String) {
                var persistentFavorites = mPersistentSettings.getSettingStringSet(ACTIVE_FAVORITES, null)
                /*if(!persistentFavorites.isNullOrEmpty()) {

                }*/
            }
        }
        val viewModel = DetailListViewModel(detailSelectionListener, mBreed, mSubBreeds)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.viewModel = viewModel
    }
}