package com.example.arinspect_practical.user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Meera
 * Date : 15-09-2019.
 * Package_Name : com.example.arinspect_practical.user
 * Class_Name : UserViewModel
 * Description : ViewModel class used for holding data for Activity/Fragment.
 * It also communication center between the Repository and the UI.
 * Here we call the repository method for calling Api and return data to the HomeActivity.
 * Inser data in User table using UserDAO instance and get data from user table return it to HomeActivity.
 */
class UserViewModel(application: Application) :  AndroidViewModel(application) {
    var mUserList: LiveData<List<User>>
    var userDAO: UserDAO
    var userRepository: UserRepository

    /**
     *  Initialize UserDAO & UserRepository class instance.
     *  Get reference to UserDAO from UserRoomDatabase.
     *  Pass userDAO instance into UserRepository class.
     */
    init {
        userDAO = UserRoomDatabase.getInstance(application).userDAO()
        userRepository = UserRepository(userDAO)
        mUserList = userRepository.allData
    }

    /**
     * Call the UserRepository class method for API call
     */
    fun getDataFromAPI(): LiveData<UserData> {
        return userRepository.getCallAPI()
    }

    /**
     * Using UserRepository class method perform Insert()
     */
    fun insertUserData(userList: ArrayList<User>) {
        userRepository.inserUserData(userList)
    }

    /**
     * LiveData gives us updated Users when they change.
     */
    fun getAllUserData() : LiveData<List<User>>
    {
        return mUserList
    }
}