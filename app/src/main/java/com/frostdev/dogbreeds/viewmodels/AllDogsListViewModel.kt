package com.frostdev.dogbreeds.viewmodels

import com.frostdev.dogbreeds.adapters.AllDogsAdapter
import com.frostdev.dogbreeds.fragments.AllDogsFragment
import com.frostdev.dogbreeds.interfaces.DogService
import com.frostdev.dogbreeds.model.DogResponse
import com.frostdev.dogbreeds.model.SingleDog
import javax.inject.Inject


class AllDogsListViewModel(breedSelectionChangedListener: AllDogsFragment.BreedSelectionListener): BaseViewModel() {

    @Inject
    lateinit var dogApi: DogService


    var allDogsList: MutableList<SingleDog>? = null
    var allDogsAdapter: AllDogsAdapter = AllDogsAdapter(breedSelectionChangedListener)

    init{
        loadBreeds()
    }

    private fun loadBreeds() {
        display(dogApi.getDogs().execute().body())
    }

    private fun display(dogResponse: DogResponse?) {
        allDogsList = mutableListOf()
        dogResponse?.message?.forEach {
            allDogsList!!.add(SingleDog(it.key, it.value, dogApi.getRandomImageFromBreed(it.key).execute().body()?.message))
        }
        allDogsAdapter.updateSingleDogList(allDogsList!!)
    }
}