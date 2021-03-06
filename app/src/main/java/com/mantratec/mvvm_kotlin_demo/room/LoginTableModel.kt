package com.mantratec.mvvm_kotlin_demo.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserTable")
data class LoginTableModel(
    @ColumnInfo(name = "firstName")
    var firstName: String = "",

    @ColumnInfo(name = "lastName")
    var Lastname: String = "",

    @ColumnInfo(name = "photoes")
    var photo: String = ""
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var Id: Int? = null


}