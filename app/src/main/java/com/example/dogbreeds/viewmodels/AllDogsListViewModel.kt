package com.example.dogbreeds.viewmodels

import com.example.dogbreeds.adapters.AllDogsAdapter
import com.example.dogbreeds.fragments.AllDogsFragment
import com.example.dogbreeds.interfaces.DogService
import com.example.dogbreeds.model.DogResponse
import com.example.dogbreeds.model.SingleDog
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
            allDogsList!!.add(SingleDog(it.key, it.value, dogApi.getRandomImageFromBreed(it.key).execute().body()?.message!!))
        }
        allDogsAdapter.updateSingleDogList(allDogsList!!)
    }
}