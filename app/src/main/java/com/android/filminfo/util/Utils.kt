package com.android.filminfo.util

import android.widget.ImageView
import com.android.filminfo.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .override(400, 400).centerInside()
        .placeholder(R.drawable.loading_animation)
        .error(R.drawable.ic_broken_image)
        .skipMemoryCache(true)
        .transform(FitCenter())
        .into(this)
}

fun ImageView.loadImage(res: Int) {
    var requestOptions = RequestOptions()
    requestOptions = requestOptions.transform(FitCenter()).override(400, 400).centerInside()
    Glide.with(this)
    apply {
        requestOptions
    }
        .setImageResource(res)
}