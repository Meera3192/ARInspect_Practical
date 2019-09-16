package com.example.arinspect_practical.user

import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.arinspect_practical.network.RetrofitClient
import retrofit2.Call
import retrofit2.Response

/**
 * Created by Meera
 * Date : 15-09-2019.
 * Package_Name : com.example.arinspect_practical.user
 * Class_Name : UserRepository
 * Description : This class is used for access of Multiple DataSources.
 * Repository is not part of architecture component.
 * Here we call the API and pass Data in UserViewModel.
 * Get all record from user table using UserDAO object
 * and Insert data in user table
 */
class UserRepository(userDAO: UserDAO) {

    var userDAO : UserDAO

    init {
        this.userDAO = userDAO
    }

    // Assign All record of User
    var allData : LiveData<List<User>> = userDAO.getAllData()
    val TAG = "UserRepository"
    lateinit var mUserList : MutableLiveData<UserData>

    /**
     * This method use for API call and return list of User data
     */
    fun getCallAPI(): LiveData<UserData> {
        mUserList = MutableLiveData()

        val call = RetrofitClient.retrofitService?.getUserData()
        call?.enqueue(object : retrofit2.Callback<UserData> {
            override fun onFailure(call: Call<UserData>, t: Throwable) {
                Log.d(TAG, t.message)
            }

            override fun onResponse(call: Call<UserData>, response: Response<UserData>) {

                if (response.isSuccessful) {
                    mUserList.value = response.body()
                }
            }
        })
        return mUserList
    }

    /**
     *  Use this methode we execute AsyncTask for insert data
     */
    fun inserUserData(userList: ArrayList<User>) {
        InsertUserData(userDAO).execute(userList)
    }

    /**
     * Insert Data process run in background
     */
    class InsertUserData(userDAO: UserDAO) : AsyncTask<List<User>,Void,Void>() {
        var userDAO:UserDAO
        init {
            this.userDAO = userDAO
        }
        override fun doInBackground(vararg mList: List<User>?): Void? {
            userDAO.insertUserData(mList[0]!!)
            return null
        }

    }

}