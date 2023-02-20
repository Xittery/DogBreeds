package com.frostdev.dogbreeds.viewmodels

import com.frostdev.dogbreeds.activities.DetailActivity
import com.frostdev.dogbreeds.adapters.DetailAdapter
import com.frostdev.dogbreeds.interfaces.DogService
import com.frostdev.dogbreeds.model.AllImagesResponse
import com.frostdev.dogbreeds.model.SingleDog
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