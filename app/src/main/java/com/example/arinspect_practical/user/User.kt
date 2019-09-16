package com.example.arinspect_practical.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Created by Meera
 * Date : 15-09-2019.
 * Package_Name : com.example.arinspect_practical.user
 * Class_Name : User
 * Description : Create User Entity class with unique record and primary key
 */
@Entity(indices = arrayOf(Index(value = ["title"], unique = true)))
data class User(
    @ColumnInfo(name = "description")
    var description: String?,

    @ColumnInfo(name = "imageHref")
    var imageHref: String?,

    @ColumnInfo(name = "title")
    var title: String?
)
{
    @PrimaryKey(autoGenerate = true)
    var id:Int =0
}