package com.frostdev.dogbreeds.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.frostdev.dogbreeds.helpers.PersistentSettings
import com.frostdev.dogbreeds.injection.module.DataModule
import com.frostdev.dogbreeds.model.SingleDog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FavoriteDogsListViewModel: BaseViewModel() {

    var allDogsList: MutableLiveData<MutableList<SingleDog>> = MutableLiveData()

    init{
        loadFavoriteDogs()
    }

    private fun loadFavoriteDogs() {
        /*viewModelScope.launch {
            var dogs: MutableList<SingleDog> = mutableListOf()
            withContext(Dispatchers.IO) {
                val persistentFavorites = mPersistentSettings.com.getSettingStringSet(DataModule.ACTIVE_FAVORITES, null)
                if(!persistentFavorites.isNullOrEmpty()) {
                    dogs = collectDogs(mPersistentSettings.getSettingStringSet(DataModule.ACTIVE_FAVORITES, null)!!)
                }
            }
            allDogsList.postValue(dogs)
        }*/
    }

    private fun collectDogs(dogFavs: Set<String>): MutableList<SingleDog> {
        val allDogsLists = mutableListOf<SingleDog>()
      /*  dogFavs?.message?.forEach {
            allDogsLists.add(SingleDog(it.key, it.value, dogApi.getRandomImageFromBreed(it.key).execute().body()?.message))
        }*/
        return allDogsLists
    }
}