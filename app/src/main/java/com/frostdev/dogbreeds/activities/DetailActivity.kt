package com.frostdev.dogbreeds.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.frostdev.dogbreeds.R
import com.frostdev.dogbreeds.adapters.DetailAdapter
import com.frostdev.dogbreeds.databinding.ActivityDetailBinding
import com.frostdev.dogbreeds.helpers.PersistentDogs
import com.frostdev.dogbreeds.injection.component.DaggerInjector
import com.frostdev.dogbreeds.injection.component.Injector
import com.frostdev.dogbreeds.injection.module.DataModule
import com.frostdev.dogbreeds.injection.module.NetworkModule
import com.frostdev.dogbreeds.viewmodels.DetailListViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import java.util.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private lateinit var detailListViewModel: DetailListViewModel
    lateinit var detailAdapter: DetailAdapter
    private lateinit var mBreed: String
    private var mSubBreeds: List<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        setBreed()
        setBindings()
    }

    private fun setBindings() {
        detailListViewModel = DetailListViewModel(mBreed, mSubBreeds)
        binding.viewModel = detailListViewModel
        binding.activity = this
        binding.lifecycleOwner = this
        detailAdapter = DetailAdapter()
        binding.detailRecycler.adapter = detailAdapter
        binding.detailRecycler.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        detailListViewModel.detailList.observe(this, androidx.lifecycle.Observer {
            detailAdapter.updateImageList(it)
            progress_loader_detail.visibility = View.GONE
        })
    }


    private fun setBreed() {
        mBreed = intent.getStringExtra("breed")!!
        mSubBreeds = intent.getStringArrayListExtra("subBreeds")!!
        binding.detailTitle.text = mBreed.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }
}