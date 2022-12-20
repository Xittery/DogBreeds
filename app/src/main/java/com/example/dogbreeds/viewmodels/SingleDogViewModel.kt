package com.example.dogbreeds.viewmodels

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.lifecycle.MutableLiveData
import com.example.dogbreeds.model.SingleDog
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


class SingleDogViewModel: BaseViewModel() {

    private val breed = MutableLiveData<String>()
    private val image = MutableLiveData<String>()

    fun bind(singleDog: SingleDog){
        breed.value = singleDog.breed
        image.value = singleDog.image
    }

    fun getBreed(): MutableLiveData<String> {
        return breed
    }

    fun getImage(): Drawable? {
        return drawableFromUrl(image.value)
    }

    @Throws(IOException::class)
    fun drawableFromUrl(url: String?): Drawable? {
        val connection: HttpURLConnection = URL(url).openConnection() as HttpURLConnection
        connection.connect()
        val x = BitmapFactory.decodeStream(connection.inputStream)
        return BitmapDrawable(Resources.getSystem(), x)
    }

}