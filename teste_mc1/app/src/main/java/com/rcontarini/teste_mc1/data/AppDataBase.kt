package com.rcontarini.teste_mc1.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rcontarini.teste_mc1.model.User

@Database(version = 5, entities = arrayOf(User::class))
abstract class AppDataBase : RoomDatabase() {

    abstract fun userDao(): UserDao
}