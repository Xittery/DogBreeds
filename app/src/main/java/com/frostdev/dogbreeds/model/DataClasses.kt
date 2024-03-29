package com.frostdev.dogbreeds.model

import android.graphics.drawable.Drawable
import com.google.gson.annotations.SerializedName

data class DogResponse(@SerializedName("status")var status: String,
                       @SerializedName("message")var message: Map<String, List<String>>)

data class ImageRandomDogResponse(@SerializedName("status")var status: String,
                                  @SerializedName("message")var message: String)
data class AllImagesResponse(@SerializedName("status")var status: String,
                                  @SerializedName("message")var message: List<String>)

data class SingleDog(var breed: String, var subBreeds: List<String>?, var imageUrl: String) : java.io.Serializable