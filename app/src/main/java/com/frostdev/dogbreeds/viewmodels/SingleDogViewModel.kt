package com.frostdev.dogbreeds.viewmodels

import androidx.lifecycle.MutableLiveData
import com.frostdev.dogbreeds.model.SingleDog
import java.util.*


class SingleDogViewModel: BaseViewModel() {

    private val breed = MutableLiveData<String>()
    private val image = MutableLiveData<String?>()

    fun bind(singleDog: SingleDog){
        breed.value = singleDog.breed.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        image.value = singleDog.imageUrl
    }

    fun getBreed(): MutableLiveData<String> {
        return breed
    }
}