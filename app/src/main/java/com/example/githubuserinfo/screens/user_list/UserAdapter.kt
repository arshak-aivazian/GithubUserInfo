package com.example.githubuserinfo.screens.user_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuserinfo.R
import com.example.githubuserinfo.network.pojo.User
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_item.view.*

class UserAdapter : PagedListAdapter<User, UserAdapter.UserViewHolder>(
    UserListDiffUtil()
) {
    private var listener: OnSelectedItem? = null

    fun setListener(listener: OnSelectedItem) {
        this.listener = listener
    }
    
    interface OnSelectedItem {
        fun onClick(login: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.user_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class UserListDiffUtil : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: User) {
            itemView.label_name.text = user.login
            Picasso.get().load(user.avatarUrl)
                .error(R.drawable.default_avatar)
                .into(itemView.iv_avatar)

            itemView.setOnClickListener({
                listener?.onClick(user.login)
            })
        }
    }
}
