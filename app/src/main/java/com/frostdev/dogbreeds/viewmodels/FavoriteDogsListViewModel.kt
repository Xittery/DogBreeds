package com.frostdev.dogbreeds.viewmodels

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.frostdev.dogbreeds.helpers.PersistentDogs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject

class FavoriteDogsListViewModel: BaseViewModel() {

    @Inject
    lateinit var mPersistentDogs: PersistentDogs

    var favDogsList: MutableLiveData<MutableList<String>> = MutableLiveData()

    fun loadFavourites() {
        viewModelScope.launch {
            var dogs: MutableList<String>
            withContext(Dispatchers.IO) {
                dogs = collectDogs(mPersistentDogs.getFavouriteDogsSet())
            }
            favDogsList.postValue(dogs)
        }
    }

    private fun collectDogs(dogResponse: MutableSet<String>?): MutableList<String> {
        val allDogsLists = mutableListOf<String>()
        dogResponse?.forEach {
            allDogsLists.add(it)
        }
        return allDogsLists
    }
}