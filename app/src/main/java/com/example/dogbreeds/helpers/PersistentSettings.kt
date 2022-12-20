package com.example.dogbreeds.helpers

import android.content.Context
import android.content.SharedPreferences
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


    fun getSettingStringSet(setting: String?, defValues: Set<String?>?): Set<String>? {
        return settings.getStringSet(setting, defValues)
    }

    fun setSettingStringSet(setting: String?, defValues: Set<String?>?): Set<String>? {
        //return settings.edit().putStringSet()
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