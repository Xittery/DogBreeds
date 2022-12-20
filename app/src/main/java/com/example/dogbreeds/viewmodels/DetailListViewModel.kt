package com.example.dogbreeds.viewmodels

import com.example.dogbreeds.activities.DetailActivity
import com.example.dogbreeds.adapters.DetailAdapter
import com.example.dogbreeds.interfaces.DogService
import com.example.dogbreeds.model.AllImagesResponse
import com.example.dogbreeds.model.SingleDog
import javax.inject.Inject


class DetailListViewModel(detailSelectionChangedListener: DetailActivity.DetailSelectionListener, breed: String, subBreeds: List<String>?): BaseViewModel() {

    @Inject
    lateinit var dogApi: DogService

    var detailList: MutableList<SingleDog>? = null
    var detailAdapter: DetailAdapter = DetailAdapter(detailSelectionChangedListener)
    var mBreed: String = breed
    var msubBreedList: List<String>? = subBreeds

    init{
        loadImages()
    }

    private fun loadImages() {
        display(dogApi.getAllImagesFromBreed(mBreed).execute().body())
    }

    private fun display(imageResponse: AllImagesResponse?) {
        detailList = mutableListOf<SingleDog>()
        imageResponse?.message?.forEach {
            detailList!!.add(SingleDog(mBreed, msubBreedList, it))
        }
        detailAdapter.updateImageList(detailList!!)
    }
}