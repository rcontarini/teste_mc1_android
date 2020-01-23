package com.rcontarini.teste_mc1.data

import android.app.Application
import androidx.room.Room

open class MyApplication : Application() {

    companion object {
        var database: AppDataBase? = null
    }

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(this, AppDataBase::class.java, "mc-db").allowMainThreadQueries().build()

    }
}