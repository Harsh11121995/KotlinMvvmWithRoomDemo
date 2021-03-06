package com.mantratec.mvvm_kotlin_demo.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DAOAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(loginTableModel: LoginTableModel)

    /*@Query("")
    fun getLoginDetails(username: String?) : LiveData<LoginTableModel>*/
}