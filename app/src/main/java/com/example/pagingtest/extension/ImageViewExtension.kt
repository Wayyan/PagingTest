package com.example.pagingtest.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.pagingtest.R

fun ImageView.loadUrl(url: String) {
    Glide.with(this.context)
        .load(url)
        .fallback(R.drawable.baseline_battery_0_bar_24)
        .error(R.drawable.baseline_battery_0_bar_24)
        .fitCenter()
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(this)
}