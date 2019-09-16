package com.example.arinspect_practical.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by Meera
 * Date : 15-09-2019.
 * Package_Name : com.example.arinspect_practical.user
 * Class_Name : UserDAO
 * Description : DAO validates your SQL at compile-time and associates it with a method.
 * we perform all the SQLite query in DAO class.
 * Here we perform Insert and get all the data from table.
 */
@Dao
interface UserDAO
{
    // Using this method we Insert data in User table
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUserData(user : List<User>)

    // Using this method we get all record from user table
    @Query("SELECT * from User")
    fun getAllData() : LiveData<List<User>>

}