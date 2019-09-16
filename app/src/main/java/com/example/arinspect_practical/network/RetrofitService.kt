package com.example.arinspect_practical.network

import com.example.arinspect_practical.user.UserData
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Meera
 * Date : 15-09-2019.
 * Package_Name : com.example.arinspect_practical.network
 * Interface_Name : RetrofitService
 * Description : Interface using special retrofit annotations to encode details about the parameters and request method.
 */
interface RetrofitService
{
    /***** https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json *****/
    @GET("s/2iodh4vg0eortkl/facts.json")
    fun getUserData() : Call<UserData>
}