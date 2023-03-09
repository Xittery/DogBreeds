package com.frostdev.dogbreeds.helpers

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import java.net.HttpURLConnection
import java.net.URL
import javax.inject.Singleton

@Singleton
class GetDrawable {

    fun drawableFromUrl(url: String?): Drawable? {
        val connection: HttpURLConnection = URL(url).openConnection() as HttpURLConnection
        connection.connect()
        return if(connection.responseCode != 404) {
            BitmapDrawable(
                Resources.getSystem(),
                BitmapFactory.decodeStream(connection.inputStream)
            )
        } else {
            null
        }
    }

}