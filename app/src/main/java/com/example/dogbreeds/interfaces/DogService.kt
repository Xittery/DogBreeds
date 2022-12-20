package com.example.dogbreeds.interfaces

import com.example.dogbreeds.model.AllImagesResponse
import com.example.dogbreeds.model.DogResponse
import com.example.dogbreeds.model.ImageRandomDogResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DogService {

    @GET("breeds/list/all")
    fun getDogs() : Call<DogResponse>

    @GET("breed/{breed}/images/random")
    fun getRandomImageFromBreed(@Path("breed") breed: String) : Call<ImageRandomDogResponse>

    @GET("breed/{breed}/images")
    fun getAllImagesFromBreed(@Path("breed") breed: String) : Call<AllImagesResponse>

}