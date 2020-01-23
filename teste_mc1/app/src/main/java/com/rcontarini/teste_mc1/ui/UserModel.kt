package com.rcontarini.teste_mc1.ui

import com.rcontarini.teste_mc1.data.MyApplication
import com.rcontarini.teste_mc1.data.UserDao
import com.rcontarini.teste_mc1.model.User
import java.lang.Exception

class UserModel {

    var mUserDao: UserDao = MyApplication.database?.userDao()!!

    fun saveUser(user: User) : Boolean {
        return try {
            mUserDao.insertUsers(user)
            true
        } catch ( e  : Exception ) {
            false
        }
    }

    fun updateUser(user: User) : Boolean {
        return try {
            mUserDao.updateUser(user)
            true
        } catch ( e : Exception ) {
            false
        }
    }

    fun getAllUsers() : List<User> {
        return mUserDao.getAllUsers()
    }

    fun removeItem(user: User) : Boolean {
        return try {
            mUserDao.deleteUser(user)
            true
        } catch ( e : Exception ){
            false
        }
    }
}