package com.aditya.foodrecipeapp.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.aditya.foodrecipeapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("urlToImage")
fun urlToImage(view: ImageView, s: String?) {
    val options = RequestOptions.placeholderOf(R.drawable.loading)
    Glide.with(view).setDefaultRequestOptions(options).load(s ?: "").into(view)
}

