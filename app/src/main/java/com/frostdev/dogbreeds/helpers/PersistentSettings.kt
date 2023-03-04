package com.frostdev.dogbreeds.helpers

import android.content.Context
import android.content.SharedPreferences
import com.frostdev.dogbreeds.model.SingleDog
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersistentSettings @Inject constructor(context: Context) {

    private var settings: SharedPreferences
    private var editor: SharedPreferences.Editor

    init {
        settings = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
        editor = settings.edit()
    }


    fun getFavouriteDogsSet(setting: String?, defValues: Set<SingleDog>): Set<SingleDog>? {
        //return settings.getStringSet(setting, defValues)
        return null
    }

    fun setFavouriteDogsSet(setting: String?, defValues: Set<String?>?): Set<String>? {
        return null
    }

    companion object {
        private const val PREF_FILE_NAME = "DogPreferences.dat"

        /**
         * This is to be removed when we get the proper NPVR backend, this is only used for the NPVRService,
         * which is a mocked NPVR backend.
         */
        var instance: PersistentSettings? = null
            private set

        fun getInstance(context: Context): PersistentSettings {
            if (instance == null) {
                instance = PersistentSettings(context)
            }
            return instance!!
        }
    }
}