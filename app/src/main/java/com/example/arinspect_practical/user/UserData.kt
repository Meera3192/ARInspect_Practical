package com.example.arinspect_practical.user

/**
 * Created by Meera
 * Date : 15-09-2019.
 * Package_Name : com.example.arinspect_practical.user
 * Class_Name : UserData
 * Description : This Data class use for API response.
 */
data class UserData(
    val rows: List<Row>,
    val title: String?
)

data class Row(
    val description: String?,
    val imageHref: String?,
    val title: String?
)