package com.frostdev.dogbreeds.interfaces

import android.graphics.drawable.Drawable
import com.frostdev.dogbreeds.model.AllImagesResponse
import com.frostdev.dogbreeds.model.DogResponse
import com.frostdev.dogbreeds.model.ImageRandomDogResponse
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url


interface DogService {

    @GET("https://dog.ceo/api/breeds/list/all")
    fun getDogs() : Call<DogResponse>

    @GET("https://dog.ceo/api/breed/{breed}/images/random")
    fun getRandomImageFromBreed(@Path("breed") breed: String) : Call<ImageRandomDogResponse>

    @GET("https://dog.ceo/api/breed/{breed}/images")
    fun getAllImagesFromBreed(@Path("breed") breed: String) : Call<AllImagesResponse>
}