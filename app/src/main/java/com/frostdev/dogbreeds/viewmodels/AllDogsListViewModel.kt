package com.frostdev.dogbreeds.viewmodels

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.frostdev.dogbreeds.interfaces.DogService
import com.frostdev.dogbreeds.model.DogResponse
import com.frostdev.dogbreeds.model.SingleDog
import kotlinx.coroutines.*
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Inject

class AllDogsListViewModel: BaseViewModel() {

    @Inject
    lateinit var dogApi: DogService

    var allDogsList: MutableLiveData<MutableList<SingleDog>> = MutableLiveData()

    fun loadBreeds() {
        viewModelScope.launch {
            var dogs: MutableList<SingleDog>
            withContext(Dispatchers.IO) {
                dogs = collectDogs(dogApi.getDogs().execute().body())
            }
            allDogsList.postValue(dogs)
        }
    }

    fun collectDogs(dogResponse: DogResponse?): MutableList<SingleDog> {
        val allDogsLists = mutableListOf<SingleDog>()
        dogResponse?.message?.forEach {
            val image: Drawable? = drawableFromUrl(dogApi.getRandomImageFromBreed(it.key).execute().body()?.message)
            allDogsLists.add(SingleDog(it.key, it.value, image, it.key))
        }
        return allDogsLists
    }

    @Throws(IOException::class)
    fun drawableFromUrl(url: String?): Drawable? {
        val connection: HttpURLConnection = URL(url).openConnection() as HttpURLConnection
        connection.connect()
        return if(connection.responseCode != 404) {
            BitmapDrawable(
                Resources.getSystem(),
                BitmapFactory.decodeStream(connection.inputStream)
            )
        } else {
            null
        }
    }
}