package com.example.dogbreeds.viewmodels

import com.example.dogbreeds.adapters.FavoriteDogsAdapter
import com.example.dogbreeds.interfaces.DogService
import com.example.dogbreeds.model.DogResponse
import com.example.dogbreeds.model.SingleDog
import io.reactivex.Single
import javax.inject.Inject


class FavoriteDogsListViewModel: BaseViewModel() {

    @Inject
    lateinit var dogApi: DogService

    var favoriteDogsList = mutableListOf<SingleDog>()
    var favoriteDogsAdapter: FavoriteDogsAdapter = FavoriteDogsAdapter()

    init{
        loadFavoriteDogs()
    }

    private fun loadFavoriteDogs() {
        display(dogApi.getDogs().execute().body())
    }

    private fun display(dogResponse: DogResponse?) {
        favoriteDogsList = mutableListOf<SingleDog>()
        dogResponse?.message?.forEach {
            favoriteDogsList.add(SingleDog(it.key, it.value, dogApi.getRandomImageFromBreed(it.key).execute().body()?.message!!))
        }
        favoriteDogsAdapter.updateSingleDogList(favoriteDogsList)
    }
}