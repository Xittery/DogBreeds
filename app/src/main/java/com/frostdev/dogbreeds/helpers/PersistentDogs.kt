package com.frostdev.dogbreeds.helpers

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Singleton

@Singleton
class PersistentDogs constructor(context: Context) {

    private var favDogs: SharedPreferences

    companion object {
        const val PREF_FILE_NAME = "DogsPreferences.dat"
    }

    init {
        favDogs = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
    }


    fun getFavouriteDogsSet(): MutableSet<String>? {
        return favDogs.getStringSet("dogs", null)
    }

    fun addToFavouriteDogsSet(dog: String) {
        val dogs = getFavouriteDogsSet() ?: mutableSetOf()
        if (!dogs.contains(dog)) {
            dogs.add(dog)
            favDogs.edit().putStringSet("dogs", dogs).apply()
        }
    }

}