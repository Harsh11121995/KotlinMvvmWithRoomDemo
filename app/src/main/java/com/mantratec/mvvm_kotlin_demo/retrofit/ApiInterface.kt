package com.mantratec.sailrourkela.utils.api

import com.mantratec.mvvm_kotlin_demo.model.UserDataResponseModel

import io.reactivex.Observable
import retrofit2.http.*

interface ApiInterface {

    @GET("users")
    fun getBookData(
        @Query("page") page: Int?
    ): Observable<UserDataResponseModel>

}