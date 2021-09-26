package com.servian.sampletest.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.servian.sampletest.R

@BindingAdapter("bind:image_url")
fun loadImage(imageView: ImageView, url: String) {
    Glide.with(imageView.context)
        .load(url)
        .placeholder(R.color.orange)
        .error(R.color.orange)
        .into(imageView)
}