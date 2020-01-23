package com.rcontarini.teste_mc1.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user")
data class User(@PrimaryKey(autoGenerate = true) var id: Long = 0,
                @ColumnInfo(name = "name") var name : String,
                @ColumnInfo(name = "address") var address : String,
                @ColumnInfo(name = "number") var number : Long,
                @ColumnInfo(name = "cpf") var cpf : String) : Serializable {


}