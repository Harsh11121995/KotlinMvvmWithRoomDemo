package com.mantratec.mvvm_kotlin_demo.model

import com.google.gson.annotations.SerializedName

data class UserDataResponseModel(
    @SerializedName("page") private val _page: String = "",
    @SerializedName("per_page") private val _per_page: String = "",
    @SerializedName("total") private val _total: String = "",
    @SerializedName("total_pages") private val _total_pages: String = "",
    @SerializedName("data") private val _items: ArrayList<UserResponseModel>? = null
) {
    val items: ArrayList<UserResponseModel> get() = _items ?: ArrayList();
    val total_page: String get() = _total_pages
}