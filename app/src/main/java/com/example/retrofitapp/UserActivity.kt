package com.example.retrofitapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitapp.repository.models.User

class UserActivity(private val userList: List<User>) : RecyclerView.Adapter<UserActivity.UserViewHolder>() {

    var onItemClick: ((User) -> Unit)? = null

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.firstNameTV)
        val lastName: TextView = itemView.findViewById(R.id.lastNameTV)
        val img: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.users_layout_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = userList[position]
        holder.name.text = currentUser.firstName
        holder.lastName.text = currentUser.lastName
        Glide.with(holder.itemView.context).load(currentUser.img).into(holder.img)
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(currentUser)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}