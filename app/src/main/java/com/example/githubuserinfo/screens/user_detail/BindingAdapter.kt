package com.example.githubuserinfo.screens.user_detail

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter(value = ["app:urlImage", "app:errorImage"], requireAll = false)
fun loadImage(view: ImageView, url: String?, errorImage: Drawable) {
    Picasso.get().load(url)
        .error(errorImage)
        .into(view)
}
