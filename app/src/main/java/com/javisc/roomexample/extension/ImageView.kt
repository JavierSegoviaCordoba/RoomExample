package com.javisc.roomexample.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

fun ImageView.glideCrossFadeCircle(url: String) =
    Glide.with(this).load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .apply(RequestOptions.circleCropTransform())
        .into(this)