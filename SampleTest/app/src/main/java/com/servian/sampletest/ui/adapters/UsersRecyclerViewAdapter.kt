package com.servian.sampletest.ui.adapters

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.servian.sampletest.R
import com.servian.sampletest.model.User
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.servian.sampletest.BR



class UsersRecyclerViewAdapter(
    private val usersList: List<User>,
    private val listener : (Int) -> Unit
) : RecyclerView.Adapter<UsersRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.users_item, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = usersList[position]
        holder.binding!!.setVariable(BR.user, item)
        holder.itemView.setOnClickListener{ listener(item.id )}
    }

    override fun getItemCount(): Int = usersList.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding: ViewDataBinding? = DataBindingUtil.bind(view)
    }
}