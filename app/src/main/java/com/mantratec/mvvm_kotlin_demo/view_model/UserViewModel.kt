package com.mantratec.mvvm_kotlin_demo.view_model

import android.content.Context
import android.service.autofill.UserData
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mantratec.mvvm_kotlin_demo.model.UserDataResponseModel
import com.mantratec.mvvm_kotlin_demo.repositpry.UserRepository
import com.mantratec.mvvm_kotlin_demo.room.LoginTableModel

class UserViewModel : ViewModel() {

    var userRepository: UserRepository? = null
    private var userResponseLiveData: LiveData<UserDataResponseModel>? = null

    init {
        userRepository = UserRepository()
        userResponseLiveData = userRepository!!.getUserData(2)
    }

    fun getUserResponseLiveData(): LiveData<UserDataResponseModel> {
        return this.userResponseLiveData!!
    }

    fun getUserApicall(pageNo:Int): LiveData<UserDataResponseModel> {
        userRepository = UserRepository()
        userResponseLiveData = userRepository!!.getUserData(pageNo)
        return this.userResponseLiveData!!
    }

    fun insertData(context: Context,loginTableModel: LoginTableModel) {
        UserRepository.insertData(context,loginTableModel)
    }


}