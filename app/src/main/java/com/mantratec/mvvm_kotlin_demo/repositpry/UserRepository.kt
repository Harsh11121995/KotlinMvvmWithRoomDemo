package com.mantratec.mvvm_kotlin_demo.repositpry

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.mantratec.mvvm_kotlin_demo.model.UserDataResponseModel
import com.mantratec.mvvm_kotlin_demo.retrofit.ApiClient
import com.mantratec.mvvm_kotlin_demo.room.LoginDatabase
import com.mantratec.mvvm_kotlin_demo.room.LoginTableModel
import com.mantratec.mvvm_kotlin_demo.view_model.UserViewModel
import com.mantratec.sailrourkela.utils.api.ApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.HttpException

class UserRepository : PageKeyedDataSource<Int, UserDataResponseModel>() {

    lateinit var userDataResponseModel: MutableLiveData<UserDataResponseModel>
    private val TAG = UserRepository::class.java.simpleName
    private val apiInterface: ApiInterface = ApiClient.getClient().create(ApiInterface::class.java)

    companion object {
        var loginDatabase: LoginDatabase? = null
        var loginTableModel: LiveData<LoginTableModel>? = null
        private fun initializeDB(context: Context): LoginDatabase {
            return LoginDatabase.getDataseClient(context)
        }

        fun insertData(context: Context,loginTableModel: LoginTableModel) {
            loginDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
//                val loginTableModel = LoginTableModel()
                loginDatabase!!.loginDao().insertData(loginTableModel)
            }
        }
    }

    @SuppressLint("CheckResult")
    fun getUserData(data: Int): LiveData<UserDataResponseModel> {
        userDataResponseModel = MutableLiveData()
        try {
            apiInterface.getBookData(data)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    try {
                        if (result != null) {
                            Log.e("UserDataGet", "success.")
                            userDataResponseModel.value = result

                            for (i in 0 until result.items.size) {

                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }, { error ->
                    try {
                        val error: HttpException = error as HttpException
                        val errorBody: String = error.response().errorBody()!!.string()
                        Log.e("UserDataGet", "onError:: $errorBody")
                        /*val errorResponse: ErrorResponse =
                        Gson().fromJson(errorBody, ErrorResponse::class.java)
                    Log.e("LeaveBalanceRes", "onError:: ${errorResponse.message}")*/

                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                })

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return userDataResponseModel
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, UserDataResponseModel>
    ) {
        TODO("Not yet implemented")
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, UserDataResponseModel>
    ) {
        TODO("Not yet implemented")
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, UserDataResponseModel>
    ) {
        TODO("Not yet implemented")
    }


}