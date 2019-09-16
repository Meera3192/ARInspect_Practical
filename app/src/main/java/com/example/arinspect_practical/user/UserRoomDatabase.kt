package com.example.arinspect_practical.user

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by Meera
 * Date : 15-09-2019.
 * Package_Name : com.example.arinspect_practical.user
 * Class_Name : UserRoomDatabase
 * Description : Room is a database layer on top of an SQLite database.
 * It also provide compile time verification.
 * Using this abstract class we create one instance of UserRoomDatabase.
 */
@Database(entities = [User::class],version = 1)
abstract class UserRoomDatabase : RoomDatabase()
{
    //abstract method of DAO class
    abstract fun userDAO() : UserDAO

    /**
     * Create Singleton class for multiple instances of database opening at the same time.
     */
    companion object
    {
        private var instance : UserRoomDatabase? = null

        @Synchronized
        fun getInstance(context: Context) : UserRoomDatabase
        {
            if(instance == null)
            {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserRoomDatabase::class.java,
                    "User_Database")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance as UserRoomDatabase
        }
    }

}