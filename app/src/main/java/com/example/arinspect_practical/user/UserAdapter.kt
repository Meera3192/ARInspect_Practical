package com.example.arinspect_practical.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.arinspect_practical.R
import com.example.arinspect_practical.databinding.RowUserBinding

/**
 * Created by Meera
 * Date : 15-09-2019.
 * Package_Name : com.example.arinspect_practical.user
 * Class_Name : UserAdapter
 * Description : This class work as subclass of RecyclerView.Adapter responsible for providing views that represent items in a data set.
 */

/**
 * Use this method for binding user image.
 */
@BindingAdapter("User_Image")
fun loadImage(view : AppCompatImageView, url: String?)
{
    Glide.with(view.context)
        .load(url)
        .placeholder(R.drawable.ic_launcher_background)
        .apply(RequestOptions())
        .into(view)
}

class UserAdapter(userList: List<User>?) : RecyclerView.Adapter<UserAdapter.UserViewHolder>()
{
    var userList: List<User>?

    init {
        this.userList = userList
    }

    /**
     * Use this method for passing view in viewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_user,parent,false)
        return UserViewHolder(view)
    }

    /**
     * This class is used to cache the view objects in order to save memory.
     */
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var xmlbinding : RowUserBinding?

        init {
            xmlbinding = DataBindingUtil.bind(itemView.rootView)
        }
    }

    override fun getItemCount(): Int = userList!!.size

    /**
     * This method used for bind view according position.
     */
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        if(holder != null)
        {
            holder.xmlbinding?.user = userList?.get(position)
        }
    }

    /**
     * Using this method we update recyclerview by new row.
     */
    fun setData(newData: List<User>?) {
        this.userList = newData
        notifyDataSetChanged()
    }

}