package com.normal.studentapp.view

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("android:imageUrl")
fun loadPhotoURL(v: ImageView, url: String){
    val picasso = Picasso.Builder(v.context)
    picasso.listener{picasso, uri, exception -> exception.printStackTrace()}
    picasso.build().load(url).into(v)
    v.visibility = View.VISIBLE
}