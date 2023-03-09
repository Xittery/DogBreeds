package com.frostdev.dogbreeds.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.frostdev.dogbreeds.helpers.GetDrawable
import com.frostdev.dogbreeds.interfaces.DogService
import com.frostdev.dogbreeds.model.DogResponse
import com.frostdev.dogbreeds.model.SingleDog
import kotlinx.coroutines.*
import javax.inject.Inject

class AllDogsListViewModel: BaseViewModel() {

    @Inject
    lateinit var dogApi: DogService

    @Inject
    lateinit var getDrawable: GetDrawable

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
            allDogsLists.add(SingleDog(it.key, it.value, dogApi.getRandomImageFromBreed(it.key).execute().body()!!.message))
        }
        return allDogsLists
    }
}