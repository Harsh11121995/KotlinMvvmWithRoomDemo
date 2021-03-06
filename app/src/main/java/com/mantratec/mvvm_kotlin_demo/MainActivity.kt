package com.mantratec.mvvm_kotlin_demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mantratec.mvvm_kotlin_demo.adapter.EMPAdapter
import com.mantratec.mvvm_kotlin_demo.model.UserDataResponseModel
import com.mantratec.mvvm_kotlin_demo.model.UserResponseModel
import com.mantratec.mvvm_kotlin_demo.room.LoginTableModel
import com.mantratec.mvvm_kotlin_demo.view_model.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    lateinit var userViewModel: UserViewModel
    lateinit var mAdapter: EMPAdapter
    lateinit var userResponseModel: ArrayList<UserResponseModel>
    lateinit var userDataResponseModel: ArrayList<UserDataResponseModel>
    private var layoutManager: LinearLayoutManager? = null
    lateinit var loginTableModel: LoginTableModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialization()
        getUserData()

    }

    fun initialization() {
        userResponseModel = ArrayList()
        layoutManager = LinearLayoutManager(this@MainActivity)
        rcvUser.layoutManager = layoutManager
        mAdapter = EMPAdapter(userResponseModel)
        rcvUser.adapter = mAdapter
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
    }

    /*fun paginationCall(){
        rcvUser.addOnScrollListener(object : RecyclerView)
    }*/

    fun getUserData() {
        userViewModel.getUserResponseLiveData().observe(this, Observer {
        userResponseModel.addAll(it.items)
//            progressBar.visibility = View.GONE
            mAdapter.notifyDataSetChanged()
            loginTableModel = LoginTableModel()

            GlobalScope.launch {
                if (userResponseModel.size > 0) {
                    for (i in 0 until userResponseModel.size) {
                        loginTableModel = LoginTableModel()
                        loginTableModel.firstName = userResponseModel[i].first_name
                        loginTableModel.Lastname = userResponseModel[i].lastname
                        loginTableModel.photo = userResponseModel[i].avtar

                        userViewModel.insertData(this@MainActivity, loginTableModel)
                    }
                }
            }

//            Log.d("MainActivity",it.size.toString())
        })


    }

}