package com.rcontarini.teste_mc1.data

import androidx.room.*
import com.rcontarini.teste_mc1.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user") fun getAllUsers(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertUsers(vararg users: User)

    @Update
    fun updateUser(user: User)

    @Delete fun deleteUser(user: User)
}