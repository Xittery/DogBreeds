package com.frostdev.dogbreeds.viewmodels

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.frostdev.dogbreeds.model.SingleDog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


class SingleDogViewModel: BaseViewModel() {

    private val breed = MutableLiveData<String>()
    private val image = MutableLiveData<Drawable>()

    fun bind(singleDog: SingleDog){
        breed.value = singleDog.breed
        image.value = singleDog.image
    }

    fun getBreed(): MutableLiveData<String> {
        return breed
    }

    fun getImage(): MutableLiveData<Drawable> {
        return image
    }
}