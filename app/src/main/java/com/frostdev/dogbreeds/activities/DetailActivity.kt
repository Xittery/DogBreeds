package com.frostdev.dogbreeds.activities

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.frostdev.dogbreeds.R
import com.frostdev.dogbreeds.adapters.DetailAdapter
import com.frostdev.dogbreeds.databinding.ActivityDetailBinding
import com.frostdev.dogbreeds.helpers.PersistentSettings
import com.frostdev.dogbreeds.injection.module.DataModule.ACTIVE_FAVORITES
import com.frostdev.dogbreeds.viewmodels.DetailListViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.fragment_all_dogs.*
import java.util.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    @Inject
    lateinit var mPersistentSettings: PersistentSettings

    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailSelectionListener: DetailSelectionListener
    private lateinit var detailListViewModel: DetailListViewModel
    lateinit var detailAdapter: DetailAdapter
    private lateinit var mBreed: String
    private var mSubBreeds: List<String>? = null

    interface DetailSelectionListener {
        fun onImageSelected(image: Drawable)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        setTheme(R.style.Theme_DogBreeds)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.viewModel = detailListViewModel
        binding.activity = this
        binding.lifecycleOwner = this
        mBreed = intent.getStringExtra("breed")!!
        mSubBreeds = intent.getStringArrayListExtra("subBreeds")!!
        detailSelectionListener = object : DetailSelectionListener {
            override fun onImageSelected(image: Drawable) {
                //var persistentFavorites = mPersistentSettings.getSettingStringSet(ACTIVE_FAVORITES, null)
                /*if(!persistentFavorites.isNullOrEmpty()) {

                }*/
            }
        }
        detailListViewModel = DetailListViewModel(mBreed, mSubBreeds)
        detailAdapter = DetailAdapter(detailSelectionListener)
        setBreed()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        detailListViewModel.detailList.observe(this, androidx.lifecycle.Observer {
            detailAdapter.updateImageList(it)
            progress_loader_detail.visibility = View.GONE
        })
    }

    fun setBreed() {
        detail_title.text = mBreed.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }
}