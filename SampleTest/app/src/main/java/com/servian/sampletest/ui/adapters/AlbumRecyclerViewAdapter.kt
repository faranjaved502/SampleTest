package com.servian.sampletest.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.servian.sampletest.BR
import com.servian.sampletest.R
import com.servian.sampletest.model.Album

class AlbumRecyclerViewAdapter(
    private val albumList: List<Album>,
    private val listener : (Album) -> Unit
) : RecyclerView.Adapter<AlbumRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.albums_item, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = albumList[position]
        holder.binding!!.setVariable(BR.album, item)
        holder.itemView.setOnClickListener{ listener(item )}
    }

    override fun getItemCount(): Int = albumList.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding: ViewDataBinding? = DataBindingUtil.bind(view)
    }
}