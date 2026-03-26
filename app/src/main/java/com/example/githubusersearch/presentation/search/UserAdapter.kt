package com.example.githubusersearch.presentation.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.githubusersearch.R
import com.example.githubusersearch.data.model.UserDto
import com.example.githubusersearch.databinding.ItemUserBinding

class UserAdapter(
    private val onUserClick: (UserDto) -> Unit
): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val users = mutableListOf<UserDto>()

    fun setUsers(users: List<UserDto>) {
        this.users.clear()
        this.users.addAll(users)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserDto) {
            binding.username.text = user.username
            binding.avatar.load(user.avatarUrl){
                crossfade(true)
                placeholder(R.drawable.ic_avatar)
            }
            binding.root.setOnClickListener { onUserClick(user) }
        }
    }


    override fun onCreateViewHolder(
        p0: ViewGroup,
        p1: Int
    ): UserAdapter.UserViewHolder {
        return UserViewHolder(ItemUserBinding.inflate(LayoutInflater.from(p0.context), p0, false))
    }

    override fun onBindViewHolder(p0: UserAdapter.UserViewHolder, p1: Int) {
        p0.bind(users[p1])
    }


    override fun getItemCount(): Int {
        return users.size
    }
}