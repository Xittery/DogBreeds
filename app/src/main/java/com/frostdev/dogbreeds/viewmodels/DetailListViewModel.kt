package com.frostdev.dogbreeds.viewmodels

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.frostdev.dogbreeds.helpers.GetDrawable
import com.frostdev.dogbreeds.interfaces.DogService
import com.frostdev.dogbreeds.model.AllImagesResponse
import com.frostdev.dogbreeds.model.SingleDog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject

class DetailListViewModel(breed: String, subBreeds: List<String>?): BaseViewModel() {

    @Inject
    lateinit var dogApi: DogService

    @Inject
    lateinit var getDrawable: GetDrawable

    var detailList: MutableLiveData<MutableList<SingleDog>> = MutableLiveData()
    private var mBreed: String = breed
    private var msubBreedList: List<String>? = subBreeds

    init {
        loadImages()
    }

    private fun loadImages() {
        viewModelScope.launch {
            var dogs: MutableList<SingleDog>
            withContext(Dispatchers.IO) {
                dogs = collectDogs(dogApi.getAllImagesFromBreed(mBreed).execute().body())
            }
            detailList.postValue(dogs)
        }
    }

    private fun collectDogs(imageResponse: AllImagesResponse?): MutableList<SingleDog> {
        val allDogsLists = mutableListOf<SingleDog>()
        imageResponse?.message?.forEach {
            allDogsLists.add(SingleDog(mBreed, msubBreedList, it))
        }
        return allDogsLists
    }

}