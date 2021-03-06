package com.mantratec.mvvm_kotlin_demo.model

import com.google.gson.annotations.SerializedName

data class UserResponseModel(
    @SerializedName("id") val id: String = "",
    @SerializedName("email") val email: String = "",
    @SerializedName("first_name")  val first_name: String = "",
    @SerializedName("last_name") val lastname: String = "",
    @SerializedName("avatar") val avtar: String = ""
)