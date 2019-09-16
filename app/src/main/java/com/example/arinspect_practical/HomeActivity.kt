package com.example.arinspect_practical

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.arinspect_practical.user.User
import com.example.arinspect_practical.user.UserAdapter
import com.example.arinspect_practical.user.UserData
import com.example.arinspect_practical.user.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager

/**
 * Created by Meera
 * Date : 15-09-2019.
 * Package_Name : com.example.arinspect_practical
 * Class_Name : HomeActivity
 * Description :
 */
class HomeActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    lateinit var prefs : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //get UserViewModel instance
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        initialize()
    }

    private fun initialize() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this)
        if (!prefs.getBoolean("FIRSTTIME", false)) {
            // run one time databaseinsert code here
            databaseSetup()
            // mark first time has run.
            val editor = prefs.edit()
            editor.putBoolean("FIRSTTIME", true)
            editor.commit()
        }

        // Call API according MVVM Architecture
        fetchUserData()

        // Implementing ScrollListener of RecyclerView for getting last position.
        rvUser.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1)) {
                    databaseSetup()
                    userViewModel.getAllUserData().observe(this@HomeActivity, object : Observer<List<User>> {
                        override fun onChanged(userList: List<User>?) {
                            UserAdapter(userList).setData(userList)
                        }
                    })
                }
            }
        })

    }

    /**
     * After getting data from API we save title in shared preference and insert data in database.
     */
    private fun databaseSetup() {
        userViewModel.getDataFromAPI().observe(this, object : Observer<UserData> {
            override fun onChanged(data: UserData?) {
                val editor = prefs.edit()
                editor.putString("TITLE", data?.title)
                editor.commit()
                InsertData(data)
            }
        })
    }

    /**
     * Get selected feild from data and create list of User(Data class used for selected field).
     * passing the list in UserViewModel class method.
     */
    private fun InsertData(data: UserData?) {

        val mList = ArrayList<User>()
        if (data != null) {
            for (row in data.rows) {
                if (row.description.isNullOrEmpty() && row.imageHref.isNullOrEmpty() && row.title.isNullOrEmpty()) {
                    continue
                }
                val user = User(row.description, row.imageHref, row.title)
                mList.add(user)
            }
        }
        userViewModel.insertUserData(mList)
    }

    /**
     * Get all User record and pass in bindUserData method.
     */
    private fun fetchUserData() {

        userViewModel.getAllUserData().observe(this, object : Observer<List<User>> {
            override fun onChanged(userList: List<User>?) {
                bindUserData(userList)
            }
        })
    }


    /**
     * Bind User data in RecyclerView and title in toolbar.
     */
    private fun bindUserData(userList: List<User>?) {
        if (!prefs.getString("TITLE", null).isNullOrEmpty()) {
            getSupportActionBar()?.setTitle(prefs.getString("title", "About Country"))
        }

        rvUser.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        var adapter = UserAdapter(userList)
        rvUser.adapter = adapter

    }

}
